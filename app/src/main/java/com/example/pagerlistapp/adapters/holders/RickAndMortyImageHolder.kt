package com.example.pagerlistapp.adapters.holders

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.pagerlistapp.R
import com.example.pagerlistapp.amodels.UIRockAndMortyModel
import com.squareup.picasso.Picasso

class RickAndMortyImageHolder(view: View): BaseViewHolder<UIRockAndMortyModel.ImageDataItem>(view) {
    val imageView: ImageView = view.findViewById(R.id.character_id)
    val name: TextView = view.findViewById(R.id.text_name)
    val status: TextView = view.findViewById(R.id.status)
    val location: TextView = view.findViewById(R.id.last_location)
    @SuppressLint("SetTextI18n")
    override fun onBind(item: UIRockAndMortyModel.ImageDataItem) {
        val placeHolderColor = "#2233FF"
        val img_url = item.image?.substringBefore("?")
        Picasso.get()
            .load(img_url)
            .placeholder(ColorDrawable(Color.parseColor(placeHolderColor)))
            .error(R.drawable.motifation)
            .into(imageView)
        name.text = item.name
        status.text = "${item.status} - ${item.gender}"
        location.text = item.location?.name
    }
}