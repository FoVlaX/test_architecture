package com.example.pagerlistapp.models.responses

data class InfoResponse(
        val data: Data?
) {

    data class Data(
            val text: String?
    )
}