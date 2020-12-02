package com.example.pagerlistapp.repository


import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.pagerlistapp.ArtistApiService
import com.example.pagerlistapp.application.App
import com.example.pagerlistapp.dao.AppDatabase
import com.example.pagerlistapp.datasource.Functions
import com.example.pagerlistapp.datasource.LoadDataPos
import com.example.pagerlistapp.datasource.PosDataSourceFactory
import com.example.pagerlistapp.models.Work
import javax.inject.Inject

class Repository @Inject constructor(
    val database: AppDatabase,
    val api: ArtistApiService
) : AbstractRepository(Repository::class) {


    //функция для которой нужен дата сурс
    @GenDataSource(sourceName = "works", type = GenDataSource.Type.Positional)
    private fun getWorks(offset: Int, count: Int) : List<Work?>?{
        refreshDao(offset,count)
        return loadWorksFromDao(offset, count)
    }

    //грузим данные из бд
    private fun loadWorksFromDao(offset: Int, count: Int) : List<Work?>?{
        return database.workDao()?.getWorks(offset,count)
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


}