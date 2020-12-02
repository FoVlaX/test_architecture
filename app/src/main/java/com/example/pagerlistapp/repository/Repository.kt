package com.example.pagerlistapp.repository


import com.example.pagerlistapp.ArtistApiService
import com.example.pagerlistapp.dao.AppDatabase
import com.example.pagerlistapp.models.Event
import com.example.pagerlistapp.models.Work
import com.example.pagerlistapp.simpledatasourcegenerator.AbstractDataSourceRepository
import com.example.pagerlistapp.simpledatasourcegenerator.annotations.GenDataSource
import com.example.pagerlistapp.simpledatasourcegenerator.annotations.KeyItem
import javax.inject.Inject



class Repository @Inject constructor(
    val database: AppDatabase,
    val api: ArtistApiService
) : AbstractDataSourceRepository(Repository::class) {

    //функция для которой нужен позиционный дата сурс
    //              offset, count
    // должная быть (Int, Int): List<*>
    @GenDataSource(sourceName = "works", type = GenDataSource.Type.Positional)
    private fun getWorks(offset: Int, count: Int) : List<Work?>?{
        refreshDao(offset,count)
        return database.workDao()?.getWorks(offset,count)
    }
    //функция для которой нужен ItemKeyed дата сурс
    //              afterKey, count
    // должная быть (*, Int): List<*>
    @GenDataSource(sourceName = "events", type = GenDataSource.Type.ItemKeyedAfter)
    private fun getNews(beforeId: Int, count: Int) : List<Event?>?{
        refreshNews(beforeId,count);
        return database.eventDao()?.getEvents(beforeId,count)
    }

    // Если в запросе надо передавать более этих двух необходимых параметров
    // например какие либо фильтры
    // можно сделать их в виде параметров и использовать в функциях загрузки из интернета
    // и передавать в запросе к дао
    // т.е. например установили какие либо фильтры вызвали у PagedList invalidate и он запустить
    // помеченную аннотацией функцию, которая уже сделает запрос с новыми доп параметрами

    //функция для получения ключей в ItemKeyedDataSource
    @KeyItem(name = "events")
    private fun getKeyForEvent(event: Event?): Int{
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