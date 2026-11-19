package br.com.fiap.zelacidade.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

private val LightColors = lightColorScheme(
    primary = Teal40,
    onPrimary = Color.White,
    primaryContainer = Teal90,
    onPrimaryContainer = Teal10,
    secondary = Amber40,
    onSecondary = Color.White,
    secondaryContainer = Amber90,
    onSecondaryContainer = Amber10,
    background = Neutral99,
    onBackground = Neutral10,
    surface = Neutral99,
    onSurface = Neutral10,
    surfaceVariant = NeutralVariant90,
    onSurfaceVariant = NeutralVariant30,
    surfaceContainerLow = Neutral95,
    surfaceContainer = Neutral95,
    surfaceContainerHigh = Neutral90,
    outline = NeutralVariant50,
    outlineVariant = NeutralVariant80
)

private val DarkColors = darkColorScheme(
    primary = Teal80,
    onPrimary = Teal10,
    primaryContainer = Teal30,
    onPrimaryContainer = Teal90,
    secondary = Amber80,
    onSecondary = Amber10,
    secondaryContainer = Amber30,
    onSecondaryContainer = Amber90,
    background = Neutral10,
    onBackground = Neutral90,
    surface = Neutral10,
    onSurface = Neutral90,
    surfaceVariant = NeutralVariant30,
    onSurfaceVariant = NeutralVariant80,
    surfaceContainerLow = Neutral20,
    surfaceContainer = Neutral20,
    surfaceContainerHigh = NeutralVariant30,
    outline = NeutralVariant50,
    outlineVariant = NeutralVariant30
)

/**
 * Tema do ZelaCidade. As cores dinâmicas do sistema não são usadas para manter a
 * identidade visual do aplicativo consistente em qualquer aparelho.
 */
@Composable
fun ZelaCidadeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}

/** Indica se o esquema de cores ativo é escuro (útil quando o tema é forçado em previews). */
@Composable
fun temaEscuroAtivo(): Boolean = MaterialTheme.colorScheme.surface.luminance() < 0.5f
