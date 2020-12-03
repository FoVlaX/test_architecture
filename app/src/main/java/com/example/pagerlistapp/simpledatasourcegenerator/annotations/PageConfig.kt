package com.example.pagerlistapp.simpledatasourcegenerator.annotations



@Target( AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class PageConfig(
        val pageSize: Int,
        val prefetchDistance : Int = -1,
        val enablePlaceholders: Boolean = true,
        val initialLoadSizeHint : Int,
        val maxSize: Int = Int.MAX_VALUE
)



