package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanTextStyle

@Preview
@Composable
fun OceanEyebrowPreview() {
    Column(
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OceanEyebrow(
            text = "O link blu voltou"
        )

        OceanEyebrow(
            text = "eyebrow"
        )
    }
}

@Composable
fun OceanEyebrow(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = OceanColors.interfaceDarkDeep
) {
    // Uppercase é garantido por construção aqui (no render), pois `TextStyle` não tem
    // equivalente a "allCaps". Os demais tokens (família, tamanho, letter-spacing,
    // line-height) vêm de `OceanTextStyle.eyebrow` — fonte única (§3.3 do spec MR-492).
    OceanText(
        text = text.uppercase(),
        modifier = modifier,
        color = textColor,
        style = OceanTextStyle.eyebrow
    )
}
