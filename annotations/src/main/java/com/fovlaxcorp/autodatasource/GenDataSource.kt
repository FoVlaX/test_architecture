package com.fovlaxcorp.autodatasource

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
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
