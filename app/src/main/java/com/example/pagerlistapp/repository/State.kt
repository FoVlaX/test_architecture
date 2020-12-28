package com.example.pagerlistapp.repository

import java.util.*

sealed class State {

    open var description: String? = ""

    data class Waiting(
        var date: Date? = null,
    ): State(){
        init{
            //вынести в res/values
            description = "Waiting...."
        }
    }

    data class Loading(
        var offset: Int? = null,
        var count: Int? = null,
        var key: Int? = null,
    ) : State(){
        init{
            description = "Loading $offset $count"
        }
    }

    data class NoConnection(
        var date: Date? = null,
    ): State(){
        init{
            description = "NoConnection"
        }
    }

    data class Loaded(
        var date: Date? = null,

        ): State(){
        init{
            description = "Load complete"
        }
    }

    data class LoadedLocal(
        var date: Date? = null,

        ): State(){
        init{
            description = "Local copy loaded"
        }
    }
}