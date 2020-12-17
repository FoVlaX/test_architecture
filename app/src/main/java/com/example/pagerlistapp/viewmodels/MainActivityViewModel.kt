package com.example.pagerlistapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.pagerlistapp.models.Event
import com.example.pagerlistapp.models.Work
import com.example.pagerlistapp.repository.IRepository
import com.example.pagerlistapp.repository.IRepository_Impl
import com.example.pagerlistapp.repository.State

class MainActivityViewModel(val state: SavedStateHandle, var repository: IRepository) : ViewModel() {


    val worksData: LiveData<PagedList<Work>>
    val eventsData: LiveData<PagedList<Event>>

    val worksState: LiveData<State> = repository.getWorksState()

    init{
        worksData = IRepository_Impl(repository).worksLivePagedList(0)
        eventsData = IRepository_Impl(repository).eventsLivePagedList( 0)
    }

    fun refreshWorks(){
        worksData.value?.dataSource?.invalidate()
    }

    fun refreshEvents(){
        eventsData.value?.dataSource?.invalidate()
    }

}