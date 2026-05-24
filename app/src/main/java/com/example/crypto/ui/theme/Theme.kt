package com.example.crypto.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.DarkGray

val appColor = AppColor()

private val LightColorScheme = lightColorScheme(

    primary = appColor.LightSurface,
    background = appColor.LightBackground,
    onBackground = appColor.LightOnBackground,
    surface = appColor.LightSurface,
    onSurface = appColor.LightOnSurface,
    secondary = appColor.LightSecondary
)

@Composable
fun CryptoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
        LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
