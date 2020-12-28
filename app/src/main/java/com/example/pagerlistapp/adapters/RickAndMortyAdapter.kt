package com.example.pagerlistapp.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagerlistapp.R
import com.example.pagerlistapp.amodels.RDataItem
import com.squareup.picasso.Picasso

class RickAndMortyAdapter : PagedListAdapter<RDataItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // только в этом методе необходимо заполнять холдеры нигде больше!!!
        if (holder is ImageHolder) {
            val value = getItem(position)

            val placeHolderColor: String = "#2233FF"

            val img_url = value?.image?.substringBefore("?")

            Picasso.get()
                .load(img_url)
                .placeholder(ColorDrawable(Color.parseColor(placeHolderColor)))
                .error(R.drawable.motifation)
                .into(holder.imageView)

            holder.name.text = value?.name
            holder.status.text = "${value?.status} - ${value?.gender}"
            holder.location.text = value?.location?.name

        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.view_work_item
    }

    class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.character_id)
        val name: TextView = view.findViewById(R.id.text_name)
        val status: TextView = view.findViewById(R.id.status)
        val location: TextView = view.findViewById(R.id.last_location)
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<RDataItem> =
            object : DiffUtil.ItemCallback<RDataItem>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                override fun areItemsTheSame(oldWork: RDataItem, newWork: RDataItem): Boolean {
                    return oldWork.id == newWork.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldWork: RDataItem,
                    newWork: RDataItem
                ): Boolean {
                    return oldWork == newWork
                }
            }
    }

}