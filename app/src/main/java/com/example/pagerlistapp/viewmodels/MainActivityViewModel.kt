package com.example.pagerlistapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.pagerlistapp.amodels.RDataItem
import com.example.pagerlistapp.amodels.Value
import com.example.pagerlistapp.repository.IRepository
import com.example.pagerlistapp.repository.IRepository_Impl
import com.example.pagerlistapp.repository.State

class MainActivityViewModel(val state: SavedStateHandle, private val repository: IRepository) : ViewModel() {

    var imageData: LiveData<PagedList<Value>> = IRepository_Impl(repository).imagesLivePagedList(0)

    var rockAndMortyData: LiveData<PagedList<RDataItem>> = IRepository_Impl(repository).charactersLivePagedList(0)

    val status = repository.getStatus()

    fun setQuery(query: String?){
        //При задании нового запроса пересоздаю PagedList т.к. собираюсь загрузить совершенно другие данные
        //и обычный invalidate здесь не подходит т.к. invalidate предназначет чтобы обновить текущие
        //и например при вызове invalidate когда список прокручен до центра если придут
        //пустые данные получим соответсвующий экзепшн от datasource
        //java.lang.IllegalArgumentException: Initial result cannot be empty if items are present in data set.
        repository.setQuery(query)
        imageData = IRepository_Impl(repository).imagesLivePagedList(0)
    }

    fun refreshImages(){
        imageData.value?.dataSource?.invalidate()
    }

    fun refreshCharacters(){
        rockAndMortyData.value?.dataSource?.invalidate()
    }

}