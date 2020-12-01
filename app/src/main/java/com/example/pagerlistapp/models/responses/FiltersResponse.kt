package com.example.pagerlistapp.models.responses


import com.example.pagerlistapp.models.Work

data class FiltersResponse(
        val data: Data?
) {

    data class Data(
        val count: String?,
        val works: List<Work>?,
          //  val filters: List<Filter>?
    ) {
       /* fun getFiltersForWorks(): List<Filter> {
            return filters ?: emptyList()
        }*/
    }
}