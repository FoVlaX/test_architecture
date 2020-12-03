package com.example.pagerlistapp.simpledatasourcegenerator

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagerlistapp.simpledatasourcegenerator.annotations.PageConfig
import com.example.pagerlistapp.simpledatasourcegenerator.datasource.Functions

class SimpleDataSourceGenerator {
    fun getLiveDataMapped(loadDataPos: HashMap<String, Functions>,
                    dataSourceFactoryClass: HashMap<String,Class<out Any>>,
                          pageConfig: HashMap<String, PageConfig>
    ): HashMap<String, LiveData<out PagedList<out Any>>> {

        val hashMap = HashMap<String, LiveData<out PagedList<out Any>>>()

        for(entry in loadDataPos) {

            var sourceFactory: DataSource.Factory<out Any, out Any>? = null

            //Если имеем дело с позиционным дата сурсом
            if (entry.value.loadDataPos!=null) {
                sourceFactory = dataSourceFactoryClass[entry.key]!!.constructors[0].newInstance(entry.value.loadDataPos)
                        as DataSource.Factory<out Any, out Any>?
            }

            //Если имеем дело с Item Data source
            if (entry.value.loadDataItemBefore!=null || entry.value.loadDataItemAfter!=null ) {
                sourceFactory = dataSourceFactoryClass[entry.key]!!
                        .constructors[0]
                        .newInstance(entry.value.loadDataItemAfter,
                                entry.value.loadDataItemBefore,
                                entry.value.getKeyFunction)
                        as DataSource.Factory<out Any, out Any>
            }


            val pagedListConfig: PagedList.Config = if (pageConfig.containsKey(entry.key)) {
                PagedList.Config.Builder()
                        .setEnablePlaceholders(pageConfig[entry.key]!!.enablePlaceholders)
                        .setInitialLoadSizeHint(pageConfig[entry.key]!!.initialLoadSizeHint)
                        .setPageSize(pageConfig[entry.key]!!.pageSize)
                        .setMaxSize(pageConfig[entry.key]!!.maxSize)
                        .setPrefetchDistance(pageConfig[entry.key]!!.prefetchDistance)
                        .build()
            }else{
                PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20)
                        .build()
            }

            hashMap[entry.key] = LivePagedListBuilder(sourceFactory?:
            throw IllegalArgumentException("Load Data function not found"), pagedListConfig)
                    .build()
        }

        return hashMap
    }
}