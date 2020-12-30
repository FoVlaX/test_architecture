package com.example.pagerlistapp.adapters

import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException
import javax.inject.Inject
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class AdaptersFactory @Inject constructor (val adapters: Set<  KClass<*> > ) : IAdapterFactory {

    override fun <T> create(adapterClass: KClass< out RecyclerView.Adapter<RecyclerView.ViewHolder> >): T{
        return if (adapters.contains(adapterClass)){
            adapterClass.createInstance() as T
        }else{
            throw IllegalArgumentException("$adapterClass not found")
        }
    }

}