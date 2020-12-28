package com.example.pagerlistapp

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.getIntents
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.pagerlistapp.adapters.ImageAdapter
import org.junit.Test
import org.junit.runner.RunWith
// for assertions on Java 8 types (Streams and java.util.Optional)
import com.google.common.truth.Truth.assertThat
import org.hamcrest.Matcher
import org.junit.Rule

// for assertions on Java 8 types (Streams and java.util.Optional)

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class ExampleInstrumentedTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        // WHEN
        Thread.sleep(2000)

        onView(ViewMatchers.withId(R.id.recycler_view))
                .perform(
                        // scrollTo will fail the test if no item matches.
                        RecyclerViewActions.actionOnItemAtPosition<ImageAdapter.ImageHolder>(
                                0,
                                click()
                        )
                )
        // THEN

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