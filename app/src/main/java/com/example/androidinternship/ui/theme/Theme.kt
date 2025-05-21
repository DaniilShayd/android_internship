package com.example.androidinternship.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFA5D6A7),  // Light Green 200
    onPrimary = Color(0xFF003300),
    primaryContainer = Color(0xFF338A3C),
    onPrimaryContainer = Color(0xFFE8F5E9),

    secondary = Color(0xFFC5E1A5),  // Light Green 100
    onSecondary = Color(0xFF1A3000),
    secondaryContainer = Color(0xFF4E6629),
    onSecondaryContainer = Color(0xFFE6F0D6),

    tertiary = Color(0xFFE6EE9C),  // Lime 200
    onTertiary = Color(0xFF2D2F00),
    tertiaryContainer = Color(0xFF949755),
    onTertiaryContainer = Color(0xFFF5F7E0),

    background = Color(0xFF121212),
    onBackground = Color(0xFFE0E0E0),

    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFEDEDED),
    surfaceVariant = Color(0xFF2E2E2E),
    onSurfaceVariant = Color(0xFFC8C8C8),

    error = Color(0xFFCF6679),
    onError = Color(0xFF3D0007),

    outline = Color(0xFF5C5C5C)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4CAF50),  // Green 500
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFC8E6C9),
    onPrimaryContainer = Color(0xFF002107),

    secondary = Color(0xFF8BC34A),  // Light Green 500
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFDCEED0),
    onSecondaryContainer = Color(0xFF0F2000),

    tertiary = Color(0xFFCDDC39),  // Lime 500
    onTertiary = Color(0xFF000000),
    tertiaryContainer = Color(0xFFE9F4A8),
    onTertiaryContainer = Color(0xFF222400),

    background = Color(0xFFF5F5F5),
    onBackground = Color(0xFF1A1A1A),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1A1A1A),
    surfaceVariant = Color(0xFFE8E8E8),
    onSurfaceVariant = Color(0xFF454545),

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),

    outline = Color(0xFF757575)
)

@Composable
fun AndroidInternshipTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}