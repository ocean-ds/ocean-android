package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize


@Preview
@Composable
fun OceanCheckboxPreview() {
    Row(modifier = Modifier
        .background(OceanColors.interfaceLightPure)
    ) {
        Column(modifier = Modifier
            .padding(start = 16.dp)
        ){
            OceanCheckbox(
                label = "Label",
                onSelected = { isSelected ->
                    println("isSelected: $isSelected")
                }
            )
            OceanCheckbox(
                label = "Label",
                enabled = false,
                onSelected = { isSelected ->
                    println("isSelected: $isSelected")
                }
            )
        }
        Column(modifier = Modifier
            .padding(horizontal = 16.dp)
        ){
            OceanCheckbox(
                label = "Label",
                selected = true,
            )
            OceanCheckbox(
                label = "Label",
                selected = true,
                enabled = false,
            )
            OceanCheckbox(
                label = "Label",
                showError = true,
                errorMessage = "Error message",
                onSelected = { isSelected ->
                    println("isSelected: $isSelected")
                }
            )
        }
    }
}

@Composable
fun OceanCheckbox(
    label: String,
    selected: Boolean = false,
    showError: Boolean = false,
    errorMessage: String = "",
    enabled: Boolean = true,
    onSelected: ((Boolean) -> Unit)? = null,
) {
    Column(modifier = Modifier
        .background(OceanColors.interfaceLightPure)
    ){
        Row {
            val isSelected by remember(selected) { mutableStateOf(selected) }
            OceanSelectableBox(
                selected = isSelected,
                showError = showError,
                enabled = enabled,
                onSelectedBox = onSelected
            )
            SelectableBoxLabel(label, enabled)
        }
        Row{
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
}
