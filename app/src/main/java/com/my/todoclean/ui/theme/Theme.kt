package com.my.todoclean.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.White,
    background = BG,
    onBackground = Color.White,
    surface = LightBlue,
    onSurface = BG
)

private val LightColorPalette = lightColors(
    primary = TextColor,
    background = BG,
    onBackground = Color.White,
    surface = Violet,
    onSurface = TextColor,
)

@Composable
fun ToDoCleanTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}