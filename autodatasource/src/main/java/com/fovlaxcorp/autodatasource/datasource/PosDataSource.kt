package com.fovlaxcorp.autodatasource.datasource

import android.util.Log
import androidx.paging.PositionalDataSource
import com.fovlax.datasourcelibrary.datasource.LoadDataPos
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception


class PosDataSource<T>(
        //функция которая подтягивает данные, можно передать, например из репозитория
    val loadDataPos: LoadDataPos<T>?
) : PositionalDataSource<T>() {


    //при вызове invalidate метод отработает в том потоке который его вызвал,
    // для упрощения использования я здесь сразу в другом потоке его запускаю
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
            Thread(){
                if (!params.placeholdersEnabled) {
                    callback.onResult(
                        loadDataPos?.invoke(params.requestedStartPosition, params.requestedLoadSize)?.first
                            ?: ArrayList<T>(),
                        params.requestedStartPosition
                    )
                }else{
                    val data = loadDataPos?.invoke(params.requestedStartPosition, params.requestedLoadSize)
                    val totalCount = if (data?.second?:0 == 0) {
                        params.requestedLoadSize
                    }else{
                        data?.second
                    }
                    callback.onResult(
                        data?.first
                            ?: ArrayList<T>(),
                        params.requestedStartPosition,
                        totalCount!!
                    )
                }
            }.start()
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
            callback.onResult(
                loadDataPos?.invoke(params.startPosition,params.loadSize,)?.first?:ArrayList<T>()
            )
    }
}