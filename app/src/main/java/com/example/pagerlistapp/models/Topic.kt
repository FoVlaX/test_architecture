package com.example.pagerlistapp.models

import com.example.pagerlistapp.models.Media

data class Topic (
    val id: Int?,
    val uri: String?,
    val uris: List<String?>,
    var media: List<Media>?,
)