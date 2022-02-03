package com.vnybst.composetimer.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vnybst.composetimer.Timer
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by Vinay Singh Bisht on 31-Jan-22.
 */
class TimerViewModel(private val timer: Timer) : ViewModel() {

    private val TAG = TimerViewModel::class.simpleName

    private val timerCounter = MutableStateFlow(0L)
    val timePassed: StateFlow<Long> = timerCounter

    var totalTime: Long = 0L

    private var job: Job? = null

    fun setTimer(time: Long) {
        Log.d(TAG, "setTime: $time")
        totalTime = time
        job = viewModelScope.launch {
            timer.startTimer(time)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0L)
                .collect { time ->
                    Log.d(TAG, "collectTime: $time")
                    timerCounter.value = time
                }
        }
    }

    fun stopTimer() {
        job?.cancel()
    }

    class TimerViewModelFactory(private val timer: Timer) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
                return TimerViewModel(timer) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}