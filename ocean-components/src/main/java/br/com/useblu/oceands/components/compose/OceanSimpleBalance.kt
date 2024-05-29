package br.com.useblu.oceands.components.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
private fun OceanSimpleBalancePreview() {
    OceanTheme {
        OceanSimpleBalance(
            headerTitle = "Saldo",
            firstLabel = "Saldo disponível",
            firstValue = "R$ 1.000,00",
            secondLabel = "Saldo a receber",
            secondValue = "R$ 1.000,00",
            thirdLabel = "Saldo a pagar",
            thirdValue = "R$ 1.000,00",
            fourthLabel = "Saldo total",
            fourthValue = "R$ 1.000,00",
            onClickHideIcon = {},
            isContentHidden = false,
            visibleShadow = true
        )
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
    fourthLabel: String,
    fourthValue: String,
    onClickHideIcon: (Boolean) -> Unit = {},
    isContentHidden: Boolean,
    visibleShadow: Boolean
) {
    var isContentHiddenInternal by remember(isContentHidden) {
        mutableStateOf(isContentHidden)
    }

    val onClickToggleHideContent = {
        isContentHiddenInternal = !isContentHiddenInternal
        onClickHideIcon(isContentHiddenInternal)
    }

    val animatedRotation = animateFloatAsState(
        targetValue = if (isContentHiddenInternal) 180f else 0f,
        label = "Expand rotation"
    )

    Column(
        modifier = modifier.fillMaxWidth()
            .background(color = OceanColors.interfaceLightPure)
            .padding(end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(48.dp)
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
                    tint = OceanColors.brandPrimaryUp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp),
                )
            }

            Column(
                modifier = Modifier.weight(1f)
                    .padding(horizontal = OceanSpacing.xxxs)
            ) {
                OceanText(text = firstLabel)
                OceanText(
                    text = firstValue,
                    fontSize = 14.sp,
                )
            }

            Box(modifier = Modifier
                .size(40.dp)
                .clickable(
                    enabled = true,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) {

                }
            ) {
                OceanIcon(
                    iconType = OceanIcons.CHEVRON_DOWN_OUTLINE,
                    tint = OceanColors.brandPrimaryUp,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterEnd)
                        .rotate(animatedRotation.value)
                )
            }
        }
    }
}