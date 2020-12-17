package com.fovlaxcorp.autodatasource.datasource

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.fovlax.datasourcelibrary.datasource.GetKeyFunction
import com.fovlax.datasourcelibrary.datasource.LoadDataItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class ItemDataSource<K,T> (
    private val loadDataItemAfter: LoadDataItem<K, T>?,
    private val loadDataItemBefore: LoadDataItem<K, T>?,
    private val getKeyFunction: GetKeyFunction<K, T>?
        ): ItemKeyedDataSource<K,T>() {

    override fun getKey(item: T): K {
        return getKeyFunction?.invoke(item)!!
    }

    override fun loadInitial(params: LoadInitialParams<K>, callback: LoadInitialCallback<T>) {
        if (loadDataItemAfter!=null || loadDataItemBefore!=null) {
                callback.onResult(
                        loadDataItemAfter?.invoke(getKeyFunction?.invoke(null)!!, params.requestedLoadSize)
                                ?:(loadDataItemBefore?.invoke(getKeyFunction?.invoke(null)!!, params.requestedLoadSize)!!)
                )

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