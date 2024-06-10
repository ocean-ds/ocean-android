package br.com.useblu.oceands.components.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.FormatTypes
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
private fun OceanSimpleBalancePreview() {
    OceanTheme {
        Scaffold {
            OceanSimpleBalance(
                modifier = Modifier.padding(it),
                headerTitle = "Saldo na Blu",
                firstLabel = "Saldo total",
                firstValue = "R$ 1.500,00",
                secondLabel = "Saldo atual",
                secondValue = "R$ 1.000,00",
                thirdLabel = "Agenda",
                thirdValue = "R$ 500,00",
                onClickHideIcon = {},
                isContentHidden = false,
                isVisibleShadow = true
            )
        }

    }
}


@Composable
fun OceanSimpleBalance(
    modifier: Modifier = Modifier,
    headerTitle: String,
    firstLabel: String,
    firstValue: String,
    secondLabel: String,
    secondValue: String,
    thirdLabel: String,
    thirdValue: String,
    onClickHideIcon: (isContentHidden: Boolean) -> Unit = {},
    isContentHidden: Boolean,
    isVisibleShadow: Boolean
) {
    var isContentHiddenInternal by remember(isContentHidden) {
        mutableStateOf(isContentHidden)
    }

    val onClickToggleHideContent = {
        isContentHiddenInternal = !isContentHiddenInternal
        onClickHideIcon(isContentHiddenInternal)
    }

    var isContentExpanded by remember {
        mutableStateOf(false)
    }

    val animatedRotation = animateFloatAsState(
        targetValue = if (isContentExpanded) 180f else 0f,
        label = "Expand rotation"
    )

    val formattedFirstValue = if (isContentHiddenInternal) {
        FormatTypes.FORMAT_VALUE_WITH_SYMBOL_HIDDEN
    } else {
        FormatTypes.FORMAT_VALUE_WITH_SYMBOL
    }.format(firstValue)

    val formattedSecondValue = if (isContentHiddenInternal) {
        FormatTypes.FORMAT_VALUE_WITH_SYMBOL_HIDDEN
    } else {
        FormatTypes.FORMAT_VALUE_WITH_SYMBOL
    }.format(secondValue)

    val formattedThridValue = if (isContentHiddenInternal) {
        FormatTypes.FORMAT_VALUE_WITH_SYMBOL_HIDDEN
    } else {
        FormatTypes.FORMAT_VALUE_WITH_SYMBOL
    }.format(thirdValue)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = OceanColors.interfaceLightPure)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(OceanSpacing.xxxs)
                    .size(OceanSpacing.xl)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = onClickToggleHideContent
                    )
            ) {
                val icon = if (isContentHiddenInternal) {
                    OceanIcons.EYE_OFF_OUTLINE
                } else OceanIcons.EYE_OUTLINE
                OceanIcon(
                    iconType = icon,
                    tint = OceanColors.interfaceDarkUp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(OceanSpacing.sm),
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = OceanSpacing.xxxs)
                    .weight(1f)
            ) {
                if (isContentExpanded) {
                    OceanText(
                        modifier = Modifier
                            .padding(horizontal = OceanSpacing.xxxs),
                        text = headerTitle,
                        fontSize = OceanFontSize.xs,
                        color = OceanColors.brandPrimaryPure,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    OceanText(
                        text = firstLabel,
                        fontSize = OceanFontSize.xxxs,
                        color = OceanColors.interfaceDarkDeep,
                        fontWeight = FontWeight.SemiBold
                    )

                    OceanText(
                        text = formattedFirstValue,
                        fontSize = OceanFontSize.xxs,
                        color = OceanColors.interfaceDarkDeep,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Box(modifier = Modifier
                .padding(OceanSpacing.xxxs)
                .size(OceanSpacing.xl)
                .clickable(
                    enabled = true,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) {
                    isContentExpanded = !isContentExpanded
                }
            ) {
                OceanIcon(
                    iconType = OceanIcons.CHEVRON_DOWN_OUTLINE,
                    tint = OceanColors.interfaceDarkUp,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                        .rotate(animatedRotation.value)
                )
            }
        }

        AnimatedVisibility(
            visible = isContentExpanded
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = OceanSpacing.xs)
                    .padding(bottom = OceanSpacing.xxsExtra)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OceanText(text = firstLabel)
                    OceanText(text = formattedFirstValue)
                }

                OceanDivider(Modifier.padding(vertical = OceanSpacing.xxsExtra))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OceanText(text = secondLabel)
                    OceanText(text = formattedSecondValue)
                }

                OceanDivider(Modifier.padding(vertical = OceanSpacing.xxsExtra))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OceanText(text = thirdLabel)
                    OceanText(text = formattedThridValue)
                }
            }
        }

        if (isVisibleShadow) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(OceanSpacing.xxs)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0x0C0D1414),
                                Color.Transparent
                            )
                        )
                    )
            )
        }
    }
}