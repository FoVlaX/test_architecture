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
    @get:Rule var instantExecutorRule = InstantTaskExecutorRule()
    private val repository = Repository(null, null)
    private val viewModel = MainActivityViewModel(SavedStateHandle(), repository)
    private val initialLoadSize = 16;

    @Test
    fun `test on not null paged list`(){
        val value =  viewModel.worksData.getOrAwaitValue()
        Assert.assertNotNull(value)
    }

    @Test
    fun `assert initial items`(){
        val expected = repository.getWorks(0,initialLoadSize)
        val actual =  viewModel.worksData.getOrAwaitValue()
        actual?.awaitChanging()
        Assert.assertEquals(expected, actual)
    }


    @Test
    fun `assert initial load count`() {
        val actual =  viewModel.worksData.getOrAwaitValue()
        actual?.awaitChanging()
        Assert.assertEquals(initialLoadSize, actual?.size)
    }

    @Test
    fun `assert state viewModel`(){
        viewModel.worksData.getOrAwaitValue(50)
        val actual =  viewModel.worksData.getOrAwaitValue()
        actual?.awaitChanging()
        Assert.assertTrue(viewModel.worksState.value is State.Loaded)
    }

}