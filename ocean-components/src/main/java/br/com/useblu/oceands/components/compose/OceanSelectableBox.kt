package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.ui.compose.OceanColors


@Preview
@Composable
fun SelectableBoxPreview() {
    Row {
        Column {
            OceanSelectableBox(
                selected = false,
                showError = false,
                enabled = true,
                isCheckbox = false
            )
            OceanSelectableBox(
                selected = false,
                showError = false,
                enabled = false,
                isCheckbox = false
            )
            OceanSelectableBox(
                selected = false,
                showError = true,
                enabled = false,
                isCheckbox = false
            )
            OceanSelectableBox(
                selected = true,
                showError = false,
                enabled = false,
                isCheckbox = false
            )
            OceanSelectableBox(
                selected = true,
                showError = false,
                enabled = true,
                isCheckbox = false
            )
        }
        Column {
            OceanSelectableBox(
                selected = false,
                showError = false,
                enabled = true,
                isCheckbox = true
            )
            OceanSelectableBox(
                selected = false,
                showError = false,
                enabled = false,
                isCheckbox = true
            )
            OceanSelectableBox(
                selected = false,
                showError = true,
                enabled = false,
                isCheckbox = true
            )
            OceanSelectableBox(
                selected = true,
                showError = false,
                enabled = false,
                isCheckbox = true
            )
            OceanSelectableBox(
                selected = true,
                showError = false,
                enabled = true,
                isCheckbox = true
            )
            OceanSelectableBox(
                selected = true,
                unsettled = true,
                showError = false,
            )
        }
    }
}

@Composable
fun OceanSelectableBox(
    selected: Boolean = false,
    unsettled: Boolean = false,
    showError: Boolean = false,
    enabled: Boolean = true,
    isCheckbox: Boolean = true,
    onSelectedBox: ((Boolean?) -> Unit)? = null
) {
    var isSelected = selected
    Row(
        modifier = Modifier
            .background(OceanColors.interfaceLightPure)
            .clickable {
                isSelected = if (isCheckbox) !isSelected else true
                onSelectedBox?.invoke(isSelected)
            }
    ) {
        val boxBorderColor = if (showError)
            OceanColors.statusNegativePure
        else if (!enabled)
            OceanColors.interfaceLightDeep
        else if (isSelected)
            OceanColors.complementaryPure
        else
            OceanColors.interfaceDarkUp

        Box(
            modifier = Modifier
                .size(20.dp)
                .padding(1.dp)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = boxBorderColor
                    ),
                    shape = if (isCheckbox) {
                        RoundedCornerShape(4.dp)
                    } else {
                        CircleShape
                    }
                )
        ) {
            if(isSelected && !showError){
                if(unsettled) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.icon_checkbox_unsettled
                        ),
                        contentDescription = ""
                    )
                } else if (isCheckbox) {
                    Image(
                        painter = painterResource(
                            id = if(enabled)
                                R.drawable.icon_checkbox_checked
                            else
                                R.drawable.icon_checkbox_checked_disabled
                        ),
                        contentDescription = ""
                    )
                }else {
                    Image(
                        painter = painterResource(
                            id = if(enabled)
                                R.drawable.icon_radiobutton_checked
                            else
                                R.drawable.icon_radio_button_disabled_checked
                        ),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}
