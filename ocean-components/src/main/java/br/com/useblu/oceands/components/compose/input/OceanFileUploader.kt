package br.com.useblu.oceands.components.compose.input

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
fun OceanFileUploaderPreview() {
    OceanTheme {
        OceanFileUploader(
            title = "Selecione um arquivo do seu celular",
            subtitle = "O arquivo deve estar em formato PDF e ter no máximo 20MB."
        )

    }
}

@Composable
fun OceanFileUploader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = OceanColors.interfaceLightPure
            )
            .border(
                border = BorderStroke(1.dp)
            )
            .padding(vertical = 24.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OceanIcon(
            iconType = OceanIcons.UPLOAD_OUTLINE,
            tint = OceanColors.brandPrimaryPure
        )

        OceanSpacing.StackXXS()
        OceanSpacing.StackXXXS()

        OceanText(
            text = title,
            style = OceanTextStyle.description,
            fontWeight = FontWeight.SemiBold,
            color = OceanColors.brandPrimaryPure
        )

        OceanSpacing.StackXXS()

        OceanText(
            text = subtitle,
            style = OceanTextStyle.caption,
            color = OceanColors.interfaceDarkUp
        )
    }
}