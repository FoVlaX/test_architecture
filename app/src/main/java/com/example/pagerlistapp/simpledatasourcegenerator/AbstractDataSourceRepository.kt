package com.example.pagerlistapp.simpledatasourcegenerator

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.pagerlistapp.simpledatasourcegenerator.datasource.Functions
import com.example.pagerlistapp.simpledatasourcegenerator.datasource.GetKeyFunction
import com.example.pagerlistapp.simpledatasourcegenerator.datasource.ItemDataSourceFactory
import com.example.pagerlistapp.simpledatasourcegenerator.datasource.PosDataSourceFactory
import com.example.pagerlistapp.simpledatasourcegenerator.annotations.GenDataSource
import com.example.pagerlistapp.simpledatasourcegenerator.annotations.KeyItem
import com.example.pagerlistapp.simpledatasourcegenerator.annotations.PageConfig
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.functions
import kotlin.reflect.jvm.isAccessible


//Здесь получилось реализовать с помощью Reflection Api
//наверное это можно как то сделать и с генерацией кода, чтобы все было
//эффективнее
abstract class AbstractDataSourceRepository(
        private val clazz: KClass<*>
) {

    private val simpleDataSourceGenerator: SimpleDataSourceGenerator = SimpleDataSourceGenerator()

    fun <K,T> getNewLivePagedList(sourceName: String, initialKey: K?) : LiveData<PagedList<T>>  {
        return simpleDataSourceGenerator.getLiveDataMapped<K,T>(
                functionsMap[sourceName],
                configNameMap[sourceName],
                initialKey) as LiveData<PagedList<T>>
    }


    //Задаем мапы для функций подгрузки данных для датасурсов, а также фабрик
    //для производства этих дата сурсов
    //которые реализованные в данном репозитории
    //таким образом у нас может быть любое количество, функций которые грузят какие либо данные из сети
    //и сохраняют их в бд например и для каждой из них будет сгенерен датасурс, а потом и LiveData<PagedList>
    //которые мы берем во вью модел и уже сетаем вв нужный адаптер
    private val functionsMap: HashMap<String, Functions> = HashMap()
    private val factoriesMap = HashMap<String, Class<out Any>>()

    //Functions - функции которые могут быть использованы в дата сурсах
    //задавать только одну в зависимости от типа дата сурса

    //Сюда запишем функции которые будут получать ключ из обЪекта
    //для вангога акутально так как там почему то ключ, который надо передать
    //в запросе, приходит в массиве в поле ids)
    private val keyFunMap: HashMap<String, GetKeyFunction<*,*>> = HashMap()
    private val configMap: HashMap<KFunction<*>,PageConfig> = HashMap()
    private val configNameMap: HashMap<String, PageConfig> = HashMap()


    init{


        for(method in clazz.functions) {
            for (annotation in method.annotations) {
                if (annotation is KeyItem){
                    method.isAccessible = true
                    keyFunMap[annotation.name] = {
                        method.call(this,it)
                    }
                }
                if (annotation is PageConfig){
                    configMap[method] = annotation
                }
            }
        }

        for(method in clazz.functions) {
            for (annotation in method.annotations) {

                if (annotation is GenDataSource) {

                    if (configMap.containsKey(method)){
                        configNameMap[annotation.sourceName] = configMap[method] as PageConfig
                    }

                    if (annotation.type == GenDataSource.Type.Positional) {
                        method.isAccessible = true
                        functionsMap[annotation.sourceName] = Functions(loadDataPos = { offset, count ->
                            method.call(this, offset, count) as List<out Any?>?
                        })
                        factoriesMap[annotation.sourceName] = PosDataSourceFactory::class.java
                    }

                    if (annotation.type == GenDataSource.Type.ItemKeyedAfter) {
                        method.isAccessible = true
                        if (!factoriesMap.containsKey(annotation.sourceName)) {
                            functionsMap[annotation.sourceName] = Functions(
                                    loadDataItemAfter = { id, count ->
                                        method.call(this, id, count) as List<out Any?>?
                                    },
                                    getKeyFunction = keyFunMap[annotation.sourceName]
                            )
                        }else{
                            functionsMap[annotation.sourceName]?.loadDataItemAfter = { id, count ->
                                method.call(this, id, count) as List<out Any?>?
                            }
                        }
                        factoriesMap[annotation.sourceName] = ItemDataSourceFactory::class.java
                    }

                    if (annotation.type == GenDataSource.Type.ItemKeyedBefore) {
                        method.isAccessible = true
                        if (!factoriesMap.containsKey(annotation.sourceName)) {
                            functionsMap[annotation.sourceName] = Functions(
                                    loadDataItemBefore = { id, count ->
                                        method.call(this, id, count) as List<out Any?>?
                                    },
                                    getKeyFunction = keyFunMap[annotation.sourceName]
                            )
                        }else{
                            functionsMap[annotation.sourceName]?.loadDataItemBefore = { id, count ->
                                method.call(this, id, count) as List<out Any?>?
                            }
                        }
                        factoriesMap[annotation.sourceName] = ItemDataSourceFactory::class.java
                    }

                }
            }
        }
    }

}