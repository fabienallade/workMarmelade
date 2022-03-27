package com.fabien.workmarmelade.ui.theme.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabien.workmarmelade.ui.theme.BackgroundNew1
import com.fabien.workmarmelade.ui.theme.ProgressBackground

@Composable
fun CustomProgressBar(
    modifier: Modifier,
    width: Dp,
    backgroundColor: Color,
    foregroundColor: Color,
    percent: Int
) {
    Column() {
//        ProgressTextWithShape(modifier = Modifier.offset(x =(width) * percent / 100 ),
//        percent)
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
            ) {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(50))
                        .wrapContentSize(Alignment.Center)
                        .size(15.dp)
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .border(width = 1.dp, color = Color.Black, shape = CircleShape)
//                    .border(border = BorderStroke(2.dp, color = Color.Black)),
                ){
                }
            }
        }
    }
}

@Composable
fun ProgressTextWithShape(modifier: Modifier,percent: Int = 0) {
    Box(
        modifier = modifier
            .width(48.dp)
            .height(21.dp),
    ) {
        Box(
            modifier = Modifier
                .height(14.5.dp)
                .width(48.dp)
                .background(
                    color = BackgroundNew1,
                    shape = RoundedCornerShape(
                        topStart = 20.dp, topEnd = 20.dp,
                        bottomEnd = 20.dp, bottomStart = 20.dp
                    )
                )
        ) {
//            Column(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .width(48.dp)
                        .height(10.dp), horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "${percent}%",
                        textAlign = TextAlign.Center, fontSize = 8.sp,
                    color = Color.White)
                }
                Box(
                    modifier = Modifier
                        .width(49.dp)
                        .height(35.dp)
                        .offset(0.dp, 10.dp)
                        .background(
                            shape = GenericShape { size, _ ->
//        moveTo(size.width/2,size.height/2)
                                lineTo(size.width, 0f)
                                lineTo(size.width / 2, size.height / 2)
                            },
                            color = BackgroundNew1,
                        )
                )
//            }
        }
    }
}

@Composable
@Preview
fun PreviewProgress(){
    CustomProgressBar(
        Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .height(14.dp),
        350.dp,
        ProgressBackground,
        BackgroundNew1,
    50)
}
//fun previewProgressTextWithShape() {
//    progressTextWithShape()
//}