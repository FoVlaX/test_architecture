package com.example.pagerlistapp

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class CustomTestRule(val clazz: Boolean) : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement(){
            override fun evaluate() {
                    try {
                        val where = if (clazz)  "class" else  ""
                        println("$where rule before")
                        base?.evaluate()
                    } finally {
                        val where = if (clazz)  "class" else  ""
                        println("$where rule after")
                    }
                }
            }
        }
}