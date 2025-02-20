package com.example.pagerlistapp

import androidx.lifecycle.MutableLiveData
import com.example.pagerlistapp.models.Work
import com.example.pagerlistapp.repository.DateRepository
import com.example.pagerlistapp.repository.State
import org.robolectric.annotation.Implements


@Implements(DateRepository::class)
class RepositoryContext {

    private val currentWorksState: MutableLiveData<State> = MutableLiveData(State.Waiting())

    fun getWorks(offset: Int, count: Int): List<Work?>?{
        currentWorksState.postValue(State.Loaded())
        return List(10){
            val work = Work()
            work.work_id = it + offset
            work.name = "test names ${it + offset}"
            work
        }
    }

    fun <T> getWorksState(): T {
        return  currentWorksState as T
    }

}