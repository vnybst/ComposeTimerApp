package com.vnybst.composetimer.ui.home

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vnybst.composetimer.Constant
import com.vnybst.composetimer.R
import com.vnybst.composetimer.listeners.KeyboardKeyListener
import com.vnybst.composetimer.ui.components.Keyboard
import com.vnybst.composetimer.ui.components.FabButton
import com.vnybst.composetimer.ui.components.ThemedTextField
import com.vnybst.composetimer.viewmodel.TimerViewModel

/**
 * Created by Vinay Singh Bisht on 22-Jan-22.
 */

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalComposeUiApi
@Preview
@Composable
fun PreviewLayout() {
    Scaffold {
        TimerSetupScreen(navController = rememberAnimatedNavController(), viewModel())
    }
}

@ExperimentalComposeUiApi
@Composable
fun TimerSetupScreen(navController: NavController, viewModel: TimerViewModel) {

    var hour by remember {
        mutableStateOf(TextFieldValue(text = ""))
    }

    var minute by remember {
        mutableStateOf(TextFieldValue(text = ""))
    }

    var seconds by remember {
        mutableStateOf(TextFieldValue(text = ""))
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (hourInput, hourDiv, minuteInput,
            minuteDiv, secondInput, keyboard,
            startButton, title) = createRefs()

        createHorizontalChain(
            hourInput,
            hourDiv,
            minuteInput,
            minuteDiv,
            secondInput,
            chainStyle = ChainStyle.Packed
        )

        createVerticalChain(
            hourInput,
            keyboard,
            startButton,
            chainStyle = ChainStyle.Packed
        )

        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        )

        ThemedTextField(
            value = hour,
            onValueChange = {
                hour = it
            },
            modifier = Modifier
                .width(50.dp)
                .constrainAs(hourInput) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(hourDiv.start)
                    bottom.linkTo(keyboard.top)
                    centerHorizontallyTo(parent)
                }
        )

        Text(
            text = ":",
            Modifier
                .width(10.dp)
                .constrainAs(hourDiv) {
                    top.linkTo(hourInput.top)
                    start.linkTo(hourInput.end)
                    bottom.linkTo(hourInput.bottom)
                    end.linkTo(minuteInput.start)
                    centerHorizontallyTo(parent)
                },
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )

        ThemedTextField(
            value = minute,
            onValueChange = { minute = it },
            modifier = Modifier
                .width(50.dp)
                .constrainAs(minuteInput) {
                    start.linkTo(hourDiv.end)
                    top.linkTo(hourInput.top)
                    bottom.linkTo(hourInput.bottom)
                    end.linkTo(minuteDiv.start)
                    centerHorizontallyTo(parent)
                }

        )

        Text(
            text = ":",
            Modifier
                .width(10.dp)
                .constrainAs(minuteDiv) {
                    start.linkTo(minuteInput.end)
                    top.linkTo(minuteInput.top)
                    bottom.linkTo(minuteInput.bottom)
                    end.linkTo(secondInput.start)
                    centerHorizontallyTo(parent)
                },
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )

        ThemedTextField(
            value = seconds,
            onValueChange = { seconds = it },
            modifier = Modifier
                .width(50.dp)
                .constrainAs(secondInput) {
                    start.linkTo(minuteDiv.end)
                    top.linkTo(minuteInput.top)
                    bottom.linkTo(minuteInput.bottom)
                    end.linkTo(parent.end)
                    centerHorizontallyTo(parent)
                }
        )

        Keyboard(modifier = Modifier.constrainAs(keyboard) {
            start.linkTo(parent.start)
            top.linkTo(hourInput.bottom, margin = 24.dp)
            bottom.linkTo(startButton.top)
            end.linkTo(parent.end)
        }, object : KeyboardKeyListener {
            override fun onKeyPressed(key: String) {
                Log.d("TimerScreen", "onKeyPressed: $key")
                when {
                    seconds.text.length < 2 -> {
                        seconds = TextFieldValue(seconds.text + key)
                    }
                    minute.text.length < 2 -> {
                        minute = TextFieldValue(minute.text + key)
                    }
                    hour.text.length < 2 -> {
                        hour = TextFieldValue(hour.text + key)
                    }
                }
            }

            override fun onClearPressed() {
                Log.d("TimeScreen", "OnClearPressed: ")
                when {
                    hour.text.isNotEmpty() -> {

                        hour = TextFieldValue(
                            clear(hour.text)
                        )
                    }

                    minute.text.isNotEmpty() -> {
                        minute = TextFieldValue(
                            clear(minute.text)
                        )
                    }

                    seconds.text.isNotEmpty() -> {
                        seconds = TextFieldValue(
                            clear(seconds.text)
                        )
                    }
                }
            }

        })

        FabButton(
            modifier = Modifier.constrainAs(startButton) {
                top.linkTo(keyboard.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                centerHorizontallyTo(parent)
            },
            icon = Icons.Outlined.PlayArrow
        ) {
            if (hour.text.typeConverter().plus(minute.text.typeConverter())
                    .plus(seconds.text.typeConverter()) > 0
            ) {
                navController.navigate(Constant.TIMER.name)
                viewModel.setTimer(
                    ((hour.text.typeConverter() * 60 * 60)
                            + (minute.text.typeConverter() * 60)
                            + seconds.text.typeConverter())
                )
            }
        }
    }

}


private fun clear(value: String): String {
    return value.substring(0, value.length - 1)
}

private fun String.typeConverter(): Long {
    return if (this.isNotEmpty()) this.toLong() else 0
}