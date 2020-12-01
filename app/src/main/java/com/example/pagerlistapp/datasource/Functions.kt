package com.example.pagerlistapp.datasource

/**
 * @param Int offset
 * @param Int count
 * */
//Функия для позиционной дата соурс
typealias LoadDataPos<T> = (Int, Int) -> List<T?>?

//Функия для айтем дата соурс
typealias LoadDataItem<K,T> = (K) -> List<T?>?

//дата класс для функций
data class Functions (
        val loadDataPos: LoadDataPos<*>? = null,
        val loadDataItem: LoadDataItem<*,*>? = null
)