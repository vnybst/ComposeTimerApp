package com.vnybst.composetimer.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vnybst.composetimer.R
import com.vnybst.composetimer.listeners.KeyboardKeyListener

/**
 * Created by Vinay Singh Bisht on 22-Jan-22.
 */

@Preview
@Composable
fun ShowPreview() {
    Scaffold {
        Keyboard(listener = object : KeyboardKeyListener {
            override fun onKeyPressed(key: String) {

            }

            override fun onClearPressed() {
            }

        })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Keyboard(
    modifier: Modifier = Modifier,
    listener: KeyboardKeyListener
) {

    val keys = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10)

    LazyVerticalGrid(
        cells = GridCells.Adaptive(95.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        modifier = modifier
    ) {
        items(keys) { key ->
            KeyBoardButton(value = key, listener)
        }
    }
}

@Composable
fun KeyBoardButton(
    value: Int,
    listener: KeyboardKeyListener
) {

    if (value <= 9) {
        TextButton(
            shape = RoundedCornerShape(100),
            onClick = { listener.onKeyPressed(value.toString()) },
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    onClick = { listener.onKeyPressed(value.toString()) }
                )) {
            Text(
                text = value.toString(), style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Normal,
                    color = if(isSystemInDarkTheme()) Color.White else Color.Black
                )
            )
        }
    } else {
        IconButton(
            onClick = { listener.onClearPressed() },
            modifier = Modifier
                .size(75.dp)
                .padding(5.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    onClick = { listener.onClearPressed() }
                )) {
            Icon(
                painter = painterResource(id = R.drawable.ic_outline_backspace_24),
                tint = Color.Red,
                contentDescription = null
            )
        }
    }
}