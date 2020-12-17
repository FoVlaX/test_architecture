package com.example.pagerlistapp

import org.junit.*


class ExampleTestClass {

    companion object {

        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            println("Run beforeClass")
        }

        @JvmStatic
        @AfterClass
        fun afterClass() {
            println("Run afterClass")
        }

        val staticVariable = println("init staticVariable")
    }



    init {
        kotlin.run { staticVariable }
        println("init ExperimentTest")
    }

    @Before
    fun beforeTest() {
        println("Run beforeTest")
    }

    @After
    fun afterTest() {
        println("Run afterTest")
    }

    @get:Rule
    val rule = CustomTestRule(false)

    @Test
    fun myTest() {
        println("Run myTest")
    }

    @Test
    fun my2Test() {
        println("Run my2Test")
    }
}


