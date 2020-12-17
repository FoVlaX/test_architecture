package com.example.pagerlistapp.repository

import com.fovlaxcorp.autodatasource.GenDataSource.Type
import com.fovlaxcorp.autodatasource.GenDataSource
import com.fovlaxcorp.autodatasource.KeyItem
import com.fovlaxcorp.autodatasource.PageConfig
import com.fovlaxcorp.autodatasource.WithDataSource
import com.example.pagerlistapp.models.Event
import com.example.pagerlistapp.models.Work

@WithDataSource
interface IRepository {

    companion object{
        const val WORKS = "works"
        const val EVENTS = "events"
    }

    @PageConfig(initialLoadSizeHint = 16,
            pageSize = 4,
            enablePlaceholders = false)
    @GenDataSource(sourceName = WORKS, type = Type.Positional)
    fun getWorks(offset: Int, count: Int) : List<Work?>?

    @PageConfig(initialLoadSizeHint = 2,
            pageSize = 4,
            enablePlaceholders = false)
    @GenDataSource(sourceName = EVENTS, type = Type.ItemKeyedAfter)
    fun getNews(beforeId: Int?, count: Int) : List<Event?>?

    @KeyItem(name = EVENTS)
    fun getKeyForEvent(event: Event?): Int?

    fun <T> getWorksState(): T


}

