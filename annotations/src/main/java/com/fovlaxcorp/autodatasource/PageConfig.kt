package com.fovlaxcorp.autodatasource



@Target( AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class PageConfig(
        val pageSize: Int,
        val prefetchDistance : Int = -1,
        val enablePlaceholders: Boolean = false,
        val initialLoadSizeHint : Int,
        val maxSize: Int = Int.MAX_VALUE
)



