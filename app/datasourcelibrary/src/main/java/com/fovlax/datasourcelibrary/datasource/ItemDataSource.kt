package com.fovlax.datasourcelibrary.datasource

import android.util.Log
import androidx.paging.ItemKeyedDataSource
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
        if (loadDataItemAfter!=null) {
            Completable.fromRunnable {
                callback.onResult(
                        loadDataItemAfter?.invoke(getKeyFunction?.invoke(null)!!, params.requestedLoadSize)
                                ?: ArrayList<T>()
                )
            }.subscribeOn(Schedulers.io())
                    .subscribeWith(object : DisposableCompletableObserver() {
                        override fun onComplete() {
                            Log.d("SimpleDataSource","loadInitial in ItemKeyed DataSource: method in ${loadDataItemAfter?.javaClass?.name} run completed")
                        }

                        override fun onError(e: Throwable?) {
                            Log.e("SimpleDataSource","Error in $this:  ${loadDataItemAfter?.javaClass?.name} run failed with exception $e")
                        }

                    })
        }

    }

    override fun loadAfter(params: LoadParams<K>, callback: LoadCallback<T>) {
        if (loadDataItemAfter!=null) {
            Completable.fromRunnable {
                callback.onResult(
                        loadDataItemAfter?.invoke(params.key, params.requestedLoadSize)
                                ?: ArrayList<T>()
                )
            }.subscribeOn(Schedulers.io())
                    .subscribeWith(object : DisposableCompletableObserver() {
                        override fun onComplete() {
                            Log.d("SimpleDataSource","loadAfter in ItemKeyed DataSource: method in ${loadDataItemAfter?.javaClass?.name} run completed")
                        }

                        override fun onError(e: Throwable?) {
                            Log.e("SimpleDataSource","Error in $this:  ${loadDataItemAfter?.javaClass?.name} run failed with exception $e")
                        }

                    })
        }
    }

    override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<T>) {
        if (loadDataItemBefore!=null) {
            Completable.fromRunnable {
                callback.onResult(
                        loadDataItemBefore?.invoke(params.key, params.requestedLoadSize)
                                ?: ArrayList<T>()
                )
            }.subscribeOn(Schedulers.io())
                    .subscribeWith(object : DisposableCompletableObserver() {
                        override fun onComplete() {
                            Log.d("SimpleDataSource","loadBefore in ItemKeyed DataSource: method in ${loadDataItemBefore?.javaClass?.name} run completed")
                        }

                        override fun onError(e: Throwable?) {
                            Log.e("SimpleDataSource","Error in $this:  ${loadDataItemBefore?.javaClass?.name} run failed with exception $e")
                        }

                    })
        }
    }
}