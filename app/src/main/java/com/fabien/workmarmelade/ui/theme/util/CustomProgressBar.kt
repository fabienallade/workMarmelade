package com.fabien.workmarmelade.ui.theme.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressBar(modifier: Modifier, width: Dp, backgroundColor: Color, foregroundColor: Color, percent: Int) {
    Box(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .background(foregroundColor)
                .width(width * percent / 100),
            contentAlignment = Alignment.CenterEnd
        ){
            Box(modifier = Modifier
                .clip(shape = CircleShape)
                .size(15.dp).fillMaxHeight()
                .background(color = Color.White)
                .border(border = BorderStroke(2.dp, color = Color.Black))
                ,
            ){

            }
        }
    }
}

fun progressTextWithShape(){

}