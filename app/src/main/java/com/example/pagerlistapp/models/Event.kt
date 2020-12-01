package com.example.pagerlistapp.models

data class Event(
    val action: String,
    val attaches: List<Attache>,
    val channel_ids: List<Int>,
    val comment: Comment,
    val date: Int,
    val ids: List<Int>,
    val is_recommendation: String,
    val is_sended: String,
    val is_show_author: String,
    val like: Like,
    val mentions: Any,
    val style: String,
    val text: String,
    val uri_author: String,
    val user_id: Int,
    var content: List<Any>
)

data class Attache(
    val media_id: Int,
    val title: String,
    val uri: String
)

data class Comment(
    val can_comment: String,
    val count: Int,
    val count_new: Int,
    val uri: String
)

data class Like(
    val can_like: String,
    val count: Int,
    val liked: String,
    val uri: String
)