package br.com.useblu.oceands.components.compose.tokeninput

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderBackground

@Composable
fun OceanTokenInput(
    modifier: Modifier = Modifier,
    label: String = "",
    value: String = "",
    error: String = "",
    autocomplete: String = "",
    tokenCount: Int = 4,
    enabled: Boolean = true,
    mask: OceanTokenMask = OceanTokenDefaultMask,
    validator: OceanTokenInputValidator = OceanTokenDefaultValidator,
    onValueChange: (String) -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        if (label.isNotEmpty()) {
            OceanText(
                text = label,
                style = OceanTextStyle.caption,
                color = OceanColors.interfaceDarkDown,
                modifier = Modifier.padding(bottom = OceanSpacing.xxs)
            )
        }

        Box {
            // Visual token boxes
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        enabled = enabled,
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        focusRequester.requestFocus()
                    }
            ) {
                repeat(tokenCount) { index ->
                    TokenBox(
                        character = value.getOrNull(index)?.let {
                            mask.mask(it)
                        } ?: "",
                        isFocused = isFocused && value.length == index,
                        hasError = error.isNotEmpty(),
                        isAutocompleted = autocomplete.isNotEmpty() && index < autocomplete.length
                    )
                }
            }

            // Hidden actual input field
            BasicTextField(
                value = value,
                onValueChange = { newValue ->
                    val processed = validator.processInput(newValue.take(tokenCount))
                    onValueChange(processed)
                },
                modifier = Modifier
                    .alpha(0f)
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                enabled = enabled,
                keyboardOptions = KeyboardOptions(
                    keyboardType = validator.keyboardType,
                    imeAction = if (value.length < tokenCount - 1) ImeAction.Next else ImeAction.Done
                ),
                singleLine = true,
                cursorBrush = SolidColor(Color.Transparent)
            )
        }

        if (error.isNotEmpty()) {
            OceanText(
                text = error,
                style = OceanTextStyle.caption,
                color = OceanColors.statusNegativePure,
                fontFamily = OceanFontFamily.BaseMedium,
                modifier = Modifier.padding(top = OceanSpacing.xxxs)
            )
        }
    }

    LaunchedEffect(autocomplete) {
        if (autocomplete.isNotEmpty()) {
            val processed = validator.processInput(autocomplete.take(tokenCount))
            onValueChange(processed)
            focusRequester.requestFocus()
        }
    }
}

@Composable
private fun TokenBox(
    character: String,
    isFocused: Boolean,
    hasError: Boolean,
    isAutocompleted: Boolean
) {
    val borderColor = when {
        hasError -> OceanColors.statusNegativePure
        isFocused -> OceanColors.brandPrimaryDown
        isAutocompleted -> OceanColors.statusPositivePure
        character.isNotEmpty() -> OceanColors.interfaceLightDown
        else -> OceanColors.interfaceLightDown
    }

    val backgroundColor = when {
        hasError -> OceanColors.statusNegativeUp
        else -> OceanColors.interfaceLightPure
    }

    val borderWidth = if (isFocused) 2.dp else 1.dp

    Box(
        modifier = Modifier
            .size(48.dp)
            .borderBackground(
                color = backgroundColor,
                borderRadius = OceanBorderRadius.SM.allCorners
            )
            .border(
                width = borderWidth,
                color = borderColor,
                shape = OceanBorderRadius.SM.allCorners.shape()
            ),
        contentAlignment = Alignment.Center
    ) {
        if (character.isNotEmpty()) {
            Text(
                text = character,
                fontSize = OceanFontSize.xs,
                fontFamily = OceanFontFamily.BaseRegular,
                color = OceanColors.interfaceDarkDeep,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun OceanTokenInputPreview() {
    OceanTheme {
        Column(
            modifier = Modifier
                .background(color = OceanColors.interfaceLightPure)
                .padding(OceanSpacing.xs),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
        ) {
            // Default alphanumeric
            OceanTokenInput(
                label = "Código de verificação",
                value = "AB12",
                onValueChange = {}
            )

            // Numeric only
            OceanTokenInput(
                label = "PIN numérico",
                value = "1234",
                validator = OceanTokenNumericValidator,
                onValueChange = {}
            )

            // Masked input
            OceanTokenInput(
                label = "Senha",
                value = "PASS",
                mask = OceanTokenSecurityMask,
                onValueChange = {}
            )

            // Letters only (uppercase)
            OceanTokenInput(
                label = "Código alfabético",
                value = "asda",
                mask = OceanTokenUppercaseMask,
                validator = OceanTokenAlphaValidator,
                onValueChange = {}
            )
        }
    }
}
