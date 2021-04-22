package com.example.pagerlistapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagerlistapp.R
import com.example.pagerlistapp.adapters.holders.RickAndMortyImageHolder
import com.example.pagerlistapp.amodels.UIRockAndMortyModel


class RickAndMortyAdapter : PagedListAdapter<UIRockAndMortyModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType) {
            R.layout.item_character -> RickAndMortyImageHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
            )
            else -> RickAndMortyImageHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // только в этом методе необходимо заполнять холдеры нигде больше!!!
        when (val item = getItem(position)) {
            is UIRockAndMortyModel.ImageDataItem -> {
                (holder as RickAndMortyImageHolder).onBind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is UIRockAndMortyModel.ImageDataItem -> R.layout.item_character
            else -> R.layout.item_character
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<UIRockAndMortyModel> =
            object : DiffUtil.ItemCallback<UIRockAndMortyModel>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                override fun areItemsTheSame(oldItem: UIRockAndMortyModel, newItem: UIRockAndMortyModel): Boolean {
                    return oldItem == newItem
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: UIRockAndMortyModel,
                    newItem: UIRockAndMortyModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

}