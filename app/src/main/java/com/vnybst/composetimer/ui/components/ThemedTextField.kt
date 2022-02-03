package com.vnybst.composetimer.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vnybst.composetimer.ui.theme.Purple200

/**
 * Created by Vinay Singh Bisht on 24-Jan-22.
 */

@Composable
fun ThemedTextField(
    value: TextFieldValue,
    modifier: Modifier,
    onValueChange: (TextFieldValue) -> Unit
) {

    BasicTextField(
        value = value,
        enabled = false,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = if (isSystemInDarkTheme()) Purple200 else Color.Black,
        ),
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier,
        decorationBox = { innerTextField ->
            Row(modifier = Modifier.width(50.dp), horizontalArrangement = Arrangement.Center) {
                if (value.text.isEmpty()) {
                    Text(
                        text = "00",
                        color = if (isSystemInDarkTheme()) Purple200 else Color.Gray,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            innerTextField()
        }
    )

}