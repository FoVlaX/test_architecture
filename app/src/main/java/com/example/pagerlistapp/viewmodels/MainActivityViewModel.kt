package com.example.pagerlistapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.example.pagerlistapp.application.App
import com.example.pagerlistapp.models.Event
import com.example.pagerlistapp.models.Work
import com.example.pagerlistapp.repository.Repository
import javax.inject.Inject

class MainActivityViewModel(val state: SavedStateHandle) : ViewModel() {

    @Inject
    lateinit var repository: Repository

    val worksData: LiveData<PagedList<Work>>
    val eventsData: LiveData<PagedList<Event>>

    init{
        App.instance?.applicationComponent
            ?.repositoryComponent()
            ?.create()
            ?.inject(this)
        worksData = repository.livePagedLists["works"] as LiveData<PagedList<Work>>
        eventsData = repository.livePagedLists["events"] as LiveData<PagedList<Event>>
    }

    fun refreshWorks(){
        worksData.value?.dataSource?.invalidate()
    }

    fun refreshEvents(){
        eventsData.value?.dataSource?.invalidate()
    }

}