package br.com.useblu.oceands.components.compose.input

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
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors

@Preview
@Composable
fun SelectableBoxPreview() {
    Row {
        Column {
            OceanSelectableBox(
                selected = false,
                showError = false,
                enabled = true
            )
            OceanSelectableBox(
                selected = false,
                showError = false,
                enabled = false
            )
            OceanSelectableBox(
                selected = false,
                showError = true,
                enabled = false
            )
        }
        Column {
            OceanSelectableBox(
                selected = true,
                showError = false,
                enabled = true,
                onSelectedBox = { isSelected ->
                    println("isSelected: $isSelected")
                }
            )
            OceanSelectableBox(
                selected = true,
                showError = false,
                enabled = false
            )
            OceanSelectableBox(
                selected = false,
                showError = true,
                enabled = false
            )
            OceanSelectableBox(
                selected = true,
                unsettled = true,
                showError = false
            )
        }
    }
}

@Composable
internal fun OceanSelectableBox(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selected: Boolean = false,
    unsettled: Boolean = false,
    showError: Boolean = false,
    enabled: Boolean = true,
    onSelectedBox: ((Boolean) -> Unit)? = null
) {
    var isSelected by remember(selected) { mutableStateOf(selected) }
    var isUnsettled by remember(unsettled) { mutableStateOf(unsettled) }

    val boxBorderColor = when {
        showError -> OceanColors.statusNegativePure
        !enabled -> OceanColors.interfaceLightDeep
        isSelected -> OceanColors.complementaryPure
        else -> OceanColors.interfaceDarkUp
    }

    val onClickSelectableBox: (() -> Unit) = {
        if (enabled && !isUnsettled) {
            isSelected = !isSelected
        } else if (isUnsettled) {
            isUnsettled = false
            isSelected = true
        }
        onSelectedBox?.invoke(isSelected)
    }

    LaunchedEffect(key1 = interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    onClickSelectableBox()
                }
            }
        }
    }

    Box(
        modifier = modifier
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
                shape = OceanBorderRadius.Tiny.allCorners.shape()
            )
    ) {
        if (isSelected && !showError) {
            if (isUnsettled) {
                Image(
                    painter = painterResource(
                        id = R.drawable.icon_checkbox_unsettled
                    ),
                    contentDescription = ""
                )
            } else {
                val resourceIcon =
                    if (enabled) R.drawable.icon_checkbox_checked
                    else R.drawable.icon_checkbox_checked_disabled

                Image(
                    painter = painterResource(
                        id = resourceIcon
                    ),
                    contentDescription = ""
                )
            }
        }
    }
}
