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

class MainActivityViewModel(val state: SavedStateHandle, private val repository: IRepository) : ViewModel() {


    val worksData: LiveData<PagedList<Work>> = IRepository_Impl(repository).worksLivePagedList(0)
    val eventsData: LiveData<PagedList<Event>> = IRepository_Impl(repository).eventsLivePagedList( 0)

    val worksState: LiveData<State> = repository.getWorksState()

    fun refreshWorks(){
        worksData.value?.dataSource?.invalidate()
    }

    fun refreshEvents(){
        eventsData.value?.dataSource?.invalidate()
    }

}