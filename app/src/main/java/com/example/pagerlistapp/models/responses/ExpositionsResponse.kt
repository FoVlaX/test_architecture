package com.company.artist.model.response

import com.example.pagerlistapp.models.Media


data class ExpositionsResponse(
        val data: Data?
) {
    data class Data(
            val count: String?,
           // val expositions: List<Exposition>?,
            val media: List<Media>?
    )
}