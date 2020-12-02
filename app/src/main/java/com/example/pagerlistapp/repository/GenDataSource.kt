package com.example.pagerlistapp.repository

@Target( AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class GenDataSource(
        val sourceName: String,
        val type: Type
) {
    enum class Type {
        Positional,
        Item
    }
}
