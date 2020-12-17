package com.example.pagerlistapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pagerlistapp.ArtistApiService
import com.example.pagerlistapp.dao.AppDatabase
import com.example.pagerlistapp.models.Event
import com.example.pagerlistapp.models.Work
import java.util.*
import javax.inject.Inject


class Repository @Inject constructor(
    val database: AppDatabase?,
    val api: ArtistApiService?
) : IRepository {


    val currentWorksState: MutableLiveData<State> = MutableLiveData(State.Waiting())

    override fun getWorks(offset: Int, count: Int) : List<Work?>?{
        currentWorksState.postValue(State.Loading(offset = offset, count = count))
        refreshDao(offset,count)
        val data =  database?.workDao()?.getWorks(offset,count)

        if (data?.size != 0 && currentWorksState.value is State.NoConnection){
            currentWorksState.postValue(State.LoadedLocal(Date()))
        }
        return data;
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

                    val works = it?.data?.getArtWorks()!!
                    var pos = 0
                    for (work in works){
                        work.positionInDb = offset + pos
                        pos+=1
                    }

                    database?.workDao()?.insert(works)
                    currentWorksState.postValue(State.Loaded(Date()))
                },{
                    currentWorksState.postValue(State.NoConnection(Date()))
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


    sealed class State{

        data class Waiting(
            var date: Date? = null
        ): State()

        data class Loading(
            var offset: Int? = null,
            var count: Int? = null,
            var key: Int? = null
        ) : State()

        data class NoConnection(
            var date: Date? = null
        ): State()

        data class Loaded(
            var date: Date? = null
        ): State()

        data class LoadedLocal(
            var date: Date? = null
        ): State()
    }

}