package com.example.pagerlistapp.amodels

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Value(
    val base64Encoding: Int?= null,
    val height: Int?= null,
    val imageWebSearchUrl: String?= null,
    val name: String?= null,
    @Embedded
    val provider: Provider?= null,
    val thumbnail: String?= null,
    val thumbnailHeight: Int?= null,
    val thumbnailWidth: Int?= null,
    val title: String?= null,
    val url: String?= null,
    val webpageUrl: String? = null,
    val width: Int? = null,
    @PrimaryKey
    var numberCount: Int? = 1,
    var nameValue: String? = null
)