package com.fovlaxcorp.autodatasource.datasource

import androidx.paging.DataSource
import com.fovlax.datasourcelibrary.datasource.LoadDataPos


class PosDataSourceFactory <K , T> (
        private val loadDataPos: LoadDataPos<T>,
        val callback: DataSource.InvalidatedCallback? = null
        ): DataSource.Factory<K, T>() {
    override fun create(): DataSource<K, T> {
        val datasource = PosDataSource<T>(loadDataPos) as DataSource<K, T>
        if (callback != null) {
            datasource.addInvalidatedCallback(callback)
        }
        return datasource
    }
}