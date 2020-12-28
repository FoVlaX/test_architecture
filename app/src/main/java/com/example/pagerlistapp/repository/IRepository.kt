package com.example.pagerlistapp.repository

import androidx.lifecycle.LiveData
import com.example.pagerlistapp.amodels.Value
import com.fovlaxcorp.autodatasource.GenDataSource.Type
import com.fovlaxcorp.autodatasource.GenDataSource
import com.fovlaxcorp.autodatasource.PageConfig
import com.fovlaxcorp.autodatasource.WithDataSource

@WithDataSource
interface IRepository {

    companion object{
        const val WORKS = "works"
        const val EVENTS = "events"
        const val IMAGE = "images"
    }


    @PageConfig(initialLoadSizeHint = 10,
                pageSize = 10,
                enablePlaceholders = false)
    @GenDataSource(sourceName = IMAGE, type = Type.Positional)
    fun getImages(offset: Int, count: Int): List<Value?>?

    fun setQuery(query: String?)

    fun getStatus() : LiveData<State>

}

