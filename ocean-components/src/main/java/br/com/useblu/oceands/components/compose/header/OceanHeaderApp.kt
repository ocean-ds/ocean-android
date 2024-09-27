package br.com.useblu.oceands.components.compose.header

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanBadge
import br.com.useblu.oceands.components.compose.OceanBadgeSize
import br.com.useblu.oceands.components.compose.OceanBluPlus
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.compose.OceanBalanceBluModel
import br.com.useblu.oceands.model.compose.OceanBalanceOthersModel
import br.com.useblu.oceands.model.compose.OceanHeaderAppModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons
import kotlinx.coroutines.launch


val modelPreview = OceanHeaderAppModel(
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

@Preview
@Composable
fun OceanHeaderAppPreview() {
    val modelPreview = remember {
        mutableStateOf(modelPreview)
    }
    LaunchedEffect(key1 = true) {
        modelPreview.value = modelPreview.value.copy(
            balanceBluModel = modelPreview.value.balanceBluModel.copy(
                onClickExpandScroll = {
                    modelPreview.value = modelPreview.value.copy(
                        isHeaderCollapsed = false
                    )
                }
            )
        )
    }
    OceanTheme {
        Column {
            OceanHeaderApp(modelPreview.value)

            OceanSpacing.StackXS()

            Button(
                onClick = {
                    modelPreview.value =
                        modelPreview.value.copy(
                            isHeaderCollapsed = !modelPreview.value.isHeaderCollapsed
                        )
                }
            ) {
                Text(text = "Toggle collapsed")
            }
        }
    }
}

@Preview
@Composable
private fun OceanHeaderAppPreviewHideBalance() {
    val modelPreview = remember {
        mutableStateOf(
            modelPreview.copy(hideBalance = true)
        )
    }

    OceanTheme {
        Column {
            OceanHeaderApp(modelPreview.value)

            OceanSpacing.StackXS()

            Button(
                onClick = {
                    modelPreview.value = modelPreview.value.copy(
                        isHeaderCollapsed = !modelPreview.value.isHeaderCollapsed
                    )
                }
            ) {
                Text(text = "Toggle collapsed")
            }
        }
    }
}

@ExperimentalFoundationApi
private val balancePageSize = object : PageSize {
    override fun Density.calculateMainAxisPageSize(
        availableSpace: Int,
        pageSpacing: Int
    ): Int {
        return availableSpace - 16.dp.roundToPx()
    }
}

@Deprecated("Use OceanHeader instead")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OceanHeaderApp(
    model: OceanHeaderAppModel,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val currentPage = remember(model.isHeaderCollapsed) {
        pagerState.currentPage
    }
    val coroutine = rememberCoroutineScope()
    val isContentHidden = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .background(color = OceanColors.brandPrimaryPure)
    ) {
        MinimalHeader(model = model)

        if (model.hideBalance) {
            return@Column
        }

        if (model.isHeaderCollapsed) {
            if (pagerState.currentPage == 0) {
                OceanBalanceBluCardCollapsed(
                    model = model.balanceBluModel,
                    isContentHidden = isContentHidden.value,
                    isLoading = model.isLoading,
                    onClickToggleHideContent = { isContentHidden.value = !isContentHidden.value },
                )
            } else {
                OceanBalanceOthersCardCollapsed(model = model.balanceOthersModel)
            }
        } else {
            LaunchedEffect(key1 = true) {
                coroutine.launch {
                    pagerState.scrollToPage(currentPage)
                }
            }
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = OceanSpacing.xs),
                pageSize = balancePageSize,
                pageSpacing = OceanSpacing.xxs,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .padding(top = OceanSpacing.xxxs)
            ) { page ->
                when (page) {
                    0 -> {
                        OceanBalanceBluCard(
                            model = model.balanceBluModel,
                            isLoading = model.isLoading,
                            isCurrentPage = pagerState.currentPage == 0,
                            isContentHidden = isContentHidden.value,
                            onClickToggleHideContent = {
                                isContentHidden.value = !isContentHidden.value
                            }
                        )
                    }

                    1 -> {
                        OceanBalanceOthersCard(model = model.balanceOthersModel)
                    }
                }
            }
        }

        AnimatedVisibility(visible = !model.isHeaderCollapsed) {
            Column {
                Spacer(modifier = Modifier.size(8.dp))
                PageIndicator(pagerState.currentPage)
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun PageIndicator(
    selectedIndex: Int
) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(2) { iteration ->
            val isSelected = selectedIndex == iteration

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
fun MinimalHeader(
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
                    fontSize = OceanFontSize.xs,
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
            Box(
                modifier = Modifier
                    .padding(OceanSpacing.xxs)
                    .align(Alignment.TopEnd)
            ) {
                OceanBadge(
                    text = badgeCount.toString(),
                    type = OceanBadgeType.WARNING,
                    size = OceanBadgeSize.Small,
                )
            }
        }
    }
}
