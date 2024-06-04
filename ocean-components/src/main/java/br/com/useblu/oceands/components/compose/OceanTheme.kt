package br.com.useblu.oceands.components.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily

@Composable
fun OceanTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = Typography().copy(
            bodyLarge = TextStyle(fontFamily = OceanFontFamily.BaseRegular)
        ),
        colorScheme = MaterialTheme.colorScheme.copy(
            background = OceanColors.interfaceLightPure,
            surface = OceanColors.interfaceLightPure
        )
    ) {
        content()
    }
}
