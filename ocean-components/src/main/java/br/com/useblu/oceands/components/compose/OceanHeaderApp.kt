package br.com.useblu.oceands.components.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.compose.OceanBalanceBluModel
import br.com.useblu.oceands.model.compose.OceanBalanceOthersModel
import br.com.useblu.oceands.model.compose.OceanHeaderAppModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.utils.OceanIcons


private val modelPreview = OceanHeaderAppModel(
    clientName = "Fcr Colchões",
    formattedCnpj = "32.677.554/0001-14",
    balanceBluModel = OceanBalanceBluModel(
        firstLabel = "First Label",
        firstValue = "-35,63",
        secondLabel = "Second Label",
        secondValue = "10,00",
        thirdLabel = "Third Label",
        thirdValue = "50,00",
        buttonCta = "Extrato",
        buttonDescription = "Confira tudo o que entrou e saiu da sua Conta Digital Blu"
    ),
    balanceOthersModel = OceanBalanceOthersModel(
        title = "Saldo em Outras maquininhas",
        description = "Receba na Blu as vendas feitas nas suas outras maquininhas",
        buttonCta = "Extrato",
        buttonCtaCollapsed = "Extrato"
    ),
    isLoading = false
)

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun OceanHeaderCardsPreview() {
    Column {
        OceanBalanceBluCard(model = modelPreview.balanceBluModel)
        OceanBalanceOthersCard(model = modelPreview.balanceOthersModel)
    }
}

@Preview
@Composable
fun OceanHeaderAppPreview() {
    OceanHeaderApp(
        modelPreview
    )
}

@ExperimentalFoundationApi
private val balancePageSize = object : PageSize {
    override fun Density.calculateMainAxisPageSize(
        availableSpace: Int,
        pageSpacing: Int
    ): Int {
        return availableSpace - 24.dp.roundToPx()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OceanHeaderApp(
    model: OceanHeaderAppModel,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = modifier
            .background(color = OceanColors.brandPrimaryPure)
    ) {
        Header(model = model)

        HorizontalPager(
            pageCount = 2,
            state = pagerState,
            pageSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSize = balancePageSize,
            verticalAlignment = Alignment.Top
        ) { page ->
            when (page) {
                0 -> {
                    OceanBalanceBluCard(
                        model = model.balanceBluModel,
                        isLoading = model.isLoading,
                        pagerState = pagerState
                    )
                }
                1 -> {
                    OceanBalanceOthersCard(model = model.balanceOthersModel)
                }
            }
        }

        Spacer(modifier = Modifier.size(8.dp))

        PageIndicator(pagerState = pagerState)

        Spacer(modifier = Modifier.size(16.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PageIndicator(pagerState: PagerState) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(2) { iteration ->
            val isSelected = remember(pagerState.currentPage) {
                pagerState.currentPage == iteration
            }

            val color = animateColorAsState(
                targetValue = if (isSelected) OceanColors.interfaceLightUp else OceanColors.brandPrimaryDown,
                label = "Page Indicator Color",
                animationSpec = tween(300)
            )

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color.value)
                    .size(4.dp)
            )
        }
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
