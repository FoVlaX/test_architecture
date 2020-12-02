package com.example.pagerlistapp.simpledatasourcegenerator.datasource

import androidx.paging.DataSource

class ItemDataSourceFactory<K,T>(
        val loadDataItemAfter: LoadDataItem<K,T>?,
        val loadDataItemBefore: LoadDataItem<K,T>?,
        val getKeyFunction: GetKeyFunction<K,T>?
) : DataSource.Factory<K, T>() {
    override fun create(): DataSource<K, T> {
        return ItemDataSource<K,T>(loadDataItemAfter,loadDataItemBefore, getKeyFunction) as DataSource<K, T>
    }
}