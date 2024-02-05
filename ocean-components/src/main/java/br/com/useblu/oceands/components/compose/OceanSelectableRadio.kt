package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
    Row {
        Column {
            OceanSelectableRadio(
                selected = false,
                showError = false,
                enabled = true,
                onSelectedBox = {
                    println("radio callback invoked")
                }
            )
            OceanSelectableRadio(
                selected = false,
                showError = false,
                enabled = false,
            )
            OceanSelectableRadio(
                selected = false,
                showError = true,
                enabled = false,
            )
        }
        Column {
            OceanSelectableRadio(
                selected = true,
                showError = false,
                enabled = true,
            )
            OceanSelectableRadio(
                selected = true,
                showError = false,
                enabled = false,
            )
            OceanSelectableRadio(
                selected = false,
                showError = true,
                enabled = false,
            )
        }
    }
}

@Composable
fun OceanSelectableRadio(
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selected: Boolean = false,
    showError: Boolean = false,
    enabled: Boolean = true,
    onSelectedBox: ((Boolean) -> Unit)? = null
) {
    var isSelected by remember(selected) { mutableStateOf(selected) }
    val boxBorderColor = when {
        showError -> OceanColors.statusNegativePure
        !enabled -> OceanColors.interfaceLightDeep
        isSelected -> OceanColors.complementaryPure
        else -> OceanColors.interfaceDarkUp
    }

    val onClickSelectableRadio: () -> Unit = {
        if (enabled) {
            isSelected = true
            onSelectedBox?.invoke(isSelected)
        }
    }

    LaunchedEffect(key1 = interactionSource ){
        interactionSource.interactions.collect {interaction ->
            when(interaction) {
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
