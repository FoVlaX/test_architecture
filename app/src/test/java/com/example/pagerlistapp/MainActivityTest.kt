package com.example.pagerlistapp

// for assertions on Java 8 types (Streams and java.util.Optional)

import android.content.Intent
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.pagerlistapp.adapters.WorksAdapter
import com.example.pagerlistapp.fragments.PosFragment

import com.google.common.truth.Truth.assertThat

import kotlinx.coroutines.awaitAll
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper



@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23], manifest = Config.NONE)
class MainActivityTest {

    private lateinit var activity: MainActivity


    @Before
    fun setUp(){
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .get()

        Robolectric.flushBackgroundThreadScheduler()
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()
    }

    @Test
    fun `activity not null`(){
        assertThat(activity).isNotNull()
    }

    @Test
    fun shouldHaveFragment() {
        assertThat(activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                ?.childFragmentManager?.findFragmentById(R.id.nav_host_fragment)).isNotNull()
    }

    @Test
    fun shouldFragmentIsPosOnStart() {
        assertThat(activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                ?.childFragmentManager?.findFragmentById(R.id.nav_host_fragment) is PosFragment).isEqualTo(true)
    }

    @Test
    fun `test recycler view`() {

        val posFragment = activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
          ?.childFragmentManager?.findFragmentById(R.id.nav_host_fragment) as PosFragment
        val recyclerView = posFragment.recyclerView.findViewHolderForItemId(0)
        val intent = Shadows.shadowOf(activity).peekNextStartedActivity() as Intent?
        assertThat(InfoActivity::class.java.canonicalName).isEqualTo(intent?.component!!.className)
    }

    @Test
    fun `espresso test`(){
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.bottom_menu))
            .perform(click())

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
            .perform(
                // scrollTo will fail the test if no item matches.
                RecyclerViewActions.actionOnItemAtPosition<WorksAdapter.WorkHolder>(
                    0,
                    click()
                )
            )

    }

}