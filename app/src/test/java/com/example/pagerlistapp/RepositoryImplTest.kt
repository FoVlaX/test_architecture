package com.example.pagerlistapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pagerlistapp.repository.IRepository_Impl
import com.example.pagerlistapp.repository.Repository
import com.example.pagerlistapp.viewmodels.MainActivityViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [23], shadows = [RepositoryContext::class], manifest = Config.NONE)
class RepositoryImplTest {

    private val repository = Repository(null,null)
    private val viewModel = MainActivityViewModel(SavedStateHandle(), repository)
    @Test
    fun test(){
        viewModel.worksData.observeForever {
            Assert.assertEquals(it.size,0)
        }
    }

}