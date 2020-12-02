package com.example.pagerlistapp.models

import androidx.room.ColumnInfo


data class Like(
        val can_like: String,
        @ColumnInfo(name = "count_like")
        val count: Int,
        val liked: String,
        @ColumnInfo(name = "uri_like")
        val uri: String
)