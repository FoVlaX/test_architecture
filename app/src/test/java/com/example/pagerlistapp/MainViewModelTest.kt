package com.example.pagerlistapp


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pagerlistapp.repository.Repository
import com.example.pagerlistapp.repository.State
import com.example.pagerlistapp.viewmodels.MainActivityViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [23], shadows = [RepositoryContext::class], manifest = Config.NONE)
class MainViewModelTest {


    //Rule for LiveData to make postValue work correctly
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private val repository = Repository(null, null)
    private val viewModel = MainActivityViewModel(SavedStateHandle(), repository)

    @Test
    fun `test on initial load count`(){
        //wait when LivePagedListBuilder post paged list,
        // at first it will be an empty paged list
        waitPagedListInit()
        //loading... wait data
        Thread.sleep(2000)
        //assert initial load count
        Assert.assertEquals(viewModel.worksData.value?.size , 16)
    }

    @Test
    fun `assert initial items`(){
        //wait when LivePagedListBuilder post paged list,
        // at first it will be an empty paged list
        waitPagedListInit()
        //loading... wait data
        Thread.sleep(2000)
        //assert value
        Assert.assertEquals(viewModel.worksData.value, repository.getWorks(0,16))
    }

    @Test
    fun `assert state viewModel`(){
        Assert.assertTrue(viewModel.worksState.value is State.Waiting)
        waitPagedListInit()
        //loading... wait data
        Thread.sleep(2000)
        //assert state
        Assert.assertTrue(viewModel.worksState.value is State.Loaded)
    }

    private fun waitPagedListInit(){
        val syncObject = Object()
        viewModel.worksData.observeForever {
            synchronized(syncObject) {
                syncObject.notify()
            }
        }
        synchronized(syncObject) {
            syncObject.wait()
        }
    }

}