package br.com.useblu.oceands.components.compose.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.components.compose.OceanBadge
import br.com.useblu.oceands.components.compose.OceanBadgeSize
import br.com.useblu.oceands.components.compose.OceanBluPlus
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.header.model.OceanHeaderStyle
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.compose.OceanBalanceBluModel
import br.com.useblu.oceands.model.compose.OceanBalanceOthersModel
import br.com.useblu.oceands.model.compose.OceanHeaderAppModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons


private val modelPreview = OceanHeaderAppModel(
    clientName = "Fabricante 3 - Fluxo dia atual teste 2",
    formattedCnpj = "32.677.554/0001-14",
    balanceBluModel = OceanBalanceBluModel(
        firstLabel = "First Label",
        firstValue = "-35,63",
        secondLabel = "Second Label",
        secondValue = "10,00",
        thirdLabel = "Third Label",
        thirdValue = "50,00",
        buttonCta = "Extrato",
        buttonDescription = "Confira tudo o que entrou e saiu da sua Conta Digital Blu",
        onClickButton = {
            println("Click blu")
        }
    ),
    balanceOthersModel = OceanBalanceOthersModel(
        title = "Saldo em Outras maquininhas",
        description = "Receba na Blu as vendas feitas nas suas outras maquininhas",
        buttonCta = "Extrato",
        buttonCtaCollapsed = "Extrato",
        onClickButton = {
            println("Click others")
        }
    ),
    isLoading = false,
    isHeaderCollapsed = false
)

@Composable
@Preview
fun OceanHeaderMinimalPreview() {
    val modelPreview = remember {
        mutableStateOf(modelPreview)
    }

    OceanHeader(
        headerModel = modelPreview.value,
        style = OceanHeaderStyle.Minimal
    )
}

@Composable
@Preview
fun OceanHeaderSmallPreview() {
    var modelPreview by remember {
        mutableStateOf(
            modelPreview.copy(isHeaderCollapsed = true)
        )
    }

    LaunchedEffect(key1 = true) {
        modelPreview = modelPreview.copy(
            balanceBluModel = modelPreview.balanceBluModel.copy(
                onClickExpandScroll = {
                    modelPreview = modelPreview.copy(
                        isHeaderCollapsed = false
                    )
                }
            )
        )
    }

    OceanTheme {
        Column {
            OceanHeader(
                headerModel = modelPreview,
                style = OceanHeaderStyle.Small
            )

            OceanSpacing.StackXS()

            Button(
                onClick = {
                    modelPreview =
                        modelPreview.copy(
                            isHeaderCollapsed = !modelPreview.isHeaderCollapsed
                        )
                }
            ) {
                Text(text = "Toggle collapsed")
            }
        }
    }
}

@Composable
@Preview
fun OceanHeaderDefaultPreview() {
    val modelPreview = remember {
        mutableStateOf(
            modelPreview.copy(isHeaderCollapsed = false)
        )
    }

    OceanHeader(
        headerModel = modelPreview.value,
        style = OceanHeaderStyle.Small
    )
}


@Composable
fun OceanHeader(
    modifier: Modifier = Modifier,
    headerModel: OceanHeaderAppModel,
    style: OceanHeaderStyle = OceanHeaderStyle.Small,
) {
    Column(
        modifier = modifier
            .background(color = OceanColors.brandPrimaryPure),
    ) {
        MinimalHeader(model = headerModel)

        if (style is OceanHeaderStyle.Small) {
            SmallHeader(model = headerModel)
        }
    }
}

@Composable
private fun SmallHeader(
    model: OceanHeaderAppModel
) {
    var isContentHidden by remember { mutableStateOf(false) }

    when {
        model.hideBalance -> return
        model.isHeaderCollapsed -> {
            OceanBalanceBluCardCollapsed(
                model = model.balanceBluModel,
                isContentHidden = isContentHidden,
                isLoading = model.isLoading,
                onClickToggleHideContent = {
                    isContentHidden = !isContentHidden
                },
            )
        }

        else -> {
            Column(
                modifier = Modifier
                    .padding(horizontal = OceanSpacing.xs)
                    .padding(top = OceanSpacing.xxxs, bottom = OceanSpacing.xs)
            ) {
                OceanBalanceBluCard(
                    model = model.balanceBluModel,
                    isLoading = model.isLoading,
                    isCurrentPage = true,
                    isContentHidden = isContentHidden,
                    onClickToggleHideContent = {
                        isContentHidden = !isContentHidden
                    },
                )
            }
        }
    }
}

@Composable
private fun MinimalHeader(
    model: OceanHeaderAppModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(OceanColors.brandPrimaryPure)
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = OceanSpacing.xs)
                .clickable { model.onClickTitle() }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = model.clientName,
                    fontFamily = OceanFontFamily.HighlightExtraBold,
                    color = OceanColors.interfaceLightPure,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, false)
                )

                Spacer(modifier = Modifier.size(2.dp))

                OceanIcon(
                    iconType = OceanIcons.CHEVRON_DOWN_SOLID,
                    modifier = Modifier.size(16.dp),
                    tint = OceanColors.interfaceLightPure
                )
            }

            OceanSpacing.StackXXXS()

            Text(
                text = model.formattedCnpj,
                fontFamily = OceanFontFamily.BaseRegular,
                color = OceanColors.brandPrimaryUp,
                fontSize = OceanFontSize.xxxs
            )
        }

        if (!model.hideBluPlus) {
            OceanBluPlus(
                bluPlusValue = model.bluPlusValue,
                onClick = model.onClickBluPlus
            )

            OceanSpacing.StackXXXS()
        }

        MinimalHeaderMenu(
            model.badgeCount,
            model.onClickMenu
        )

        OceanSpacing.StackXXXS()
    }
}

@Composable
private fun MinimalHeaderMenu(
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
                    .padding(OceanSpacing.xxs)
                    .align(Alignment.TopEnd)
            )
        }
    }
}