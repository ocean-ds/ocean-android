package br.com.useblu.oceands.components.compose.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.stringmask.OceanInputType
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun PreviewOceanTextInput() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp)
    ) {
        var text1: String by remember { mutableStateOf("teste") }

        OceanTextInput(
            value = text1,
            label = "Label",
            errorText = "Deu erro",
            onTextChanged = { text1 = it }
        )

        OceanTextInput(
            value = text1,
            label = "Label",
            isTextArea = true,
            onTextChanged = { text1 = it }
        )

        OceanSpacing.StackXS()

        var text2: String by remember { mutableStateOf("user@pag.net") }

        OceanTextInput(
            value = text2,
            label = "Label",
            onTextChanged = { text2 = it }
        )

        OceanSpacing.StackXXS()

        OceanButton(
            text = "Resetar texto",
            buttonStyle = OceanButtonStyle.SecondaryMedium,
            onClick = {
                text2 = "user@pag.net"
            }
        )

        OceanSpacing.StackXS()

        var text3 by remember { mutableStateOf("") }
        OceanTextInput(
            value = text3,
            label = "Label",
            placeholder = "Teste placeholder",
            onTextChanged = { text3 = it },
            trailingIcon = OceanIcons.CALENDAR_OUTLINE
        )

        OceanSpacing.StackXS()

        var text4: String by remember { mutableStateOf("") }
        OceanTextInput(
            value = text4,
            label = "Label",
            placeholder = "Teste placeholder disabled",
            onTextChanged = { text4 = it },
            enabled = false
        )
    }
}

@Preview
@Composable
fun PreviewOceanTextInputMask() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(8.dp)
    ) {
        CreateOceanTextInputPreview("90680120", OceanInputType.CEP)
        CreateOceanTextInputPreview("90680-120", OceanInputType.CEP)
        CreateOceanTextInputPreview("(51)999400685", OceanInputType.Phone)
        CreateOceanTextInputPreview("49.944.251/0001-13", OceanInputType.CpfCnpj)
        CreateOceanTextInputPreview("49944251000113", OceanInputType.CpfCnpj)
        CreateOceanTextInputPreview("06786397956", OceanInputType.CPF)
        CreateOceanTextInputPreview("", OceanInputType.Currency())
        CreateOceanTextInputPreview("90000", OceanInputType.Currency())
        CreateOceanTextInputPreview("900.00", OceanInputType.Currency(false))
        CreateOceanTextInputPreview("900,00", OceanInputType.Currency(true, showZeroValue = true))
        CreateOceanTextInputPreview("23122023", OceanInputType.Date)
    }
}

@Composable
private fun CreateOceanTextInputPreview(
    value: String,
    inputType: OceanInputType
) {
    var text: String by remember { mutableStateOf(value) }

    OceanTextInput(
        value = text,
        label = "Label",
        placeholder = "Placeholder",
        onTextChanged = {
            text = it
            println("text: [$it]")
        },
        oceanInputType = inputType,
    )
}

@Composable
private fun getTextFieldColors() = OutlinedTextFieldDefaults.colors(
    unfocusedBorderColor = OceanColors.interfaceLightDeep,
    focusedBorderColor = OceanColors.brandPrimaryDown,
    errorBorderColor = OceanColors.statusNegativePure,
    disabledBorderColor = OceanColors.interfaceLightUp,
    disabledContainerColor = OceanColors.interfaceLightUp,
)

