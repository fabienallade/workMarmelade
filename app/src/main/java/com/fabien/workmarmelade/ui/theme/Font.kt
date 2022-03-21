package com.fabien.workmarmelade.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.fabien.workmarmelade.R

val fontSansFamily = FontFamily(
    Font(R.font.inriasans_light, FontWeight.Light),
    Font(R.font.inriasans_regular, FontWeight.Normal),
    Font(R.font.inriasans_lightitalic, FontWeight.Normal, FontStyle.Italic),
//    Font(R.font.inriasans_lightitalic, FontWeight.Medium),
    Font(R.font.inriasans_bold, FontWeight.Bold)
)