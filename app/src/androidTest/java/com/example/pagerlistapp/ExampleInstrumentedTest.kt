package com.example.pagerlistapp

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val result: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse("2018-04-01T08:00:00.000+05:00")
        Log.d("date",result.toString())
    }
}