package br.com.useblu.oceands.components.compose.cardlistitem.style

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.cardlistitem.ContentCardListItem
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemContentStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import kotlinx.coroutines.delay

@Composable
internal fun HighlightedCardListItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    caption: String = "",
    contentStyle: OceanCardListItemContentStyle = OceanCardListItemContentStyle.Default,
    tagStyle: OceanTagStyle? = null,
    type: OceanCardListItemType,
    style: OceanCardListItemStyle.Highlighted,
    disabled: Boolean = false,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    var currentState by remember { mutableStateOf(if (isSelected) AnimateState.Stop else AnimateState.Start) }
    val transition = updateTransition(currentState)
    val shadowElevation by transition.animateFloat(
        transitionSpec = { transictionSpec<Float>()() }
    ) { state ->
        when (state) {
            AnimateState.Start -> 0f
            AnimateState.Mid -> 1f
            AnimateState.Stop -> 0f
        }
    }
    val baseBorderColor = style.getBorderColor(isSelected = isSelected, type = type)
    val borderColor by transition.animateColor(
        transitionSpec = { transictionSpec<Color>()() }
    ) { state ->
        when (state) {
            AnimateState.Start -> baseBorderColor
            AnimateState.Mid -> style.animation.targetBorderColor
            AnimateState.Stop -> baseBorderColor
        }
    }

    BaseCardListItem(
        modifier = modifier
            .shadow(
                elevation = OceanSpacing.xs * (shadowElevation),
                ambientColor = style.animation.shadowColor,
                spotColor = style.animation.shadowColor
            ),
        type = type,
        disabled = disabled,
        isSelected = isSelected,
        onClick = onClick,
        targetBorderColor = borderColor
    ) {
        Column {
            ContentCardListItem(
                title = title,
                description = description,
                caption = caption,
                contentStyle = contentStyle,
                tagStyle = tagStyle,
                type = type,
                style = style,
                isSelected = isSelected,
                disabled = disabled
            )

            HighlightedCardListItem(style = style)
        }
    }

    LaunchedEffect(currentState) {
        when (currentState) {
            AnimateState.Start -> {
                currentState = AnimateState.Mid
            }
            AnimateState.Mid -> {
                delay(AnimateState.Mid.animationTime().toLong())
                currentState = AnimateState.Stop
            }
            AnimateState.Stop -> Unit
        }
    }
}

@Composable
private fun HighlightedCardListItem(
    style: OceanCardListItemStyle.Highlighted
) {
    Row(
        modifier = Modifier
            .background(color = style.backgroundColor)
            .padding(top = OceanSpacing.xxs)
            .padding(horizontal = OceanSpacing.xs)
            .padding(bottom = OceanSpacing.xs)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = OceanSpacing.xxs)
    ) {
        OceanIcon(
            iconType = style.icon,
            tint = style.iconColor
        )

        OceanText(
            text = style.caption,
            style = OceanTextStyle.captionBold,
            color = OceanColors.interfaceDarkDown
        )
    }
}

@Composable
private fun <T> transictionSpec(): (Transition.Segment<AnimateState>.() -> FiniteAnimationSpec<T>) {
    return {
        when {
            AnimateState.Start isTransitioningTo AnimateState.Mid -> {
                tween(
                    delayMillis = AnimateState.Mid.delay(),
                    durationMillis = AnimateState.Mid.duration()
                )
            }

            AnimateState.Mid isTransitioningTo AnimateState.Stop -> {
                tween(
                    durationMillis = AnimateState.Stop.duration()
                )
            }

            else -> tween(durationMillis = 0)
        }
    }
}

private enum class AnimateState {
    Start,
    Mid,
    Stop;

    fun delay() = when (this) {
        Start -> 0
        Mid -> 800
        Stop -> 0
    }

    fun duration() = when (this) {
        Start -> 0
        Mid -> 600
        Stop -> 200
    }

    fun animationTime() = delay() + duration()
}
