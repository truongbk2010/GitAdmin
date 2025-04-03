package com.tyme.component.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme =
    darkColorScheme(
        primary = Indigo40,
        secondary = Color.White,
        tertiary = Indigo80,
    )

private val LightColorScheme =
    lightColorScheme(
        primary = Indigo40,
        secondary = Color.White,
        tertiary = Indigo80,
        /* Other default colors to override
            background = Color.White,
            surface = Indigo80,
            onSurface = Indigo80,
            onBackground = Indigo80,
            onPrimary = Color.White,
            onSecondary = Color.White,
            onTertiary = Color.White,
         */
    )

@Composable
fun GitAdminTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.secondary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                true
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
