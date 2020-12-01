package com.example.pagerlistapp.models


data class Exposition(
    val _extended: String,
    val address: String,
    val artist_ids: List<Int>,
    val ava_id: Int,
    val bg_id: String,
    val coords: Coords,
    val counters: Counters,
    val create_date: Int,
    val date_end: Int,
    val date_start: Int,
    val exposition_id: Int,
    val f_addr: String,
    val flags: Flags,
    val is_confirmed: String,
    val is_feed: String,
    val is_personal: String,
    val media_ids: List<Int>,
    val name: String,
    val op_times: List<Any>,
    val op_times_str: String,
    val partner_uris: List<Any>,
    val region_id: Long,
    val set_id: Int,
    val short: String,
    val uri: String,
    val uri_owner: String,
    val uri_place: String,
    val user_id: Int,
    var image: Media?
)

data class Coords(
    val lat: Double,
    val lon: Double
)

data class Counters(
    val comments: Int,
    val discus: Int,
    val likes: Int,
    val members: Int,
    val photo: Int,
    val review: Int,
    val video: Int,
    val visitors: Int,
    val work: Int
)

data class Flags(
    val is_can_comment: String,
    val is_can_like: String,
    val is_liked: String,
    val is_member: String
)