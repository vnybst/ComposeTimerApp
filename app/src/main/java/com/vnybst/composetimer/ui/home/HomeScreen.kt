package com.vnybst.composetimer.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import com.vnybst.composetimer.viewmodel.TimerViewModel

/**
 * Created by Vinay Singh Bisht on 31-Jan-22.
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: TimerViewModel) {
    TimerSetupScreen(navController, viewModel)
}