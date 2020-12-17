package com.example.pagerlistapp.repository

import com.example.pagerlistapp.ArtistApiService
import com.example.pagerlistapp.dao.AppDatabase
import com.example.pagerlistapp.models.Event
import com.example.pagerlistapp.models.Work
import javax.inject.Inject


class Repository @Inject constructor(
    val database: AppDatabase?,
    val api: ArtistApiService?
) : IRepository {

    override fun getWorks(offset: Int, count: Int) : List<Work?>?{
        refreshDao(offset,count)
        return database?.workDao()?.getWorks(offset,count)
    }


    override fun getNews(beforeId: Int?, count: Int) : List<Event?>?{
        refreshNews(beforeId!!,count);
        return database?.eventDao()?.getEvents(beforeId,count)
    }

    override fun getKeyForEvent(event: Event?): Int{
        return event?.ids?.get(0)?:0
    }

    //загружаем данные записываем их в бд
    private fun refreshDao(offset: Int, count: Int){
        api?.getWorks(offset = offset, count = count)
                ?.subscribe({
                    database?.workDao()?.insert(it?.data?.getArtWorks()!!)
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
            database?.eventDao()?.insert(it?.data?.getNewsEvents()!!)
        },{

        })
    }

}