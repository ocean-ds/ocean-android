package br.com.useblu.oceands.components.compose

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.ui.compose.OceanColors

@Composable
fun OceanDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(modifier = modifier, color = OceanColors.interfaceLightDown)
}