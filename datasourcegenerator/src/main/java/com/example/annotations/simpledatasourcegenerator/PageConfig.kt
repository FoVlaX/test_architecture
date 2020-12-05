package com.example.annotations.simpledatasourcegenerator



@Target( AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class PageConfig(
        val pageSize: Int,
        val prefetchDistance : Int = -1,
        val enablePlaceholders: Boolean = true,
        val initialLoadSizeHint : Int,
        val maxSize: Int = Int.MAX_VALUE
)



