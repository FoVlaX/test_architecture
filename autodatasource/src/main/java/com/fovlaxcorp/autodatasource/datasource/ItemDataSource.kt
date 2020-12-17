package com.fovlaxcorp.autodatasource.datasource

import androidx.paging.ItemKeyedDataSource
import com.fovlax.datasourcelibrary.datasource.GetKeyFunction
import com.fovlax.datasourcelibrary.datasource.LoadDataItem

class ItemDataSource<K,T> (
    private val loadDataItemAfter: LoadDataItem<K, T>?,
    private val loadDataItemBefore: LoadDataItem<K, T>?,
    private val getKeyFunction: GetKeyFunction<K, T>?
        ): ItemKeyedDataSource<K,T>() {

    override fun getKey(item: T): K {
        return getKeyFunction?.invoke(item)!!
    }

    //при вызове invalidate метод отработает в том потоке который его вызвал,
    // для упрощения использования я здесь сразу в другом потоке его запускаю
    override fun loadInitial(params: LoadInitialParams<K>, callback: LoadInitialCallback<T>) {
        if (loadDataItemAfter!=null || loadDataItemBefore!=null) {
            Thread() {
                callback.onResult(
                    loadDataItemAfter?.invoke(
                        getKeyFunction?.invoke(null)!!,
                        params.requestedLoadSize
                    )
                        ?: (loadDataItemBefore?.invoke(
                            getKeyFunction?.invoke(null)!!,
                            params.requestedLoadSize
                        )!!)
                )
            }.start()

        }

    }

    override fun loadAfter(params: LoadParams<K>, callback: LoadCallback<T>) {
        if (loadDataItemAfter!=null) {

                callback.onResult(
                        loadDataItemAfter?.invoke(params.key, params.requestedLoadSize)
                                ?: ArrayList<T>()
                )
        }
    }

    override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<T>) {
        if (loadDataItemBefore!=null) {
                callback.onResult(
                        loadDataItemBefore?.invoke(params.key, params.requestedLoadSize)
                                ?: ArrayList<T>()
                )
        }
    }
}