package com.example.pagerlistapp.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagerlistapp.R
import com.example.pagerlistapp.amodels.Value
import com.squareup.picasso.Picasso

class ImageAdapter : PagedListAdapter<Value, RecyclerView.ViewHolder>(DIFF_CALLBACK) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // только в этом методе необходимо заполнять холдеры нигде больше!!!
        if (holder is ImageHolder) {
            val value = getItem(position)

            val placeHolderColor: String = "#2233FF"
            if (value!=null) {
                val img_url = value?.url?.substringBefore("?")

                Picasso.get()
                    .load(img_url)
                    .placeholder(ColorDrawable(Color.parseColor(placeHolderColor)))
                    .error(R.drawable.motifation)
                    .into(holder.imageView)

                holder.textView.text = value?.title ?: ""
            }else{

                //holder.imageView.background = holder.imageView.context.getDrawable(R.drawable.ic_launcher_foreground)
                //holder.textView.text = "loading..."

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.view_work_item
    }

    class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_view)
        val textView: TextView = view.findViewById(R.id.text_image)
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Value> =
            object : DiffUtil.ItemCallback<Value>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                override fun areItemsTheSame(oldWork: Value, newWork: Value): Boolean {
                    return oldWork.numberCount == newWork.numberCount
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldWork: Value,
                    newWork: Value
                ): Boolean {
                    return oldWork == newWork
                }
            }
    }
}