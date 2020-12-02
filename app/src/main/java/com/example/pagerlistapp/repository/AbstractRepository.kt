package com.example.pagerlistapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.pagerlistapp.application.App
import com.example.pagerlistapp.datasource.Functions
import com.example.pagerlistapp.datasource.PosDataSourceFactory
import javax.inject.Inject
import kotlin.reflect.KClass
import kotlin.reflect.full.functions
import kotlin.reflect.jvm.isAccessible

abstract class AbstractRepository(
        val clazz: KClass<*>
) {
    init{
        //Задаем мапы для функций подгрузки данных для датасурсов, а также фабрик
        //для производства этих дата сурсов
        //которые реализованные в данном репозитории
        //таким образом у нас может быть любое количество, функций которые грузят какие либо данные из сети
        //и сохраняют их в бд например и для каждой из них будет сгенерен датасурс, а потом и LiveData<PagedList>
        //которые мы берем во вью модел и уже сетаем вв нужный адаптер
        val map: HashMap<String, Functions> = HashMap()
        val factoryMap = HashMap<String, Class<out Any>>()

        //Functions - функции которые могут быть использованы в дата сурсах
        //задавать только одну в зависимости от типа дата сурса

        for(method in clazz.functions) {
            for (annotation in method.annotations) {
                if (annotation is GenDataSource) {
                    if (annotation.type == GenDataSource.Type.Positional) {
                        method.isAccessible = true
                        map[annotation.sourceName] = Functions(loadDataPos = { offset, count ->
                            method.call(this, offset, count) as List<out Any?>?
                        })
                        factoryMap[annotation.sourceName] = PosDataSourceFactory::class.java
                    }
                }
            }
        }

        App.instance?.applicationComponent
                ?.dataSourceComponent()
                //какие фабрики изспользуем
                ?.withClass(factoryMap)
                //какие функции используем для подкгрузки данных в датасоурсах
                ?.with (map)
                ?.build()
                ?.inject(this)
    }

    @Inject
    lateinit var livePagedLists: HashMap<String, LiveData<out PagedList<out Any>>>

}