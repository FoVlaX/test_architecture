package com.example.pagerlistapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.pagerlistapp.application.App
import com.example.pagerlistapp.models.Work
import com.example.pagerlistapp.repository.Repository
import javax.inject.Inject

class MainActivityViewModel(val state: SavedStateHandle) : ViewModel() {

    @Inject
    lateinit var repository: Repository

    lateinit var worksData: LiveData<PagedList<Work>>

    init{
        App.instance?.applicationComponent
            ?.repositoryComponent()
            ?.create()
            ?.inject(this)
        worksData = repository.livePagedLists["works"] as LiveData<PagedList<Work>>
    }

}