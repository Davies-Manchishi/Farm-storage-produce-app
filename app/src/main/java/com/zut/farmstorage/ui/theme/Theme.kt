package com.zut.farmstorage.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// We disable dark mode for now so the app always has a bright, readable farming theme
private val FarmColorScheme = lightColorScheme(
    primary = FarmGreen,
    onPrimary = White,
    secondary = FarmBrown,
    onSecondary = White,
    background = OffWhite,
    onBackground = DarkText,
    surface = White,
    onSurface = DarkText,
    surfaceVariant = FarmBrownLight,
    onSurfaceVariant = FarmBrown
)

@Composable
fun FarmStorageTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FarmColorScheme,
        typography = Typography,
        content = content
    )
}
