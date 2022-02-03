package com.vnybst.composetimer

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

/**
 * Created by Vinay Singh Bisht on 31-Jan-22.
 */
class Timer {

    fun startTimer(time: Long) = flow {
        (time downTo 0).onEach {
            emit(it)
            delay(1000)
        }
    }

}