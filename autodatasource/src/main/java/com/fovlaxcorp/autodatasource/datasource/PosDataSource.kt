package com.fovlaxcorp.autodatasource.datasource

import android.util.Log
import androidx.paging.PositionalDataSource
import com.fovlax.datasourcelibrary.datasource.LoadDataPos
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver
import io.reactivex.rxjava3.schedulers.Schedulers




class PosDataSource<T>(
        //функция которая подтягивает данные, можно передать, например из репозитория
    val loadDataPos: LoadDataPos<T>?
) : PositionalDataSource<T>() {


    //при вызове invalidate метод отработает в том потоке который его вызвал,
    // для упрощения использования я здесь сразу в другом потоке его запускаю
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
            Thread(){
            callback.onResult(
                    loadDataPos?.invoke(params.requestedStartPosition, params.requestedLoadSize)?:ArrayList<T>(),
                    params.requestedStartPosition
            )}.start()
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
            callback.onResult(
                loadDataPos?.invoke(params.startPosition,params.loadSize,)?:ArrayList<T>()
            )
    }
}