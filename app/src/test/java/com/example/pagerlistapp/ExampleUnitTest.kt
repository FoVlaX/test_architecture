package com.example.pagerlistapp

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import com.example.pagerlistapp.fragments.PosFragment
import com.example.pagerlistapp.models.Event
import com.example.pagerlistapp.repository.Repository
import com.example.pagerlistapp.repository.Repository_Impl
import com.example.pagerlistapp.viewmodels.MainActivityViewModel
import com.example.pagerlistapp.viewmodels.ViewModelsFactory
import io.mockk.MockK
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class ExampleUnitTest {

    val handle: SavedStateHandle = Mockito.mock(SavedStateHandle::class.java)
    val repository: Repository = Mockito.mock(Repository::class.java)

    @Test
    fun `when get LiveData from repository`() {
        val viewModel = MainActivityViewModel(handle)
    }
}