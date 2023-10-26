package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons
import kotlinx.coroutines.delay

@Preview
@Composable
fun OceanInputSearchPreview() {
    Column(
        modifier = Modifier
            .background(OceanColors.interfaceLightPure)
            .padding(16.dp)
    ){
        var setFocus by remember{ mutableStateOf(false) }
        LaunchedEffect(Unit) {
            delay(2000)
            setFocus = true
        }

        OceanInputSearch(
            modifier = Modifier.fillMaxWidth(),
            inputValue = "",
            placeholder = "Search",
            requestFocus = setFocus,
            onTextChange = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        OceanInputSearch(
            modifier = Modifier.fillMaxWidth(),
            inputValue = "Blu",
            placeholder = "Search",
            onTextChange = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        OceanInputSearch(
            modifier = Modifier.fillMaxWidth(),
            inputValue = "",
            placeholder = "Search",
            onTextChange = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
fun OceanInputSearch(
    modifier: Modifier = Modifier,
    inputValue: String = "",
    placeholder: String = "",
    requestFocus: Boolean = false,
    onTextChange: (String) -> Unit,
) {
    var input by remember { mutableStateOf(inputValue) }
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    var isFocused by remember { mutableStateOf(requestFocus) }

    val containerColor = OceanColors.interfaceLightPure
    val activeColor = OceanColors.interfaceDarkDeep
    val inactiveColor = OceanColors.interfaceLightDeep
    val activeSearchIcon = OceanColors.brandPrimaryPure
    val activeBorderColor = OceanColors.brandPrimaryDown
    val unfocusedBorderColor = OceanColors.brandPrimaryUp

    LaunchedEffect(requestFocus) {
        if(requestFocus) {
            focusRequester.requestFocus()
        }
    }

    Box(
        modifier = modifier
            .focusable(true)
            .onFocusChanged { isFocused = it.isFocused }
            .background(OceanColors.interfaceLightPure)
            .clip(RoundedCornerShape(8.dp)),
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
                .focusRequester(focusRequester)
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
    input: String,
    onClick: () -> Unit
): @Composable (() -> Unit)? {
    val activeColor = OceanColors.interfaceDarkUp

    return if(input.isNotEmpty()) {
        {
            Icon(
                modifier = Modifier.clickable { onClick.invoke() },
                painter = painterResource(id = OceanIcons.X_OUTLINE.icon),
                tint = activeColor,
                contentDescription = null,
            )
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

