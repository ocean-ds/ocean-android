package br.com.useblu.oceands.components.compose.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.model.compose.OceanBalanceOthersModel
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily


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
            .background(
                color = Color(0xFF2244E8),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = model.title,
            fontSize = 12.sp,
            modifier = Modifier.height(18.dp),
            color = OceanColors.brandPrimaryUp,
            fontFamily = OceanFontFamily.BaseBold
        )

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            text = model.description,
            fontSize = 14.sp,
            color = OceanColors.interfaceLightDown,
            fontFamily = OceanFontFamily.BaseRegular
        )

        Spacer(modifier = Modifier.size(8.dp))

        OceanButton(
            text = model.buttonCta,
            buttonStyle = OceanButtonStyle.SecondarySmall,
            onClick = {
                model.onClickButton
            }
        )
    }
}
