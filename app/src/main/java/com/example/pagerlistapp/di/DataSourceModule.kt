package com.example.pagerlistapp.di

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagerlistapp.datasource.Functions
import com.example.pagerlistapp.datasource.LoadDataPos
import com.example.pagerlistapp.datasource.PosDataSourceFactory
import dagger.Module
import dagger.Provides



@Module
class DataSourceModule {

    @Provides
    fun getLiveData(loadDataPos: HashMap<String, Functions>,
                    dataSourceFactoryClass: HashMap<String,Class<out Any>>
    ):
            HashMap<String, LiveData<out PagedList<out Any>>> {

        val hashMap = HashMap<String, LiveData<out PagedList<out Any>>>()

        for( entry in loadDataPos) {

            var sourceFactory: DataSource.Factory<out Any, out Any>? = null


            //Если имеем дело с позиционным дата сурсом
            if (entry.value.loadDataPos!=null) {
                sourceFactory = dataSourceFactoryClass[entry.key]!!.constructors[0].newInstance(entry.value.loadDataPos)
                        as DataSource.Factory<out Any, out Any>?
            }

            //Если имеем дело с Item Data source

            /*
            * Будущая реализация
            * */

                //также сюда можно прокинуть и размеры страниц для каждого дата сурса, которые здесь заданы численно
                val pagedListConfig: PagedList.Config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20)
                        .build()

                hashMap[entry.key] = LivePagedListBuilder(sourceFactory?:
                throw IllegalArgumentException("Load Data function not found"), pagedListConfig)
                        .build()
            }


        return hashMap
    }
}