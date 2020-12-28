package com.example.pagerlistapp.amodels

import androidx.room.*

@Entity
data class RDataItem(
    var created: String? = null,
    @Ignore
    var episode: List<String?>? = null,
    var gender: String?= null,
    @PrimaryKey
    var id: Int?= null,
    var image: String?= null,
    @Embedded
    var location: Location?= null,
    var name: String?= null,
    @Embedded
    var origin: Origin?= null,
    var species: String?= null,
    var status: String?= null,
    var type: String?= null,
    var url: String?= null
)

