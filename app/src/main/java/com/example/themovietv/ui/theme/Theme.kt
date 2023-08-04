@file:Suppress("PrivatePropertyName")

package com.example.themovietv.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.lightColorScheme

@OptIn(ExperimentalTvMaterial3Api::class)
private val ColorScheme = lightColorScheme(
    primary = LightBlue,
    secondary = DarkBlue,
    tertiary = MediumBlue

    /*Other default colors to override
   background = Color(0xFFFFFBFE),
   surface = Color(0xFFFFFBFE),
   onPrimary = Color.White,
   onSecondary = Color.White,
   onTertiary = Color.White,
   onBackground = Color(0xFF1C1B1F),
   onSurface = Color(0xFF1C1B1F)*/
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TheMovieTvTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = ColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
