package org.example.calculadorapenal.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

enum class AppTheme { Dark, Light }

private val DarkColorScheme = darkColorScheme(
    primary = Navy,
    secondary = BlueBright,
    tertiary = Orange,
    background = BackgroundDark,
    surface = SurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    primaryContainer = PrimaryContainerDark,
    secondaryContainer = SecondaryContainerDark,
    tertiaryContainer = TertiaryContainerDark,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.Black,
    onBackground = TextOnDark,
    onSurface = TextOnDark,
    onSurfaceVariant = TextOnDark
)

private val LightColorScheme = lightColorScheme(
    primary = BlueLight_Primary,
    secondary = BlueLight_Secondary,
    tertiary = Orange_Light,
    background = BackgroundLight,
    surface = SurfaceVariantLight, // make cards/painéis contrast against white background
    surfaceVariant = SurfaceVariantLight,
    primaryContainer = PrimaryContainerLight,
    secondaryContainer = SecondaryContainerLight,
    tertiaryContainer = TertiaryContainerLight,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.Black,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary
)

@Composable
fun CalculadoraPenalTheme(
    appTheme: AppTheme,
    content: @Composable () -> Unit
) {
    val isDark = appTheme == AppTheme.Dark
    val colorScheme = if (isDark) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            // Dark icons on light theme; light icons on dark theme
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
