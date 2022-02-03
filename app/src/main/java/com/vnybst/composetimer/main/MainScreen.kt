package com.vnybst.composetimer.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.vnybst.composetimer.ui.components.AppNavigation
import com.vnybst.composetimer.viewmodel.TimerViewModel

/**
 * Created by Vinay Singh Bisht on 22-Jan-22.
 */
@ExperimentalComposeUiApi
@Composable
fun MainScreen(viewModel: TimerViewModel) {

    Scaffold {
        AppNavigation(viewModel)
    }

}