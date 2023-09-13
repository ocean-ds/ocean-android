package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize


@Preview
@Composable
fun OceanRadioButtonPreview() {
    Row(modifier = Modifier
        .background(OceanColors.interfaceLightPure)
    ) {
       Column(modifier = Modifier
           .padding(start = 16.dp)
       ){
           OceanRadioButton(
               label = "Label",
               onSelected = {
                   println("radio button selected")
               }
           )
           OceanRadioButton(
               label = "Label",
               enabled = false,
               onSelected = {
                   println("radio button selected")
               }
           )
       }
       Column(modifier = Modifier
           .padding(horizontal = 16.dp)
       ){
           OceanRadioButton(
               label = "Label",
               selected = true,
           )
           OceanRadioButton(
               label = "Label",
               selected = true,
               enabled = false,
           )

           var wasSelected by remember { mutableStateOf(true) }
           OceanRadioButton(
               label = "Label",
               selected = true,
               errorMessage = if(!wasSelected) "Error message" else "",
               onSelected = {
                   wasSelected = !wasSelected
               }
           )
       }
    }
}

@Composable
fun OceanRadioButton(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean = false,
    errorMessage: String = "",
    enabled: Boolean = true,
    onSelected: ((Boolean) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = modifier
        .background(OceanColors.interfaceLightPure)
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            onClick = {}
        )
    ){
        Row {
            OceanSelectableRadio(
                interactionSource = interactionSource,
                selected = selected,
                showError = errorMessage.isNotBlank(),
                enabled = enabled,
                onSelectedBox = onSelected
            )
            SelectableBoxLabel(label, enabled)
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
