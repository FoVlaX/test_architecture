package com.example.pagerlistapp.simpledatasourcegenerator.annotations

@Target( AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class GenDataSource(
        val sourceName: String,
        val type: Type
) {
    enum class Type {
        Positional,
        ItemKeyedBefore,
        ItemKeyedAfter
    }
}
