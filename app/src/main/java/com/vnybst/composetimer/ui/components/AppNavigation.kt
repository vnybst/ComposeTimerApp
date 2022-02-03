package com.vnybst.composetimer.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vnybst.composetimer.Constant
import com.vnybst.composetimer.ui.home.HomeScreen
import com.vnybst.composetimer.ui.timer.TimerProgress
import com.vnybst.composetimer.ui.timer.TimerScreen
import com.vnybst.composetimer.viewmodel.TimerViewModel

/**
 * Created by Vinay Singh Bisht on 31-Jan-22.
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(viewModel: TimerViewModel) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController, startDestination = Constant.HOME.name) {
        composable(Constant.HOME.name, enterTransition = { initial, _ ->
            when (initial.destination.route) {
                Constant.TIMER.name -> slideInHorizontally(initialOffsetX = { 1000 })
                else -> null
            }
        }, exitTransition = { _, target ->
            when (target.destination.route) {
                Constant.TIMER.name -> slideOutHorizontally(targetOffsetX = { -1000 })
                else -> null
            }
        }) {
            HomeScreen(navController, viewModel)
        }
        composable(Constant.TIMER.name, enterTransition = { initial, _ ->
            when (initial.destination.route) {
                Constant.HOME.name -> slideInHorizontally(initialOffsetX = { 1000 })
                else -> null
            }
        }, exitTransition = { _, target ->
            when (target.destination.route) {
                Constant.HOME.name -> slideOutHorizontally(targetOffsetX = { -1000 })
                else -> null
            }
        }) {
            TimerProgress(viewModel)
        }
    }
}