package com.fabien.workmarmelade.ui.theme.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
                .width(width * percent / 100)
        ){
            Box(modifier = modifier.width(10.dp).height(10.dp).background(color = Color.White))
        }
    }
}