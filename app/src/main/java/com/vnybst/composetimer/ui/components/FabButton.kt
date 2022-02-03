package com.vnybst.composetimer.ui.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Vinay Singh Bisht on 25-Jan-22.
 */
@Composable
fun FabButton(
    modifier: Modifier,
    icon: ImageVector,
    startTimer: () -> Unit
) {
    FloatingActionButton(onClick = {
        startTimer()
    }, modifier = modifier) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}
