package br.com.useblu.oceands.components.compose.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun OceanBalanceOthersCardCollapsedPreview() {
    val model = OceanBalanceOthersModel(
        description = "Receba na Blu as vendas feitas nas suas outras maquininhas",
        buttonCta = "Extrato",
        buttonCtaCollapsed = "Extrato"
    )

    OceanBalanceOthersCardCollapsed(
        model = model,
    )
}
@Composable
fun OceanBalanceOthersCardCollapsed(
    modifier: Modifier = Modifier,
    model: OceanBalanceOthersModel
) {
    Row(
        modifier = modifier
            .background(color = Color(0xFF2244E8))
            .height(58.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = model.description,
            fontSize = 12.sp,
            fontFamily = OceanFontFamily.BaseRegular,
            modifier = Modifier.weight(1f),
            color = OceanColors.interfaceLightPure
        )

        Spacer(modifier = Modifier.size(8.dp))

        OceanButton(
            text = model.buttonCta,
            buttonStyle = OceanButtonStyle.SecondarySmall,
            onClick = model.onClickButton
        )
    }
}