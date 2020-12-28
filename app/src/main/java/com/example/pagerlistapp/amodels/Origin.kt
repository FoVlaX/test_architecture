package com.example.pagerlistapp.amodels

import androidx.room.ColumnInfo

data class Origin(
    @ColumnInfo(name = "origin_name")
    var  name: String?,
    @ColumnInfo(name = "origin_url")
    var url: String?
)