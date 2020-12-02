package com.example.pagerlistapp.simpledatasourcegenerator.datasource

import androidx.paging.DataSource

class ItemDataSourceFactory<K,T>(
        private val loadDataItemAfter: LoadDataItem<K,T>?,
        private val loadDataItemBefore: LoadDataItem<K,T>?,
        private val getKeyFunction: GetKeyFunction<K,T>?
) : DataSource.Factory<K, T>() {
    override fun create(): DataSource<K, T> {
        return ItemDataSource<K,T>(loadDataItemAfter,loadDataItemBefore, getKeyFunction) as DataSource<K, T>
    }
}