package com.example.pagerlistapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.DataSource
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
        worksData = repository.getNewLivePagedList("works",100 )
        eventsData = repository.getNewLivePagedList("events", 0)
    }

    fun refreshWorks(){
        worksData.value?.dataSource?.invalidate()
        worksData.value?.dataSource.apply {

        }
    }

    fun refreshEvents(){
        eventsData.value?.dataSource?.invalidate()
        eventsData.value;
    }

}