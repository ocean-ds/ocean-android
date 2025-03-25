package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing

@Preview(showBackground = true)
@Composable
fun OceanSwitchPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OceanSwitch(
            checked = true,
            onCheckedChange = {
                println("isChecked: $it")
            }
        )

        OceanSpacing.StackMD()

        OceanSwitch(
            checked = false,
            onCheckedChange = {
                println("isChecked: $it")
            }
        )

        OceanSpacing.StackMD()

        OceanSwitch(
            enabled = false,
            checked = false,
            onCheckedChange = {
                println("isChecked: $it")
            }
        )

        OceanSpacing.StackMD()

        OceanSwitch(
            enabled = false,
            checked = true,
            onCheckedChange = {
                println("isChecked: $it")
            }
        )
    }
}

@Composable
fun OceanSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    var isChecked by remember(checked) { mutableStateOf(checked) }

    Switch(
        modifier = modifier.scale(0.8f, 0.8f),
        enabled = enabled,
        checked = isChecked,
        onCheckedChange = {
            isChecked = it
            onCheckedChange(it)
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = OceanColors.interfaceLightPure,
            checkedTrackColor = if (enabled) OceanColors.complementaryPure else OceanColors.interfaceLightDown,
            uncheckedThumbColor = if (enabled) OceanColors.interfaceDarkUp else OceanColors.interfaceLightDown,
            uncheckedTrackColor = OceanColors.interfaceLightPure,
            uncheckedBorderColor = if (enabled) OceanColors.interfaceDarkUp else OceanColors.interfaceLightDown
        )
    )
}
