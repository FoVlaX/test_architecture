package com.example.annotations.simpledatasourcegenerator

@Target( AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class KeyItem(
        val name: String
)

