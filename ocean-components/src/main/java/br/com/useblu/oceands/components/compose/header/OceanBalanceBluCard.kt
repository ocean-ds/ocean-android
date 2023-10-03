package br.com.useblu.oceands.components.compose.header

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.shimmeringBrush
import br.com.useblu.oceands.model.compose.OceanBalanceBluModel
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.FormatTypes.Companion.FORMAT_VALUE_WITH_SYMBOL
import br.com.useblu.oceands.utils.FormatTypes.Companion.FORMAT_VALUE_WITH_SYMBOL_HIDDEN
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun OceanBalanceBluCardPreview() {
    val isContentHidden = remember { mutableStateOf(false) }
    val model = OceanBalanceBluModel(
        firstLabel = "First Label",
        firstValue = "First Value",
        secondLabel = "Second Label",
        secondValue = "Second Value",
        thirdLabel = "Third Label",
        thirdValue = "Third Value",
        buttonCta = "Extrato",
        buttonDescription = "Confira tudo o que entrou e saiu da sua Conta Digital Blu",
        onClickButton = {
            println("Click extrato")
        }
    )
    OceanTheme {
        OceanBalanceBluCard(
            model,
            isLoading = true,
            isContentHidden = isContentHidden.value,
            onClickToggleHideContent = {
                isContentHidden.value = !isContentHidden.value
            }
        )
    }
}

@Composable
fun OceanBalanceBluCard(
    model: OceanBalanceBluModel,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isCurrentPage: Boolean = true,
    isContentHidden: Boolean = false,
    onClickToggleHideContent: () -> Unit
) {
    val isExpanded = remember(isCurrentPage) {
        mutableStateOf(false)
    }
    val shimmeringBrush = shimmeringBrush()

    Column(
        modifier = modifier
            .background(
                color = Color(0xFF2244E8),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        BluCardTopBar(
            isContentHidden = isContentHidden,
            model = model,
            isLoading = isLoading,
            shimmeringBrush = shimmeringBrush,
            isExpanded = isExpanded.value,
            isCurrentPage = isCurrentPage,
            onClickToggleHideContent = onClickToggleHideContent,
            onClickExpandContent = {
                isExpanded.value = !isExpanded.value
            }
        )

        AnimatedVisibility(visible = isExpanded.value) {
            ExpandableContent(
                model = model,
                isLoading = isLoading,
                shimmeringBrush = shimmeringBrush,
                isContentHidden = isContentHidden
            )
        }

        Column(
            modifier = Modifier
                .padding(bottom = 12.dp, top = 8.dp)
                .padding(horizontal = 16.dp)
        ) {
            Divider(color = OceanColors.brandPrimaryUp.copy(alpha = 0.4f))

            BluCardBottomBar(model, isCurrentPage)
        }
    }
}

@Composable
private fun BluCardTopBar(
    isContentHidden: Boolean,
    model: OceanBalanceBluModel,
    isLoading: Boolean,
    shimmeringBrush: Brush,
    isExpanded: Boolean,
    isCurrentPage: Boolean,
    onClickToggleHideContent: () -> Unit,
    onClickExpandContent: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BalanceCardMainValues(onClickToggleHideContent, isContentHidden, model, isLoading, shimmeringBrush)

        val animatedRotation = animateFloatAsState(
            targetValue = if (isExpanded) 180f else 0f,
            label = "Expand rotation"
        )

        Box(modifier = Modifier
            .size(40.dp)
            .clickable(
                enabled = isCurrentPage,
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) {
                onClickExpandContent()
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

@Composable
fun RowScope.BalanceCardMainValues(
    onClickToggleHideContent: () -> Unit,
    isContentHidden: Boolean,
    model: OceanBalanceBluModel,
    isLoading: Boolean,
    shimmeringBrush: Brush
) {
    Box(
        modifier = Modifier
            .height(40.dp)
            .width(28.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    onClickToggleHideContent()
                }
            )
    ) {
        val icon = if (isContentHidden) {
            OceanIcons.EYE_OFF_OUTLINE
        } else OceanIcons.EYE_OUTLINE
        OceanIcon(
            iconType = icon,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(20.dp),
            tint = OceanColors.brandPrimaryUp
        )
    }

    Column(
        modifier = Modifier.Companion
            .weight(1f)
            .padding(start = 8.dp)
    ) {
        Text(
            text = model.firstLabel,
            fontSize = 12.sp,
            modifier = Modifier.height(18.dp),
            color = OceanColors.brandPrimaryUp
        )

        if (isLoading) {
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .padding(vertical = 8.dp)
                    .width(72.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(shimmeringBrush)
            )
        } else {
            val text = if (isContentHidden) {
                FORMAT_VALUE_WITH_SYMBOL_HIDDEN
            } else {
                FORMAT_VALUE_WITH_SYMBOL
            }.format(model.firstValue)

            Text(
                text = text,
                fontSize = 20.sp,
                modifier = Modifier.height(30.dp),
                color = OceanColors.interfaceLightPure,
                fontFamily = OceanFontFamily.BaseBold
            )
        }
    }
}

@Composable
private fun ExpandableContent(
    model: OceanBalanceBluModel,
    isLoading: Boolean,
    shimmeringBrush: Brush = shimmeringBrush(),
    isContentHidden: Boolean
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 4.dp)
    ) {
        ExpandableContentTextRow(
            isLoading = isLoading,
            shimmeringBrush = shimmeringBrush,
            isContentHidden = isContentHidden,
            label = model.secondLabel,
            value = model.secondValue
        )

        Spacer(modifier = Modifier.size(12.dp))

        Divider(color = OceanColors.brandPrimaryUp.copy(alpha = 0.4f))

        ExpandableContentTextRow(
            isLoading = isLoading,
            shimmeringBrush = shimmeringBrush,
            isContentHidden = isContentHidden,
            label = model.thirdLabel,
            value = model.thirdValue
        )
    }
}

@Composable
private fun ExpandableContentTextRow(
    isLoading: Boolean,
    shimmeringBrush: Brush,
    isContentHidden: Boolean,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.padding(top = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            fontFamily = OceanFontFamily.BaseBold,
            color = OceanColors.interfaceLightPure
        )

        Spacer(modifier = Modifier.size(16.dp))

        if (isLoading) {
            Box(
                modifier = Modifier
                    .height(14.dp)
                    .width(72.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(shimmeringBrush)
                    .padding(vertical = 8.dp)
            )
        } else {
            val text = if (isContentHidden) {
                FORMAT_VALUE_WITH_SYMBOL_HIDDEN
            } else {
                FORMAT_VALUE_WITH_SYMBOL
            }.format(value)

            Text(
                text = text,
                fontFamily = OceanFontFamily.BaseBold,
                color = OceanColors.interfaceLightPure
            )
        }
    }
}

@Composable
private fun BluCardBottomBar(
    model: OceanBalanceBluModel,
    isCurrentPage: Boolean
) {
    Row(
        modifier = Modifier.padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = model.buttonDescription, modifier = Modifier.weight(1f),
            style = OceanTextStyle.description,
            color = OceanColors.interfaceLightDown
        )

        Spacer(modifier = Modifier.size(16.dp))

        OceanButton(
            text = model.buttonCta,
            buttonStyle = OceanButtonStyle.SecondarySmall,
            onClick = {
                if (isCurrentPage) {
                    model.onClickButton()
                }
            }
        )
    }
}