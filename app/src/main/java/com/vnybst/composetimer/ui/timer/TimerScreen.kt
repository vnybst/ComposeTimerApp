package com.vnybst.composetimer.ui.timer

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vnybst.composetimer.ui.components.ArcProgressBar
import com.vnybst.composetimer.ui.components.FabButton
import com.vnybst.composetimer.viewmodel.TimerViewModel

/**
 * Created by Vinay Singh Bisht on 31-Jan-22.
 */
@Preview
@Composable
fun TimerScreen() {
    TimerProgress(viewModel())
}

@Composable
fun TimerProgress(viewModel: TimerViewModel) {
    val spentTime by viewModel.timePassed.collectAsState()
    val totalTime = viewModel.totalTime

    val context = LocalContext.current

    Scaffold {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ArcProgressBar(spentTime * 1000, totalTime * 1000)

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )

                FabButton(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(vertical = 24.dp),
                    icon = Icons.Outlined.Close
                ) {
                    viewModel.stopTimer()
                    Toast.makeText(context, "Timer Stopped!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
