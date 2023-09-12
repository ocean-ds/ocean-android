package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize

@Composable
fun SelectableBoxLabel(label: String, enabled: Boolean) {
    Text(
        modifier = Modifier
            .padding(horizontal = 8.dp),
        text = label,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontSize = OceanFontSize.xxs,
        color = if (enabled) OceanColors.interfaceDarkDown else OceanColors.interfaceDarkUp,
        fontFamily = OceanFontFamily.BaseRegular,
    )
}
