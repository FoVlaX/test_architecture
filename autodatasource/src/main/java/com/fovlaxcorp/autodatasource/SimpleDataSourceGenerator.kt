package com.fovlax.datasourcelibrary


import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fovlax.datasourcelibrary.datasource.*


class SimpleDataSourceGenerator {
    fun <K, T> getLiveDataMapped(
        functions: Functions<K,T>?,
        pageConfig: PagedList.Config?,
        initialKey: K?
    ): LiveData<PagedList<T>> {

        var sourceFactory: DataSource.Factory<K, T>? = null

        //Если имеем дело с позиционным дата сурсом
        if (functions?.loadDataPos != null) {
            sourceFactory = PosDataSourceFactory<K, T>(functions.loadDataPos)
        }

        //Если имеем дело с Item Data source
        if (functions?.loadDataItemBefore != null || functions?.loadDataItemAfter != null) {

            var loadDataItemAfter: LoadDataItem<K, T>? = null
            var loadDataItemBefore: LoadDataItem<K, T>? = null
            var getKeyFunction: GetKeyFunction<K, T>? = null

            if (functions?.loadDataItemAfter != null) {
                loadDataItemAfter = functions?.loadDataItemAfter as LoadDataItem<K, T>
            }

            if (functions?.loadDataItemBefore != null) {
                loadDataItemBefore = functions?.loadDataItemBefore as LoadDataItem<K, T>
            }

            if (functions?.getKeyFunction != null) {
                getKeyFunction = functions?.getKeyFunction as GetKeyFunction<K, T>


                sourceFactory = ItemDataSourceFactory<K, T>(
                    loadDataItemAfter,
                    loadDataItemBefore,
                    getKeyFunction
                )
            }
        }


            val pagedListConfig: PagedList.Config = if (pageConfig != null) {
                PagedList.Config.Builder()
                    .setEnablePlaceholders(pageConfig.enablePlaceholders)
                    .setInitialLoadSizeHint(pageConfig.initialLoadSizeHint)
                    .setPageSize(pageConfig.pageSize)
                    .setMaxSize(pageConfig.maxSize)
                    .setPrefetchDistance(pageConfig.prefetchDistance)
                    .build()
            } else {
                PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(20)
                    .build()
            }

            return LivePagedListBuilder<K, T>(
                sourceFactory ?: throw IllegalArgumentException("Load Data function not found"),
                pagedListConfig
            ).setInitialLoadKey(initialKey)
                .build()

        }
}
