package com.example.pagerlistapp.models.typeconverters

import androidx.room.TypeConverter

class IdsConverter {
    @TypeConverter
    fun fromIds(ids: List<Int?>?): Int {
        return ids?.get(0)?:0
    }

    @TypeConverter
    fun toIds(data: Int): List<Int?>? {
        return List(1){
            data
        }
    }
}