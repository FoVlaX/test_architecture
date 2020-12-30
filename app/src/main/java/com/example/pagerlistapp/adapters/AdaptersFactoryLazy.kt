package com.example.pagerlistapp.adapters

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.RecyclerView
import com.example.pagerlistapp.decorations.RecyclerViewMargin
import kotlin.reflect.KClass

@MainThread
inline fun <reified A : RecyclerView.Adapter<RecyclerView.ViewHolder>> Fragment.createAdapters(
        noinline factoryProducer: (() -> IAdapterFactory)? = null
) = AdapterLazy(A::class, factoryProducer)


class AdapterLazy<A : RecyclerView.Adapter<RecyclerView.ViewHolder>> (
        private val adapterClass: KClass<A>,
        private val factoryProducer: (() -> IAdapterFactory)? = null
) : Lazy<A> {
    private var cached: A? = null

    override val value: A
        get() {
            val adapter = cached
            return adapter
                    ?:factoryProducer?.invoke()?.create<A>(adapterClass)!!.also{
                        cached = it
                    }
        }

    override fun isInitialized() = cached != null
}