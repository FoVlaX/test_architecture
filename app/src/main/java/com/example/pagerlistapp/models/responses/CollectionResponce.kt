package com.example.pagerlistapp.models.responses


import com.example.pagerlistapp.models.Media
import com.example.pagerlistapp.models.Set

data class CollectionResponce(
        val data: Data?
) {

    data class Data(
            val count: String?,
            val sets: List<Set>?,
            val media: List<Media>?
    )
}