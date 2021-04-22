package com.example.pagerlistapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pagerlistapp.ImageApiService
import com.example.pagerlistapp.RickAndMortyApiService
import com.example.pagerlistapp.amodels.UIRockAndMortyModel
import com.example.pagerlistapp.amodels.Value
import com.example.pagerlistapp.dao.AppDatabase
import javax.inject.Inject


class DateRepository @Inject constructor(
    val database: AppDatabase?,
    val apiImage: ImageApiService,
    val rickAndMortyApiService: RickAndMortyApiService
) : Repository {


    private var query: String? = "cat"

    private val currentWorksState: MutableLiveData<State> = MutableLiveData(State.Waiting())

    override fun setQuery(query: String?){
        this.query = query
    }

    private val status: MutableLiveData<State> = MutableLiveData(State.Loading())

   override fun getStatus() : LiveData<State>{
        return status
    }

    override fun getImages(offset: Int, count: Int): List<Value?>? {
        status.postValue(State.Loading())
        val totalCount = refreshImagesInDb(offset, count)
        val data = database?.valueDao()?.getForName(query!!, offset,count)
        if (data?.size?:0 == 0){
            status.postValue(State.Loaded())
        }
        return data?:List(0){
            Value()
        }
    }

    override fun getCharacters(id: Int, count: Int): List<UIRockAndMortyModel?>? {
        status.postValue(State.Loading())
        refreshCharacters(id, count)
        val value = database?.rDataItemDao()?.getForName(id,count)
        if (value?.size?:0 == 0) {
            status.postValue(State.Loaded())
        }
        return value
    }

    override fun getKeyCharacter(rDataItem: UIRockAndMortyModel?): Int {
        return when (rDataItem) {
            is UIRockAndMortyModel.ImageDataItem -> rDataItem.id?:0
            else -> 0
        }
    }

    private fun refreshCharacters(id: Int, count: Int){

        var query = "$id"

        (id until id+count).forEach { id ->
            query +=",$id"
        }

        val data = rickAndMortyApiService.getCharacters(query)
        data?.subscribe({
            if (it!=null) {
                database?.rDataItemDao()?.insert(it)
            }
        },{

        })
    }

    private fun refreshImagesInDb(offset: Int, count: Int): Int{

        val pageNumber = offset / count + 1
        val data = apiImage.getImagesForName(query?:"ou",pageNumber, count)
        var valueList: List<Value?>? = null
        var totalCount: Int? = null
        data.subscribe({

            valueList = it.value

            (0 until valueList?.size!!).forEach {
                valueList?.get(it)?.numberCount = offset + it
                valueList?.get(it)?.nameValue = query
            }
            totalCount = it.totalCount
            database?.valueDao()?.insert(valueList!!)

        },{
            totalCount = database?.valueDao()?.getAll()?.size
        })
        return totalCount!!
    }

}