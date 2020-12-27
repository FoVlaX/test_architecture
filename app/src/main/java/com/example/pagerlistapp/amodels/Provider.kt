package com.example.pagerlistapp.amodels

import androidx.room.ColumnInfo

data class Provider(
    @ColumnInfo(name = "nameProvider")
    val name: String
)