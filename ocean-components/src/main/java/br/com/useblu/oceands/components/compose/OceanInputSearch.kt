package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
fun OceanInputSearchPreview() {
    Column(
        modifier = Modifier
            .background(OceanColors.interfaceLightPure)
            .padding(16.dp)
    ){
        OceanInputSearch(
            modifier = Modifier.fillMaxWidth(),
            inputValue = "Blu",
            placeholder = "Search",
            onTextChange = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        OceanInputSearch(
            modifier = Modifier.fillMaxWidth(),
            inputValue = "Blu",
            placeholder = "Search",
            onTextChange = {}
        )
    }
}

@Composable
fun OceanInputSearch(
    modifier: Modifier = Modifier,
    inputValue: String = "",
    placeholder: String = "",
    onTextChange: (String) -> Unit,
) {
    var input by remember { mutableStateOf(inputValue) }
    var isFocused by remember { mutableStateOf(false) }

    val containerColor = OceanColors.interfaceLightPure
    val activeColor = OceanColors.interfaceDarkDeep
    val inactiveColor = OceanColors.interfaceLightDeep
    val activeSearchIcon = OceanColors.brandPrimaryPure
    val activeBorderColor = OceanColors.brandPrimaryDown
    val unfocusedBorderColor = OceanColors.brandPrimaryUp

    Box(
        modifier = modifier
            .onFocusChanged { isFocused = it.isFocused }
            .clip(RoundedCornerShape(8.dp))
            .background(OceanColors.interfaceLightPure),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            modifier = Modifier
                .border(
                    shape = RoundedCornerShape(8.dp),
                    width = if (!isFocused && input.isNotEmpty()) 1.dp else 2.dp,
                    color = if (!isFocused && input.isEmpty()) inactiveColor
                    else if (isFocused) activeBorderColor else unfocusedBorderColor,
                )
                .fillMaxWidth(),
            value = input,
            onValueChange = {
                input = it
                onTextChange.invoke(it)
            },
            singleLine = true,
            textStyle = OceanTextStyle.paragraph,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                focusedTextColor = activeColor,
                unfocusedTextColor = inactiveColor,
                focusedLeadingIconColor = activeSearchIcon,
                unfocusedLeadingIconColor = inactiveColor,
                focusedPlaceholderColor = inactiveColor,
                unfocusedPlaceholderColor = inactiveColor,
            ),
            leadingIcon = leadingIconSetup(
                isFocused = isFocused,
                input = input,
            ),
            trailingIcon = trailingIconSetup(
                isFocused = isFocused,
                input = input,
                onClick = {
                    input = ""
                }
            ),
            placeholder = placeholderSetup(
                isFocused = isFocused,
                input = input,
                placeholder = placeholder
            ),
        )
    }
}

@Composable
fun trailingIconSetup(
    isFocused: Boolean,
    input: String,
    onClick: () -> Unit
): @Composable (() -> Unit)? {
    val activeColor = OceanColors.interfaceDarkUp

    return if(isFocused && input.isNotEmpty()) {
        {
            Box(
                modifier = Modifier.clickable { onClick.invoke() }
            ) {
                Icon(
                    painter = painterResource(id = OceanIcons.X_OUTLINE.icon),
                    tint = activeColor,
                    contentDescription = null,
                )
            }
        }
    } else null
}

@Composable
fun leadingIconSetup(
    isFocused: Boolean,
    input: String,
): @Composable (() -> Unit) {
    val activeColor = OceanColors.brandPrimaryPure
    val inactiveColor = OceanColors.interfaceLightDeep

    return {
            if(isFocused || input.isNotEmpty()) {
                Icon(
                    painter = painterResource(id = OceanIcons.SEARCH_OUTLINE.icon),
                    tint = activeColor,
                    contentDescription = null,
                )
            } else {
                Icon(
                    painter = painterResource(id = OceanIcons.SEARCH_OUTLINE.icon),
                    tint = inactiveColor,
                    contentDescription = null,
                )
            }
        }
}

@Composable
fun placeholderSetup(
    isFocused: Boolean,
    input: String,
    placeholder: String
): @Composable (() -> Unit)? {
    val inactiveColor = OceanColors.interfaceLightDeep

    return if(isFocused || input.isEmpty()) {
        {
            Text(
                text = placeholder,
                style = OceanTextStyle.paragraph,
                color = inactiveColor
            )
        }
    } else null
}

