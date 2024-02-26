package br.com.useblu.oceands.components.compose.input

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.ui.compose.OceanColors


@Preview
@Composable
fun SelectableRadioPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(OceanColors.interfaceLightPure)
            .padding(16.dp)
    ) {
        Text(text = "Checkboxes interagíveis para teste")
        Row {
            var selected1 by remember { mutableIntStateOf(-1) }

            OceanSelectableRadio(
                isSelected = selected1 == 0,
                showError = false,
                onSelectedBox = {
                    println("radio callback invoked")
                    selected1 = 0
                }
            )

            OceanSelectableRadio(
                isSelected = selected1 == 1,
                showError = false,
                onSelectedBox = {
                    selected1 = 1
                }
            )

            OceanSelectableRadio(
                isSelected = selected1 == 2,
                showError = true,
                onSelectedBox = {
                    selected1 = 2
                }
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        Text(text = "Checkboxes estáticos para visualização")
        Row {
            OceanSelectableRadio(
                isSelected = true,
                showError = false,
                enabled = true,
            )
            OceanSelectableRadio(
                isSelected = true,
                showError = false,
                enabled = false,
            )
            OceanSelectableRadio(
                isSelected = false,
                showError = true,
                enabled = false,
            )
        }
    }
}

@Composable
internal fun OceanSelectableRadio(
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isSelected: Boolean = false,
    showError: Boolean = false,
    enabled: Boolean = true,
    onSelectedBox: () -> Unit = {}
) {
    val boxBorderColor = when {
        showError -> OceanColors.statusNegativePure
        !enabled -> OceanColors.interfaceLightDeep
        isSelected -> OceanColors.complementaryPure
        else -> OceanColors.interfaceDarkUp
    }

    val onClickSelectableRadio: () -> Unit = {
        if (enabled) {
            onSelectedBox.invoke()
        }
    }

    LaunchedEffect(key1 = interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    onClickSelectableRadio()
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .size(20.dp)
            .padding(1.dp)
            .background(OceanColors.interfaceLightPure)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = {}
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = boxBorderColor
                ),
                shape = CircleShape
            )
    ) {
        if (isSelected) {
            val resourceIcon =
                if (enabled) R.drawable.icon_radiobutton_checked
                else R.drawable.icon_radio_button_disabled_checked

            Image(
                painter = painterResource(
                    id = resourceIcon
                ),
                contentDescription = ""
            )
        }
    }
}
