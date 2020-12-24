package com.example.pagerlistapp

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.pagerlistapp.models.Work
import com.example.pagerlistapp.repository.Repository
import com.example.pagerlistapp.repository.State
import org.robolectric.annotation.Implements
import org.robolectric.annotation.RealObject


@Implements(Repository::class)
class RepositoryContext {

    private val currentWorksState: MutableLiveData<State> = MutableLiveData(State.Waiting())

    fun getWorks(offset: Int, count: Int): List<Work?>?{
        //currentWorksState.postValue(State.Loaded())
        return List(count){
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