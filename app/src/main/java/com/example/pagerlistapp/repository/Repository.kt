package com.example.pagerlistapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.annotations.simpledatasourcegenerator.GenDataSource
import com.example.annotations.simpledatasourcegenerator.KeyItem
import com.example.annotations.simpledatasourcegenerator.PageConfig
import com.example.annotations.simpledatasourcegenerator.WithDataSource
import com.example.pagerlistapp.ArtistApiService
import com.example.pagerlistapp.dao.AppDatabase
import com.example.pagerlistapp.models.Event
import com.example.pagerlistapp.models.Work
import com.fovlax.datasourcelibrary.SimpleDataSourceGenerator
import com.fovlax.datasourcelibrary.datasource.Functions
import javax.inject.Inject

@WithDataSource
class Repository @Inject constructor(
    val database: AppDatabase,
    val api: ArtistApiService
) {

    companion object{
        const val WORKS = "works"
        const val EVENTS = "events"
    }
    //функция для которой нужен позиционный дата сурс
    //              offset, count
    // должная быть (Int, Int): List<*>
    @PageConfig(initialLoadSizeHint = 4, pageSize = 2, enablePlaceholders = false)
    @GenDataSource(sourceName = WORKS, type = com.example.annotations.simpledatasourcegenerator.GenDataSource.Type.Positional)
    fun getWorks(offset: Int, count: Int) : List<Work?>?{
        refreshDao(offset,count)
        return database.workDao()?.getWorks(offset,count)
    }
    //функция для которой нужен ItemKeyed дата сурс
    //              afterKey, count
    // должная быть (*, Int): List<*>
    @PageConfig(initialLoadSizeHint = 2
    ,pageSize = 4
    ,enablePlaceholders = false)
    @GenDataSource(sourceName = EVENTS, type = com.example.annotations.simpledatasourcegenerator.GenDataSource.Type.ItemKeyedAfter)
    fun getNews(beforeId: Int?, count: Int) : List<Event?>?{
        refreshNews(beforeId!!,count);
        return database.eventDao()?.getEvents(beforeId,count)
    }


    //функция для получения ключей в ItemKeyedDataSource
    @KeyItem(name = EVENTS)
    fun getKeyForEvent(event: Event?): Int{
        return event?.ids?.get(0)?:0
    }

    //загружаем данные записываем их в бд
    private fun refreshDao(offset: Int, count: Int){
        api.getWorks(offset = offset, count = count)
                .subscribe({
                    database.workDao()?.insert(it?.data?.getArtWorks()!!)
                },{
                    //делает что либо если произошла ошибка,
                    //например устанавливаем состояние отсутствие подключения к сети и т.д.
                })
    }

    private fun refreshNews(beforeEventId: Int,
                count: Int) {
        ArtistApiService.create().getArtistNews(beforeEventId = beforeEventId,
                count = count
        ).subscribe({
            database.eventDao()?.insert(it?.data?.getNewsEvents()!!)
        },{

        })
    }

}