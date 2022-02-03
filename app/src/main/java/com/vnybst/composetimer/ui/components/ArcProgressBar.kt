package com.vnybst.composetimer.ui.components

import android.graphics.Paint
import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.vnybst.composetimer.R
import java.util.concurrent.TimeUnit

/**
 * Created by Vinay Singh Bisht on 22-Jan-22.
 */

@Preview
@Composable
fun ShowArcProgressPreview() {
    ArcProgressBar(100, 1000)
}

@Composable
fun ArcProgressBar(timeElapsed: Long, totalTime: Long) {
    val startAngle = 120f
    val sweepAngle = 300f
    val darkThemeEnabled = isSystemInDarkTheme()
    val context = LocalContext.current
    val font = ResourcesCompat.getFont(context, R.font.open_sans_medium)

    Canvas(modifier = Modifier.wrapContentSize()) {
        val canvasHeight = size.height
        val canvasWidth = size.width
        val arcRadius = 650f

        val percentTimeCompleted = ((totalTime - timeElapsed) / totalTime.toDouble())
        val sweepValue = (sweepAngle * percentTimeCompleted).toFloat()

        drawArc(
            color = Color.Gray,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            style = Stroke(width = 12f, cap = StrokeCap.Round),
            topLeft = Offset(
                (canvasWidth / 2) - (arcRadius / 2),
                canvasHeight / 2 - (arcRadius / 2)
            ),
            useCenter = false,
            size = Size(width = arcRadius, height = arcRadius)
        )

        drawArc(
            color = Color.Blue,
            startAngle = startAngle,
            sweepAngle = sweepValue,
            style = Stroke(width = 20f, cap = StrokeCap.Round),
            topLeft = Offset(
                (canvasWidth / 2) - (arcRadius / 2),
                canvasHeight / 2 - (arcRadius / 2)
            ),
            useCenter = false,
            size = Size(width = arcRadius, height = arcRadius)
        )

        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 60f
            typeface = font
            color = if (darkThemeEnabled) Color.White.toArgb() else Color.Black.toArgb()
        }

        val formattedTime = String.format(
            "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timeElapsed),
            TimeUnit.MILLISECONDS.toMinutes(timeElapsed) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(timeElapsed) % TimeUnit.MINUTES.toSeconds(1)
        )

        drawContext.canvas.nativeCanvas.drawText(
            formattedTime,
            center.x,
            center.y,
            paint
        )

    }
}