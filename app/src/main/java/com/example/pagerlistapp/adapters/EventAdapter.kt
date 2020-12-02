package com.example.pagerlistapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagerlistapp.R
import com.example.pagerlistapp.models.Event


class EventAdapter : PagedListAdapter<Event, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_work_item, parent, false)
        return EventHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // только в этом методе необходимо заполнять холдеры нигде больше!!!
        if (holder is EventHolder) {
            val item = getItem(position)
            if (item != null) {
                holder.name.text = item.text
                holder.count_likes.text = item.ids.toString()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.view_work_item
    }

    class EventHolder(view: View) : RecyclerView.ViewHolder(view) {
        var item: View
        var image_view: AppCompatImageView
        var name: TextView
        var count_likes: TextView
        var image_size: Int

        init {
            image_size = view.resources.getDimension(R.dimen.medium_image_size).toInt()
            item = view.findViewById(R.id.item)
            image_view = view.findViewById(R.id.work_image)
            name = view.findViewById(R.id.work_name)
            count_likes = view.findViewById(R.id.work_count_likes)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Event> = object : DiffUtil.ItemCallback<Event>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldWork: Event, newWork: Event): Boolean {
                return oldWork.ids!! == newWork.ids
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldWork: Event,
                                            newWork: Event): Boolean {
                return oldWork == newWork
            }
        }
    }
}