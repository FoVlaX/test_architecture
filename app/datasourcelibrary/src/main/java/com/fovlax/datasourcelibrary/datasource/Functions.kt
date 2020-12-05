package com.fovlax.datasourcelibrary.datasource

/**
 * @param Int offset
 * @param Int count
 * */
//Функия для позиционной дата соурс
typealias LoadDataPos<T> = (Int, Int) -> List<T?>?

//if I set T? how null then i should return initial key in this function
typealias GetKeyFunction<K,T> = (T?) -> K
//Функия для айтем дата соурс
typealias LoadDataItem<K,T> = (K, Int) -> List<T?>?



//дата класс для функций
data class Functions (
    val loadDataPos: LoadDataPos<*>? = null,
    var loadDataItemBefore: LoadDataItem<*, *>? = null,
    var loadDataItemAfter: LoadDataItem<*, *>? = null,
    val getKeyFunction: GetKeyFunction<*, *>? = null
)