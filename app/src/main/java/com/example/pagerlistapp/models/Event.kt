package com.example.pagerlistapp.models

import androidx.room.*
import com.example.pagerlistapp.models.typeconverters.IdsConverter

@Entity
@TypeConverters(value = [IdsConverter::class])
data class Event(
    var action: String,
    var date: Int,
    @PrimaryKey
    var ids: List<Int?>?,
    var style: String,
    var text: String,
    var uri_author: String,
    var user_id: Int,
)

