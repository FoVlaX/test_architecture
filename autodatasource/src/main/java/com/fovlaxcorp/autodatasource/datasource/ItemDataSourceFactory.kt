package com.fovlax.datasourcelibrary.datasource

import androidx.paging.DataSource
import com.fovlaxcorp.autodatasource.datasource.ItemDataSource

class ItemDataSourceFactory<K,T>(
    private val loadDataItemAfter: LoadDataItem<K, T>?,
    private val loadDataItemBefore: LoadDataItem<K, T>?,
    private val getKeyFunction: GetKeyFunction<K, T>?,
    val callback: DataSource.InvalidatedCallback? = null
) : DataSource.Factory<K, T>() {
    override fun create(): DataSource<K, T> {
        val datasource = ItemDataSource<K,T>(loadDataItemAfter,loadDataItemBefore, getKeyFunction) as DataSource<K, T>
        if (callback!=null) {
            datasource.addInvalidatedCallback(callback)
        }
        return datasource
    }
}