package com.fovlax.datasourcelibrary.datasource

/**
 * @param Int offset
 * @param Int count
 * */
//Функия для позиционной дата соурс
typealias LoadDataPos<T> = (Int,  Int) ->  Pair<List< T?>?, Int>
//if I set T? how null then i should return initial key in this function
typealias GetKeyFunction<K,T> = (T?) -> @JvmSuppressWildcards  K
//Функия для айтем дата соурс
typealias LoadDataItem<K,T> = (K, Int) -> @JvmSuppressWildcards  List<T?>?



//дата класс для функций
data class Functions<K,T> (
    val loadDataPos: LoadDataPos<T>? = null,
    var loadDataItemBefore: LoadDataItem<K, T>? = null,
    var loadDataItemAfter: LoadDataItem<K, T>? = null,
    val getKeyFunction: GetKeyFunction<K, T>? = null
)