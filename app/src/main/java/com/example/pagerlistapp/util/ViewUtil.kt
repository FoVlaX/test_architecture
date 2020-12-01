package com.example.pagerlistapp.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.example.pagerlistapp.models.Media

import com.squareup.picasso.Picasso

fun ImageView.showMedia(media: Media, ratio: Media.MediaRatio, side: Media.MediaSide, size: Int, placeHolderColor: String = "#FFFFFF") {
    val uri = media.makeUrl(ratio, side, size)
    Picasso.get()
            .load(uri)
            .placeholder(ColorDrawable(Color.parseColor(placeHolderColor)))
            .into(this)
}