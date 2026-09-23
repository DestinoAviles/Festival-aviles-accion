package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val FestivalLightColorScheme = lightColorScheme(
    primary = PrimaryRed,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    secondary = FilmCoal,
    onSecondary = PureWhite,
    secondaryContainer = PaperTint,
    onSecondaryContainer = FilmBlack,
    tertiary = Tertiary,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = PureWhite,
    background = Surface,
    onBackground = OnSurface,
    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceContainerHigh,
    onSurfaceVariant = OnSurfaceVariant,
    outline = Outline,
    outlineVariant = OutlineVariant,
    surfaceContainerLowest = SurfaceContainerLowest,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainer = SurfaceContainer,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest,
)

private val FestivalDarkColorScheme = darkColorScheme(
    primary = PrimaryContainer,
    onPrimary = PureWhite,
    primaryContainer = BrandRedDark,
    onPrimaryContainer = OnPrimaryContainer,
    secondary = PaperMuted,
    onSecondary = FilmBlack,
    secondaryContainer = FilmCoal,
    onSecondaryContainer = PaperBase,
    tertiary = TertiaryFixed,
    onTertiary = FilmBlack,
    tertiaryContainer = Tertiary,
    onTertiaryContainer = PureWhite,
    background = FilmBlack,
    onBackground = PaperBase,
    surface = FilmCoal,
    onSurface = PureWhite,
    surfaceVariant = FilmCoal,
    onSurfaceVariant = PaperMuted,
    outline = FilmGray,
    outlineVariant = FilmCoal,
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) FestivalDarkColorScheme else FestivalLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
