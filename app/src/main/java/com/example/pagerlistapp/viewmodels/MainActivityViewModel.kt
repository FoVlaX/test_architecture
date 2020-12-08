package com.example.pagerlistapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.pagerlistapp.application.App
import com.example.pagerlistapp.models.Event
import com.example.pagerlistapp.models.Work
import com.example.pagerlistapp.repository.Repository
import com.example.pagerlistapp.repository.Repository_Impl
import javax.inject.Inject

class MainActivityViewModel(val state: SavedStateHandle, var repository: Repository) : ViewModel() {


   val worksData: LiveData<PagedList<Work>>
   val eventsData: LiveData<PagedList<Event>>

    init{
        worksData = Repository_Impl(repository).worksLivePagedList(0)
        eventsData = Repository_Impl(repository).eventsLivePagedList( 0)
    }

    fun refreshWorks(){
        worksData.value?.dataSource?.invalidate()
    }

    fun refreshEvents(){
        eventsData.value?.dataSource?.invalidate()
    }

}