package com.example.pagerlistapp

import androidx.annotation.VisibleForTesting
import androidx.paging.PagedList
import java.lang.Exception
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> PagedList<T>.awaitChanging(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
){
    val latch = CountDownLatch(1)

    addWeakCallback(this, object : PagedList.Callback(){
        override fun onChanged(position: Int, count: Int) {
            println("onChanged $position $count")
            latch.countDown()
        }

        override fun onInserted(position: Int, count: Int) {
            println("onInserted $position $count")
            latch.countDown()
        }

        override fun onRemoved(position: Int, count: Int) {
            println("onRemoved $position $count")
            latch.countDown()
        }

    })

    if (!latch.await(time, timeUnit)){
        throw Exception("PagedList has zero size")
    }

}