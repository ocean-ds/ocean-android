package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.extensions.compose.height
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.model.OceanOptionCardItem
import br.com.useblu.oceands.model.OceanOptionCardSize
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
fun OceanCardOptionPreview() {
    val fakeOptions = listOf(
        OceanOptionCardItem(
            data = Any(),
            icon = OceanIcons.RETAILER_OUTLINE.token,
            title = "Seu Negócio",
            subTitle = "Taxas e tarifas",
            disabled = false,
            recommend = false
        ),
        OceanOptionCardItem(
            data = Any(),
            icon = "academiccapoutline",
            title = "PagBlu com prazo",
            subTitle = "Pague em 05/12/2021 com saldo futuro sem taxa de antecipação",
            disabled = true
        ),
        OceanOptionCardItem(
            data = Any(),
            icon = "academiccapsolid",
            title = "Title 2",
            subTitle = "Subtitle 2",
            recommend = true,
            recommendColor = "colorstatusneutraldeep",
            recommendDescription = "Recomendado"
        ),
        OceanOptionCardItem(
            data = Any(),
            icon = "academiccapsolid",
            heightSize = OceanOptionCardSize.SMALL,
            title = "Title 2",
            subTitle = "Subtitle 2",
            recommend = true,
            recommendColor = "colorstatuspositivepure",
            recommendDescription = "Aproveite o cashback"
        )
    )

    OceanTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            fakeOptions.forEach {
                OceanCardOption(
                    item = it,
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
private fun OceanCardOption02Preview() {
    OceanCardOption(
        item = OceanOptionCardItem(
            data = Any(),
            icon = "academiccapsolid",
            title = "PagBlu",
            subTitle = "Economize até 10% usando saldo futuro sem taxa de antecipação",
            disabled = false,
            recommend = false,
            heightSize = OceanOptionCardSize.LARGE
        ),
        showBackgroundIcon = false,
        onClick = {}
    )
}

@Preview
@Composable
private fun OceanCardOption03Preview() {
    OceanCardOption(
        item = OceanOptionCardItem(
            data = Any(),
            icon = "academiccapsolid",
            title = "PagBlu",
            subTitle = "Economize até 10% usando saldo futuro sem taxa de antecipação",
            disabled = false,
            recommend = false
        ),
        showBackgroundIcon = true,
        onClick = {}
    )
}

@Preview
@Composable
private fun OceanCardOption04Preview() {
    OceanCardOption(
        item = OceanOptionCardItem(
            data = Any(),
            icon = "academiccapsolid",
            title = "PagBlu",
            subTitle = "Economize até 10% usando saldo futuro sem taxa de antecipação",
            disabled = false,
            recommend = false
        ),
        sizeIcon = OceanSpacing.xl,
        showBackgroundIcon = false,
        onClick = {}
    )
}

@Composable
fun OceanCardOption(
    modifier: Modifier = Modifier,
    item: OceanOptionCardItem,
    showBackgroundIcon: Boolean = false,
    sizeIcon: Dp = OceanSpacing.sm,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) OceanColors.brandPrimaryUp else OceanColors.interfaceLightDown
    val cardBackgroundColor = when {
        item.disabled -> OceanColors.interfaceLightPure
        isSelected -> OceanColors.interfaceLightUp
        else -> OceanColors.interfaceLightPure
    }

    val density = LocalDensity.current
    var cardHeight by remember { mutableStateOf(0.dp) }

    Card(
        modifier = modifier
            .onSizeChanged {
                density.run {
                    cardHeight = it.height.toDp()
                }
            },
        shape = OceanBorderRadius.SM.allCorners.shape(),
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor
        ),
        border = BorderStroke(1.dp, borderColor),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
        ) {
            Row {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = OceanSpacing.md),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val contentPadding = if (item.heightSize == OceanOptionCardSize.SMALL) {
                        OceanSpacing.xs
                    } else OceanSpacing.sm

                    if (item.icon != null) {
                        val backgroundColor = when {
                            isSelected -> OceanColors.brandPrimaryDown
                            else -> OceanColors.interfaceLightUp
                        }

                        val iconColor = when {
                            item.disabled -> OceanColors.interfaceDarkUp
                            isSelected -> OceanColors.interfaceLightUp
                            else -> OceanColors.brandPrimaryDown
                        }

                        Box(
                            modifier = Modifier
                                .padding(start = contentPadding)
                                .iconContainerBackground(showBackgroundIcon, backgroundColor),
                            contentAlignment = Alignment.Center
                        ) {
                            OceanIcon(
                                modifier = Modifier
                                    .padding(if (showBackgroundIcon) OceanSpacing.xxsExtra else 0.dp)
                                    .size(sizeIcon),
                                iconType = OceanIcons.fromToken(item.icon),
                                tint = iconColor
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(start = OceanSpacing.xxsExtra, end = OceanSpacing.xxs)
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs)
                    ) {
                        OceanText(
                            text = item.title ?: "",
                            style = OceanTextStyle.heading4,
                            color = if (item.disabled) OceanColors.interfaceDarkDown else OceanColors.interfaceDarkDeep
                        )

                        if (item.heightSize != OceanOptionCardSize.SMALL) {
                            OceanText(
                                text = item.subTitle ?: "",
                                color = OceanColors.interfaceDarkDown,
                                style = OceanTextStyle.description
                            )
                        }
                    }
                }

                if (item.disabled) {
                    Box(
                        modifier = Modifier
                            .height(cardHeight)
                            .borderBackground(
                                color = OceanColors.interfaceLightDown,
                                borderRadius = OceanBorderRadius
                                    .SM(corners = setOf(OceanBorderRadius.Corners.TopEnd, OceanBorderRadius.Corners.BottomEnd))
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        OceanIcon(
                            modifier = Modifier
                                .padding(horizontal = OceanSpacing.xxs),
                            iconType = OceanIcons.LOCK_CLOSED_SOLID,
                            tint = OceanColors.interfaceDarkUp
                        )
                    }
                }
            }

            if (item.recommend) {
                OceanTagRecommended(
                    title = item.recommendDescription ?: "",
                    color = OceanColors.fromString(item.recommendColor ?: ""),
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
    }
}

@Composable
fun OceanTagRecommended(
    modifier: Modifier = Modifier,
    title: String,
    color: Color
) {
    Box(
        modifier = modifier
            .borderBackground(
                color = color,
                borderRadius = OceanBorderRadius.SM(
                    corners = setOf(
                        OceanBorderRadius.Corners.TopEnd,
                        OceanBorderRadius.Corners.BottomStart
                    )
                )
            )
            .padding(OceanSpacing.xxs)
    ) {
        OceanText(
            text = title,
            fontFamily = OceanFontFamily.BaseExtraBold,
            color = OceanColors.interfaceLightPure
        )
    }
}
