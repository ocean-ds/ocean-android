package br.com.useblu.oceands.components.compose.cardlistitem.style

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.cardlistitem.ContentCardListItem
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

@Composable
internal fun HighlightedCardListItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    caption: String = "",
    tagStyle: OceanTagStyle? = null,
    type: OceanCardListItemType,
    style: OceanCardListItemStyle.Highlighted,
    disabled: Boolean = false,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val transition = rememberInfiniteTransition()
    val shadowElevation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = style.animation.duration
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val borderColor by transition.animateColor(
        initialValue = style.getBorderColor(
            isSelected = isSelected,
            type = type
        ),
        targetValue = style.animation.targetBorderColor,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = style.animation.duration
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    BaseCardListItem(
        modifier = modifier
            .shadow(
                elevation = OceanSpacing.xs * shadowElevation,
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
                tagStyle = tagStyle,
                type = type,
                style = style,
                isSelected = isSelected,
                disabled = disabled
            )

            HighlightedCardListItem(style = style)
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
            .padding(bottom = OceanSpacing.xs),
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
