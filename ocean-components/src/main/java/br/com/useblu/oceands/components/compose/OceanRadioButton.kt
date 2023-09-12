package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
               onSelected = { isSelected ->
                   println("isSelected: $isSelected")
               }
           )
           OceanRadioButton(
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
           OceanRadioButton(
               label = "Label",
               selected = true,
           )
           OceanRadioButton(
               label = "Label",
               selected = true,
               enabled = false,
           )
           OceanRadioButton(
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
fun OceanRadioButton(
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
            OceanSelectableRadio(
                selected = selected,
                showError = showError,
                enabled = enabled,
                onSelectedBox = onSelected
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                text = label,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = OceanFontSize.xxs,
                color = if(enabled) OceanColors.interfaceDarkDown else OceanColors.interfaceDarkUp,
                fontFamily = OceanFontFamily.BaseRegular,
            )
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
