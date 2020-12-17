package com.example.pagerlistapp

import android.content.Context
import com.example.pagerlistapp.models.Work
import com.example.pagerlistapp.repository.Repository
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.shadows.ShadowContextImpl

@Implements(Repository::class)
class RepositoryContext {
    fun getWorks(offset: Int, count: Int): List<Work?>?{
        return List(count){
            val work = Work()
            work.work_id = it + offset
            work.name = "test names ${it + offset}"
            work
        }
    }
}