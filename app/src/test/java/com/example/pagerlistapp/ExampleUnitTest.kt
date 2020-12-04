package com.example.pagerlistapp


import android.icu.text.SimpleDateFormat
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val result: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse("2018-04-01T08:00:00.000+05:00")
        println(result)
    }
}