@Composable
fun OceanTextInput(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    labelColor: Color = OceanColors.interfaceDarkUp,
    errorText: String? = null,
    placeholder: String = "",
    enabled: Boolean = true,
    onTextChanged: (String) -> Unit,
    oceanInputType: OceanInputType = OceanInputType.DEFAULT,
    leadingIcon: OceanIcons? = null,
    trailingIcon: OceanIcons? = null,
    onClickTrailingIcon: (() -> Unit)? = null,
    isTextArea: Boolean = false
) {
    val localTextStyle = TextStyle(
        fontSize = OceanFontSize.xs,
        color = if (enabled) OceanColors.interfaceDarkDeep else OceanColors.interfaceDarkUp,
        fontFamily = OceanFontFamily.BaseRegular
    )

    CompositionLocalProvider(LocalTextStyle provides localTextStyle) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            if (label.isNotBlank()) {
                Text(
                    text = label,
                    color = if (enabled) labelColor else OceanColors.interfaceDarkUp,
                    fontSize = OceanFontSize.xxs
                )
                OceanSpacing.StackXXS()
            }

            val interactionSource = remember { MutableInteractionSource() }

            var textFieldSelection by remember {
                mutableStateOf(TextRange.Zero)
            }

            var textFieldValue by remember(value) {
                val maskedValue = oceanInputType.modifyBeforeOnChange(value)
                mutableStateOf(TextFieldValue(maskedValue, textFieldSelection))
            }

            val height = if (isTextArea) 150.dp else 48.dp
            val singleLine = !isTextArea

            BasicTextField(
                value = textFieldValue,
                modifier = Modifier
                    .height(height)
                    .fillMaxWidth()
                    .background(
                        color = OceanColors.interfaceLightPure,
                        shape = RoundedCornerShape(8.dp)
                    ),
                onValueChange = { changedField ->
                    val modifiedValue = oceanInputType.modifyBeforeOnChange(changedField.text)

                    if (modifiedValue != textFieldValue.text) {
                        textFieldValue = changedField.copy(text = modifiedValue)

                        if (oceanInputType.alwaysGoToEndOfInput()) {
                            textFieldValue = textFieldValue.copy(
                                selection = TextRange(modifiedValue.length)
                            )
                            textFieldSelection = TextRange(modifiedValue.length)
                        } else {
                            textFieldSelection = changedField.selection
                        }

                        onTextChanged(modifiedValue)
                    } else {
                        textFieldValue = changedField.copy(text = modifiedValue)
                    }
                },
                enabled = enabled,
                singleLine = singleLine,
                textStyle = LocalTextStyle.current,
                keyboardOptions = KeyboardOptions(keyboardType = oceanInputType.getKeyboardType()),
                cursorBrush = SolidColor(OceanColors.brandPrimaryPure),
                interactionSource = interactionSource,
                visualTransformation = oceanInputType.getVisualTransformation(),
                decorationBox = { innerTextField ->
                    OceanTextInputDecorationBox(
                        value = value,
                        innerTextField = innerTextField,
                        placeholderCompose = getPlaceholder(
                            placeholder,
                            enabled && value.isBlank()
                        ),
                        oceanInputType = oceanInputType,
                        enabled = enabled,
                        errorText = errorText,
                        interactionSource = interactionSource,
                        textFieldColors = getTextFieldColors(),
                        leadingIcon = if (leadingIcon != null) {
                            {
                                OceanIcon(
                                    iconType = leadingIcon,
                                    tint = OceanColors.interfaceDarkUp
                                )
                            }
                        } else null,
                        trailingIcon = if (trailingIcon != null) {
                            {
                                OceanIcon(
                                    iconType = trailingIcon,
                                    tint = OceanColors.interfaceDarkUp,
                                    modifier = Modifier.clickable(onClickTrailingIcon != null) {
                                        onClickTrailingIcon?.invoke()
                                    }
                                )
                            }
                        } else null,
                        singleLine = singleLine
                    )
                }
            )

            if (!errorText.isNullOrEmpty()) {
                OceanSpacing.StackXXXS()
                Text(
                    text = errorText,
                    color = OceanColors.statusNegativePure,
                    fontFamily = OceanFontFamily.BaseBold,
                    fontSize = OceanFontSize.xxxs
                )
            }
        }
    }
}

@Composable
private fun getPlaceholder(
    placeholder: String,
    enabled: Boolean
): @Composable () -> Unit {
    return {
        Text(
            text = placeholder,
            fontFamily = OceanFontFamily.BaseRegular,
            color = if (enabled) OceanColors.interfaceLightDeep else OceanColors.interfaceDarkUp
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun OceanTextInputDecorationBox(
    value: String,
    innerTextField: @Composable () -> Unit,
    placeholderCompose: @Composable () -> Unit,
    oceanInputType: OceanInputType,
    enabled: Boolean,
    errorText: String?,
    interactionSource: MutableInteractionSource,
    textFieldColors: TextFieldColors,
    leadingIcon: (@Composable () -> Unit)?,
    trailingIcon: (@Composable () -> Unit)?,
    singleLine: Boolean = true
) {
    val contentPadding = if (singleLine) {
        PaddingValues(horizontal = 16.dp)
    } else {
        PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    }

    OutlinedTextFieldDefaults.DecorationBox(
        value = value,
        visualTransformation = VisualTransformation.None,
        innerTextField = innerTextField,
        placeholder = placeholderCompose,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = oceanInputType.getPrefixComposable(),
        singleLine = singleLine,
        enabled = enabled,
        isError = !errorText.isNullOrEmpty(),
        interactionSource = interactionSource,
        colors = textFieldColors,
        contentPadding = contentPadding,
        container = {
            OutlinedTextFieldDefaults.ContainerBox(
                enabled = enabled,
                isError = !errorText.isNullOrEmpty(),
                interactionSource = interactionSource,
                colors = textFieldColors,
                shape = RoundedCornerShape(8.dp),
                focusedBorderThickness = 2.dp,
                unfocusedBorderThickness = 1.dp
            )
        }
    )
}
