package br.com.useblu.oceands.components.compose.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.stringmask.OceanInputType
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
private fun OceanInputTextSearchPreview() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp)
    ) {
        var text1: String by remember { mutableStateOf("teste") }

        OceanInputTextSearch(
            value = text1,
            onTextChanged = { text1 = it }
        )

        OceanSpacing.StackXS()

        var text2: String by remember { mutableStateOf("12356") }

        OceanInputTextSearch(
            value = text2,
            onTextChanged = { text2 = it },
            oceanInputType = OceanInputType.CNPJ
        )

        OceanSpacing.StackXXS()
    }
}

@Composable
fun OceanInputTextSearch(
    modifier: Modifier = Modifier,
    oceanInputType: OceanInputType = OceanInputType.DEFAULT,
    label: String = "",
    onTextChanged: (String) -> Unit,
    value: String,
    placeholder: String = "",
    error: String? = null
) {
    OceanTextInput(
        modifier = modifier,
        onTextChanged = onTextChanged,
        value = value,
        oceanInputType = oceanInputType,
        leadingIcon = OceanIcons.SEARCH_SOLID,
        placeholder = placeholder,
        errorText = error,
        label = label
    )
}