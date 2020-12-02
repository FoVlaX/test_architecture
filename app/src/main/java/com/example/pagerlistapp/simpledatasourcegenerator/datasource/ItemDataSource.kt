package com.example.pagerlistapp.simpledatasourcegenerator.datasource

import androidx.paging.ItemKeyedDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class ItemDataSource<K,T> (
        val loadDataItemAfter: LoadDataItem<K,T>?,
        val loadDataItemBefore: LoadDataItem<K,T>?,
        val getKeyFunction: GetKeyFunction<K,T>?
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

                        }

                        override fun onError(e: Throwable?) {

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

                        }

                        override fun onError(e: Throwable?) {

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

                        }

                        override fun onError(e: Throwable?) {

                        }

                    })
        }
    }
}