package com.example.pagerlistapp.models

import androidx.room.Embedded
import androidx.room.Ignore

data class Data(
        var version: String? = null,
        var version_big: String? = null,
        var version_orig: String? = null,
        @Embedded
        var sizes: Media.Size? = null,
        var x: String? = null,
        var y: String? = null,
        var ratio: String? = null,
        var ext: String? = null,
        var is_animated: String? = null
)