package com.example.pagerlistapp


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pagerlistapp.models.Event
import com.example.pagerlistapp.models.Work
import com.example.pagerlistapp.repository.IRepository_Impl
import com.example.pagerlistapp.repository.DateRepository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [23], shadows = [RepositoryContext::class], manifest = Config.NONE)
class MainViewModelTest {

    //Rule for LiveData to make postValue work correctly
    @get:Rule var instantExecutorRule = InstantTaskExecutorRule()
    private val repository = DateRepository(null, null)


    @Test
    fun `boundary callback test`(){

        val boundaryCallback = object : PagedList.BoundaryCallback<Work>(){
            override fun onItemAtEndLoaded(itemAtEnd: Work) {
                println("asd")
            }

            override fun onItemAtFrontLoaded(itemAtFront: Work) {
                println("asdad")
            }

            override fun onZeroItemsLoaded() {
                println("zero")
            }
        }

        val boundaryCallback2 = object : PagedList.BoundaryCallback<Event>(){
            override fun onItemAtEndLoaded(itemAtEnd: Event) {
                println("asd")
            }

            override fun onItemAtFrontLoaded(itemAtFront: Event) {
                println("asdad")
            }

            override fun onZeroItemsLoaded() {
                println("zero")
            }
        }

        val invalidatedCallback = object : DataSource.InvalidatedCallback{
            override fun onInvalidated() {
                println("asdasdad")
            }

        }

        val liveData = IRepository_Impl(repository).worksLivePagedList(0, boundaryCallback, invalidatedCallback)
        val eventData = IRepository_Impl(repository).eventsLivePagedList(0, boundaryCallback2, invalidatedCallback)
        eventData?.getOrAwaitValue ()
        eventData.value?.dataSource?.invalidate()
        liveData?.getOrAwaitValue()

    }



}