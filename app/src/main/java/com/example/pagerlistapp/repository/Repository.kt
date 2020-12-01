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
) {


    init{
        //Задаем мапы для функций подгрузки данных для датасурсов, а также фабрик
        //для производства этих дата сурсов
        //которые реализованные в данном репозитории
        //таким образом у нас может быть любое количество, функций которые грузят какие либо данные из сети
        //и сохраняют их в бд например и для каждой из них будет сгенерен датасурс, а потом и LiveData<PagedList>
        //которые мы берем во вью модел и уже сетаем вв нужный адаптер
        val map: HashMap<String, Functions> = HashMap()
        val factoryMap = HashMap<String, Class<out Any>>()

        //Functions - функции которые могут быть использованы в дата сурсах
        //задавать только одну в зависимости от типа дата сурса
        map["works"] =  Functions(loadDataPos = { offset, count ->
            getWorks(offset, count)
        })
        factoryMap["works"] = PosDataSourceFactory::class.java

        App.instance?.applicationComponent
            ?.dataSourceComponent()
                //какие фабрики изспользуем
            ?.withClass(factoryMap)
                //какие функции используем для подкгрузки данных в датасоурсах
            ?.with (map)
            ?.build()
            ?.inject(this)
    }


    //здесь получаем PagedList's для функций, которые мы определяли выше
    //и дальше используем по назначению в ViewModel
    @Inject
    lateinit var worksData: HashMap<String, LiveData<out PagedList<out Any>>>

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