package br.com.useblu.oceands.components.compose

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.components.compose.input.OceanSelectableBox
import br.com.useblu.oceands.model.compose.checkbox.OceanCheckBoxTextStyle
import br.com.useblu.oceands.model.compose.checkbox.OceanCheckboxLabelItem
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing

@Preview
@Composable
fun OceanCheckboxPreview() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .background(OceanColors.interfaceLightPure)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            AddCheckBox(
                label = "Label"
            )
            AddCheckBox(
                label = "Label",
                enabled = false
            )
            AddCheckBox(
                label = "Label",
                selected = true,
                enabled = true,
                unsettled = true,
                isBold = true
            )
            AddCheckBox(
                label = stringResource(
                    id = R.string.link
                ),
                selected = true,
                enabled = false,
                unsettled = false
            )
            AddCheckBox(
                label = "Search on <a href=\"https://www.google.com\">Google</a> " +
                    "or <a href=\"https://www.duckduckgo.com\">DuckDuckGo</a>",
                selected = true,
                enabled = true,
                unsettled = false
            )

            AddCheckBox<String>(
                labels = listOf(
                    OceanCheckboxLabelItem(
                        id = "default",
                        label = "Li e aceito os "
                    ),
                    OceanCheckboxLabelItem(
                        id = "event",
                        label = " <u>Termos de Uso</u>",
                        style = OceanCheckBoxTextStyle.Selectable(isBold = true)
                    )
                ),
                onSelectItem = { id ->
                    Toast.makeText(
                        context,
                        "Select checkbox item id: $id",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            AddCheckBox(
                label = "Label",
                selected = true
            )
            AddCheckBox(
                label = "Label",
                selected = true,
                enabled = false
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
fun <ID> OceanCheckbox(
    modifier: Modifier = Modifier,
    labels: List<OceanCheckboxLabelItem<ID>>,
    selected: Boolean = false,
    unsettled: Boolean = false,
    errorMessage: String = "",
    enabled: Boolean = true,
    onCheck: (Boolean) -> Unit = { },
    onSelectItem: (id: ID) -> Unit = { _ -> }
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
    ) {
        Row {
            OceanSelectableBox(
                interactionSource = interactionSource,
                selected = selected,
                unsettled = unsettled,
                showError = errorMessage.isNotBlank(),
                enabled = enabled,
                onSelectedBox = onCheck
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = OceanSpacing.xxs)
            ) {
                labels.forEach { item ->
                    SelectableBoxLabel(
                        modifier = Modifier,
                        label = item.label,
                        enabled = enabled,
                        isBold = item.style.isBold,
                        onSelected = {
                            if (item.style.isSelectable) onSelectItem(item.id)
                            else onCheck(!selected)
                        }
                    )
                }
            }
        }
        Text(
            text = errorMessage,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontFamily = OceanFontFamily.BaseRegular,
            fontSize = OceanFontSize.xxxs,
            color = OceanColors.statusNegativePure
        )
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
    isBold: Boolean = false
) {
    OceanCheckbox<Unit>(
        modifier = modifier,
        labels = listOf(
            OceanCheckboxLabelItem(
                id = Unit,
                label = label,
                style = OceanCheckBoxTextStyle.Regular(isBold = isBold)
            )
        ),
        selected = selected,
        unsettled = unsettled,
        errorMessage = errorMessage,
        enabled = enabled,
        onCheck = onSelected
    )
}

@Composable
private fun AddCheckBox(
    label: String,
    selected: Boolean = false,
    unsettled: Boolean = false,
    enabled: Boolean = true,
    errorMessage: String = "",
    isBold: Boolean = false
) {
    AddCheckBox<Unit>(
        labels = listOf(
            OceanCheckboxLabelItem(
                id = Unit,
                label = label,
                style = OceanCheckBoxTextStyle.Regular(isBold = isBold)
            )
        ),
        selected = selected,
        unsettled = unsettled,
        errorMessage = errorMessage,
        enabled = enabled
    )
}

@Composable
private fun <ID> AddCheckBox(
    labels: List<OceanCheckboxLabelItem<ID>>,
    selected: Boolean = false,
    unsettled: Boolean = false,
    errorMessage: String = "",
    enabled: Boolean = true,
    onSelectItem: (id: ID) -> Unit = { _ -> }
) {
    var wasSelected by remember { mutableStateOf(selected) }
    OceanCheckbox(
        labels = labels,
        selected = wasSelected,
        unsettled = unsettled,
        errorMessage = errorMessage,
        enabled = enabled,
        onCheck = {
            wasSelected = it
            println("wasSelected: $wasSelected")
        },
        onSelectItem = {
            println("Selected item ID: $it")
            onSelectItem(it)
        }
    )
}
