package com.example.pagerlistapp.datasource

import androidx.paging.DataSource


class PosDataSourceFactory <K , T> (
    private val loadDataPos: LoadDataPos<T>
        ): DataSource.Factory<K, T>() {
    override fun create(): DataSource<K, T> {
        return PosDataSource<T>(loadDataPos) as DataSource<K, T>
    }
}