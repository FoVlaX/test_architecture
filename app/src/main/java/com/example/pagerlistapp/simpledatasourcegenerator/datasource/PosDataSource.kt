package com.example.pagerlistapp.simpledatasourcegenerator.datasource

import androidx.paging.PositionalDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver
import io.reactivex.rxjava3.schedulers.Schedulers




class PosDataSource<T>(
        //функция которая подтягивает данные, можно передать, например из репозитория
    val loadDataPos: LoadDataPos<T>?
) : PositionalDataSource<T>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        //Использовал Runnuble так как зачем личшний раз кидать данные между потоками если их
        //все равно следует передать в callback.onResult
        Completable.fromRunnable {
            callback.onResult(
                    loadDataPos?.invoke(params.requestedStartPosition,params.requestedLoadSize)?:ArrayList<T>(),
                params.requestedStartPosition
            )
        }.subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableCompletableObserver(){
                override fun onComplete() {

                }

                override fun onError(e: Throwable?) {

                }

            })
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        Completable.fromRunnable {
            callback.onResult(
                loadDataPos?.invoke(params.startPosition,params.loadSize)?:ArrayList<T>()
            )
        }.subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableCompletableObserver(){
                override fun onComplete() {

                }

                override fun onError(e: Throwable?) {

                }

            })
    }
}