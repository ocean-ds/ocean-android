package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.compose.OceanBalanceBluModel
import br.com.useblu.oceands.model.compose.OceanBalanceOthersModel
import br.com.useblu.oceands.model.compose.OceanHeaderAppModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.utils.OceanIcons


@Preview(device = "spec:width=1800px,height=2340px,dpi=440,orientation=portrait")
@Composable
fun OceanHeaderCardsPreview() {
    val bluModel = OceanBalanceBluModel(
        firstLabel = "First Label",
        firstValue = "First Value",
        secondLabel = "Second Label",
        secondValue = "Second Value",
        thirdLabel = "Third Label",
        thirdValue = "Third Value",
        buttonCta = "Extrato",
        buttonDescription = "Confira tudo o que entrou e saiu da sua Conta Digital Blu"
    )
    val othersModel = OceanBalanceOthersModel(
        title = "Saldo em Outras maquininhas",
        description = "Receba na Blu as vendas feitas nas suas outras maquininhas",
        buttonCta = "Extrato",
        buttonCtaCollapsed = "Extrato",
        onClickButton = {}
    )
    Row {
        OceanBalanceBluCard(model = bluModel, modifier = Modifier.weight(1f))
        OceanBalanceOthersCard(model = othersModel, modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun OceanHeaderAppPreview() {
    val model = OceanHeaderAppModel(
        clientName = "Fcr Colchões",
        formattedCnpj = "32.677.554/0001-14"
    )
    OceanHeaderApp(
        model
    )
}

@Composable
fun OceanHeaderApp(
    model: OceanHeaderAppModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = OceanColors.brandPrimaryPure)
    ) {
        Header(model = model)
    }
}

@Composable
fun Header(
    model: OceanHeaderAppModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .clickable { model.onClickTitle() }
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = model.clientName,
                    fontFamily = OceanFontFamily.HighlightExtraBold,
                    color = OceanColors.interfaceLightPure,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.size(2.dp))
                OceanIcon(
                    iconType = OceanIcons.CHEVRON_DOWN_SOLID,
                    modifier = Modifier.size(16.dp),
                    tint = OceanColors.interfaceLightPure
                )
            }

            Spacer(modifier = Modifier.size(4.dp))

            Text(
                text = model.formattedCnpj,
                fontFamily = OceanFontFamily.BaseRegular,
                color = OceanColors.interfaceLightPure,
                fontSize = 12.sp
            )
        }

        OceanBluPlus(
            bluPlusValue = model.bluPlusValue,
            onClick = model.onClickBluPlus
        )

        Spacer(modifier = Modifier.size(4.dp))

        HeaderMenu(
            model.badgeCount,
            model.onClickMenu
        )

        Spacer(modifier = Modifier.size(4.dp))
    }
}

@Composable
private fun HeaderMenu(
    badgeCount: Int,
    onClickMenu: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clickable {
                onClickMenu()
            }
    ) {
        OceanIcon(
            iconType = OceanIcons.MENU_OUTLINE,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center),
            tint = OceanColors.interfaceLightPure
        )

        if (badgeCount > 0) {
            OceanBadge(
                text = badgeCount.toString(),
                type = OceanBadgeType.WARNING,
                size = OceanBadgeSize.Small,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
            )
        }
    }
}
