package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.MutableLiveData
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.stringmask.OceanInputType


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

        OceanSpacing.StackXS()

        var text2: String by remember { mutableStateOf("user@pag.net") }

        OceanTextInput(
            value = text2,
            label = "Label",
            onTextChanged = { text2 = it }
        )

        OceanSpacing.StackXS()

        val textLiveData = remember {
            MutableLiveData("userLiveData")
        }

        OceanTextInput(
            valueLiveData = textLiveData,
            label = "Label"
        )

        OceanSpacing.StackXS()

        val liveDataState = textLiveData.observeAsState(initial = "")
        OceanTextInput(
            value = liveDataState.value,
            label = "Label",
            onTextChanged = { textLiveData.value = it }
        )

        OceanSpacing.StackXS()

        var text3 by remember { mutableStateOf("") }
        OceanTextInput(
            value = text3,
            label = "Label",
            placeholder = "Teste placeholder",
            onTextChanged = { text3 = it }
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
            .background(Color.White)
            .padding(8.dp)
    ) {
        CreateOceanTextInputPreview("90680120", OceanInputType.CEP)
        CreateOceanTextInputPreview("51999400685", OceanInputType.Phone)
        CreateOceanTextInputPreview("067863", OceanInputType.CpfCnpj)
        CreateOceanTextInputPreview("", OceanInputType.Currency())
        CreateOceanTextInputPreview("90000", OceanInputType.Currency())
        CreateOceanTextInputPreview("900.00", OceanInputType.Currency(false))
        CreateOceanTextInputPreview("900,00", OceanInputType.Currency(true, showZeroValue = true))
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
        onTextChanged = { text = it },
        oceanInputType = inputType
    )
}

@Composable
fun OceanTextInput(
    valueLiveData: MutableLiveData<String>,
    label: String,
    errorText: String? = null,
    placeholder: String = "",
    enabled: Boolean = true
) {
    val value = valueLiveData.observeAsState(initial = "")

    OceanTextInput(
        value.value,
        label,
        errorText,
        placeholder,
        enabled,
        onTextChanged = {
           valueLiveData.value = it
        }
    )
}

@Composable
fun OceanTextInput(
    value: String,
    label: String,
    errorText: String? = null,
    placeholder: String = "",
    enabled: Boolean = true,
    oceanInputType: OceanInputType = OceanInputType.DEFAULT,
    onTextChanged: (String) -> Unit
) {
    val localTextStyle = TextStyle(
        fontSize = OceanFontSize.xs,
        color = if (enabled) OceanColors.interfaceDarkDeep else OceanColors.interfaceDarkUp,
        fontFamily = OceanFontFamily.BaseRegular
    )

    CompositionLocalProvider(LocalTextStyle provides localTextStyle) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = label,
                color = if (enabled) OceanColors.interfaceDarkDown else OceanColors.interfaceDarkUp,
                fontSize = OceanFontSize.xxs
            )
            OceanSpacing.StackXXS()

            val placeholderCompose = @Composable {
                Text(
                    text = placeholder,
                    fontFamily = OceanFontFamily.BaseRegular,
                    color = if (enabled) OceanColors.interfaceLightDeep else OceanColors.interfaceDarkUp
                )
            }

            val textFieldColors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = OceanColors.interfaceLightDeep,
                focusedBorderColor = OceanColors.brandPrimaryDown,
                errorBorderColor = OceanColors.statusNegativePure,
                disabledBorderColor = OceanColors.interfaceLightUp,
                disabledContainerColor = OceanColors.interfaceLightUp,
            )

            val interactionSource = remember { MutableInteractionSource() }

            var textFieldValue by remember {
                val maskedValue = oceanInputType.modifyBeforeOnChange(value)
                mutableStateOf(TextFieldValue(maskedValue))
            }

            BasicTextField(
                value = textFieldValue,
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                onValueChange = {
                    val modifiedValue = oceanInputType.modifyBeforeOnChange(it.text)
                    if (modifiedValue != textFieldValue.text) {
                        onTextChanged(modifiedValue)
                        textFieldValue = it.copy(text = modifiedValue)
                        if (oceanInputType.alwaysGoToEndOfInput()) {
                            textFieldValue = textFieldValue.copy(
                                selection = TextRange(modifiedValue.length)
                            )
                        }
                    } else {
                        textFieldValue = it.copy(text = modifiedValue)
                    }
                },
                enabled = enabled,
                singleLine = true,
                textStyle = LocalTextStyle.current,
                keyboardOptions = KeyboardOptions(keyboardType = oceanInputType.getKeyboardType()),
                cursorBrush = SolidColor(OceanColors.brandPrimaryPure),
                interactionSource = interactionSource,
                visualTransformation = oceanInputType.getVisualTransformation(),
                decorationBox = @Composable { innerTextField ->
                    OceanTextInputDecorationBox(
                        value,
                        innerTextField,
                        placeholderCompose,
                        oceanInputType,
                        enabled,
                        errorText,
                        interactionSource,
                        textFieldColors
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
@OptIn(ExperimentalMaterial3Api::class)
private fun OceanTextInputDecorationBox(
    value: String,
    innerTextField: @Composable () -> Unit,
    placeholderCompose: @Composable () -> Unit,
    oceanInputType: OceanInputType,
    enabled: Boolean,
    errorText: String?,
    interactionSource: MutableInteractionSource,
    textFieldColors: TextFieldColors
) {
    OutlinedTextFieldDefaults.DecorationBox(
        value = value,
        visualTransformation = VisualTransformation.None,
        innerTextField = innerTextField,
        placeholder = placeholderCompose,
        trailingIcon = null,
        prefix = oceanInputType.getPrefixComposable(),
        singleLine = true,
        enabled = enabled,
        isError = !errorText.isNullOrEmpty(),
        interactionSource = interactionSource,
        colors = textFieldColors,
        contentPadding = PaddingValues(horizontal = 16.dp),
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