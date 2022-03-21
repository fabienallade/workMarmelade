package com.fabien.workmarmelade.ui.theme.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressBar(modifier: Modifier, width: Dp, backgroundColor: Color, foregroundColor: Color, percent: Int) {
    Box(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxWidth(0.9f)
    ) {
        Box(
            modifier = modifier
                .background(foregroundColor)
                .width(width * percent / 100),
            contentAlignment = Alignment.CenterEnd
        ){
            Box(modifier = modifier
                .clip(shape = RoundedCornerShape(50))
                .width(15.dp).height(15.dp)
                .background(color = Color.White)
                .border(border = BorderStroke(2.dp, color = Color.Black)))
        }
    }
}