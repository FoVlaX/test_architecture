package com.example.pagerlistapp

import androidx.lifecycle.SavedStateHandle
import com.example.pagerlistapp.models.Work
import com.example.pagerlistapp.repository.Repository
import com.example.pagerlistapp.viewmodels.MainActivityViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

// for assertions on Java 8 types (Streams and java.util.Optional)
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
// for assertions on Java 8 types (Streams and java.util.Optional)
import com.google.common.truth.Truth8.assertThat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23], shadows = [RepositoryContext::class], manifest = Config.NONE)
class MainViewModelTest {


    private val listWorks = List(20){
        val work = Work()
        work.work_id = it
        work
    }
    private val handle: SavedStateHandle = mock(SavedStateHandle::class.java)
    private val repository = mock(Repository::class.java)
    private val repository_2 = Repository(null, null)
    val viewModel = MainActivityViewModel(handle, repository)

    @Before
    fun `mocked repository load data methods`(){
        `when`(repository.getWorks(0, 20)).thenReturn(listWorks)

    }

    @Test
    fun `test repository getWorks 1`() {

        Assert.assertEquals(repository.getWorks(0, 20), listWorks)
    }

    @Test
    fun `test repository getWorks 2`() {
        val work = Work()
        work.work_id = 19
        assertThat(repository_2.getWorks(0, 20)).contains(work)
        assertThat(repository_2.getWorks(0, 20)).isEqualTo(listWorks)
        val test_string = "abra kadabra"

        assertThat(test_string).contains("ra")
    }


}