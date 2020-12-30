package com.example.pagerlistapp.adapters

import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

interface IAdapterFactory {
    fun <T> create(adapterClass: KClass<out RecyclerView.Adapter<RecyclerView.ViewHolder>>): T
}