package com.fovlaxcorp.autodatasource

@Target( AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class KeyItem(
        val name: String
)

