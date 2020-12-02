package com.example.pagerlistapp.simpledatasourcegenerator.annotations

@Target( AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class KeyItem(
        val name: String
)

