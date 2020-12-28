package com.example.pagerlistapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pagerlistapp.ImageApiService
import com.example.pagerlistapp.amodels.Value
import com.example.pagerlistapp.dao.AppDatabase
import javax.inject.Inject


class Repository @Inject constructor(
    val database: AppDatabase?,
    val apiImage: ImageApiService
) : IRepository {


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
        refreshImagesInDb(offset, count)
        val data = database?.valueDao()?.getForName(query!!, offset,count)
        if (data?.size?:0 == 0){
            status.postValue(State.Loaded())
        }
        return data?:List(0){
            Value()
        }
    }

    private fun refreshImagesInDb(offset: Int, count: Int){

        val pageNumber = offset / count + 1
        val data = apiImage.getImagesForName(query?:"ou",pageNumber, count)
        var valueList: List<Value?>? = null
        data.subscribe({

            valueList = it.value

            (0 until valueList?.size!!).forEach {
                valueList?.get(it)?.numberCount = offset + it
                valueList?.get(it)?.nameValue = query
            }

            database?.valueDao()?.insert(valueList!!)

        },{

        })

    }

}