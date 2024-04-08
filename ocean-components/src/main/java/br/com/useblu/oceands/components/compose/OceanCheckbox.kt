package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.components.compose.input.OceanSelectableBox
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize


@Preview
@Composable
fun OceanCheckboxPreview() {
    Row(
        modifier = Modifier
            .background(OceanColors.interfaceLightPure)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            AddCheckBox(
                label = "Label",
            )
            AddCheckBox(
                label = "Label",
                enabled = false,
            )
            AddCheckBox(
                label = "Label",
                selected = true,
                enabled = true,
                unsettled = true,
            )
            AddCheckBox(
                label = stringResource(
                    id = R.string.link
                ),
                selected = true,
                enabled = false,
                unsettled = false,
            )
            AddCheckBox(
                label =  "Search on <a href=\"https://www.google.com\">Google</a> " +
                        "or <a href=\"https://www.duckduckgo.com\">DuckDuckGo</a>",
                selected = true,
                enabled = true,
                unsettled = false,
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            AddCheckBox(
                label = "Label",
                selected = true,
            )
            AddCheckBox(
                label = "Label",
                selected = true,
                enabled = false,
            )
            AddCheckBox(
                label = "Label",
                selected = true,
                errorMessage = "Error message"
            )
        }
    }
}

@Composable
fun OceanCheckbox(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean = false,
    unsettled: Boolean = false,
    errorMessage: String = "",
    enabled: Boolean = true,
    onSelected: (Boolean) -> Unit = { },
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier.background(OceanColors.interfaceLightPure)
    ) {
        Row {
            OceanSelectableBox(
                interactionSource = interactionSource,
                selected = selected,
                unsettled = unsettled,
                showError = errorMessage.isNotBlank(),
                enabled = enabled,
                onSelectedBox = onSelected
            )
            SelectableBoxLabel(
                label = label,
                enabled = enabled,
                onSelected = {
                    onSelected(!selected)
                },
            )
        }
        Text(
            text = errorMessage,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontFamily = OceanFontFamily.BaseRegular,
            fontSize = OceanFontSize.xxxs,
            color = OceanColors.statusNegativePure,
        )
    }
}



@Composable
private fun AddCheckBox(
    label: String,
    selected: Boolean = false,
    unsettled: Boolean = false,
    enabled: Boolean = true,
    errorMessage: String = "",
) {
    var wasSelected by remember { mutableStateOf(selected) }
    OceanCheckbox(
        label = label,
        selected = wasSelected,
        errorMessage = errorMessage,
        enabled = enabled,
        unsettled = unsettled,
        onSelected = {
            wasSelected = it
            println("wasSelected: $wasSelected")
        }
    )
}
