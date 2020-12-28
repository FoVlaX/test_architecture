package com.example.pagerlistapp

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.getIntents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pagerlistapp.adapters.ImageAdapter
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
// for assertions on Java 8 types (Streams and java.util.Optional)
import com.google.common.truth.Truth.assertThat
import org.hamcrest.Matcher
import org.junit.Before


@RunWith(AndroidJUnit4::class)
@Config(sdk = [23], shadows = [RepositoryContext::class], manifest = Config.NONE)
class EspressoTest  {

    @Test
    fun `check data test`() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(2000)
    }

    @Before
    fun setUp(){
        Intents.init()
    }

    @Test
    fun `espresso test`(){
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
            .perform(
                // scrollTo will fail the test if no item matches.
                RecyclerViewActions.actionOnItemAtPosition<ImageAdapter.ImageHolder>(
                    0,
                    customAction(R.id.item)
                )
            )
        assertThat( getIntents().last().component?.className).isEqualTo(InfoActivity::class.java.name)
    }

    fun customAction(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Action Description"
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id)
                v?.performClick()
            }
        }
    }

}