package com.example.listadecompras.utility

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun getGradient(startColor: Color, endColor: Color): Brush {
    return Brush.verticalGradient(
        colors = listOf(startColor, endColor)
    )
}