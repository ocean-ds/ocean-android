package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize

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
    Text(
        text = text.uppercase(),
        style = TextStyle(
            fontSize = OceanFontSize.xxs,
            fontFamily = OceanFontFamily.BaseMedium,
            color = textColor,
            letterSpacing = 2.16.sp,
        ),
        modifier = modifier
    )
}