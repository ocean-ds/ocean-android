package br.com.useblu.oceands.components.compose.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.model.compose.OceanBalanceOthersModel
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.borderBackground


@Preview
@Composable
private fun OceanBalanceOthersCardPreview() {
    val model = OceanBalanceOthersModel(
        title = "Saldo em Outras maquininhas",
        description = "Receba na Blu as vendas feitas nas suas outras maquininhas",
        buttonCta = "Extrato",
        buttonCtaCollapsed = "Extrato",
        onClickButton = {}
    )
    OceanBalanceOthersCard(
        model
    )
}

@Composable
fun OceanBalanceOthersCard(
    model: OceanBalanceOthersModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp)
            .borderBackground(
                color = Color(0xFF2244E8),
                borderRadius = OceanBorderRadius.SM.allCorners
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = model.title,
            fontSize = OceanFontSize.xxxs,
            color = OceanColors.brandPrimaryUp,
            fontFamily = OceanFontFamily.BaseBold,
            modifier = Modifier
                .height(18.dp)
                .padding(horizontal = OceanSpacing.xs)
        )

        OceanSpacing.StackXXXS()

        Text(
            text = model.description,
            fontSize = OceanFontSize.xxs,
            color = OceanColors.interfaceLightDown,
            fontFamily = OceanFontFamily.BaseRegular,
            modifier = Modifier.padding(horizontal = OceanSpacing.xs)
        )

        OceanSpacing.StackXXS()

        OceanButton(
            text = model.buttonCta,
            buttonStyle = OceanButtonStyle.SecondarySmall,
            onClick = {
                model.onClickButton()
            },
            modifier = Modifier.padding(horizontal = OceanSpacing.xs)
        )
    }
}
