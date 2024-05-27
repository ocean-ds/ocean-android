package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

@Preview
@Composable
private fun OceanSectionHeaderPreview() {
    Column(
        modifier = Modifier
            .background(color = Color(0xFFFCFCFC))
    ) {
        OceanSectionHeader(title = "Title", modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun OceanSectionHeader(
    modifier: Modifier = Modifier,
    title: String
) {
    Row(
        modifier = modifier.height(20.dp)
    ) {
        OceanText(
            text = title,
            modifier = Modifier
                .padding(horizontal = OceanSpacing.xs),
            style = OceanTextStyle.heading5,
            color = OceanColors.interfaceDarkUp
        )
    }
}