package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OceanTagPreview() {
    Row(
        modifier = Modifier.padding(OceanSpacing.xxxs),
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs)
        ) {
            OceanTag(
                label = "<del>11,03%</del> por <b>7,33%</b>",
                type = OceanTagType.Positive
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.Warning,
                showIcon = false
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.Negative
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.Complementary
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.Neutral
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.NeutralPrimary
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs)
        ) {
            OceanTag(
                label = "Label",
                icon = OceanIcons.LOCK_CLOSED_OUTLINE,
                type = OceanTagType.Positive
            )

            OceanTag(
                label = "Label",
                icon = OceanIcons.INFO_OUTLINE,
                type = OceanTagType.Warning
            )

            OceanTag(
                label = "Label",
                icon = OceanIcons.PLACEHOLDER_SOLID,
                type = OceanTagType.Negative
            )

            OceanTag(
                label = "Label",
                icon = OceanIcons.PLACEHOLDER_SOLID,
                type = OceanTagType.Complementary
            )

            OceanTag(
                label = "Label",
                icon = OceanIcons.PLACEHOLDER_SOLID,
                type = OceanTagType.Neutral
            )

            OceanTag(
                label = "Label",
                icon = OceanIcons.PLACEHOLDER_SOLID,
                type = OceanTagType.NeutralPrimary
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs)
        ) {
            OceanTag(
                label = "Label",
                type = OceanTagType.Positive,
                isSmall = true
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.Warning,
                isSmall = true
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.Negative,
                isSmall = true
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.Complementary,
                isSmall = true
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.Neutral,
                isSmall = true
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.NeutralPrimary,
                isSmall = true
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.Important,
                isSmall = true
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.Highlight,
                isSmall = true
            )
        }
    }
}

@Composable
fun OceanTag(
    modifier: Modifier = Modifier,
    label: String,
    type: OceanTagType,
    showIcon: Boolean = true,
    icon: OceanIcons? = null,
    isSmall: Boolean = false
) {
    val height = if (isSmall) 16.dp else 20.dp
    val textColor = getTextColor(type = type)
    val backgroundColor = getBackgroundColor(type = type)

    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .height(height),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isSmall) {
            OceanSpacing.StackXXS()

            if (showIcon) {
                val finalIcon = icon ?: getIconDefault(type)

                if (finalIcon != null) {
                    OceanIcon(
                        iconType = finalIcon,
                        tint = textColor,
                        modifier = Modifier.size(16.dp)
                    )

                    OceanSpacing.StackXXXS()
                }
            }
        } else {
            OceanSpacing.StackXXXS()
        }

        OceanText(
            text = label,
            color = textColor,
            fontSize = if (isSmall) 10.sp else OceanFontSize.xxxs
        )

        if (isSmall) {
            OceanSpacing.StackXXXS()
        } else {
            OceanSpacing.StackXXS()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun OceanTagPreviewV2() {
    Row(
        modifier = Modifier.padding(OceanSpacing.xxxs),
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs)
        ) {
            OceanTag(
                style = OceanTagStyle.Default(
                    label = "<del>11,03%</del> por <b>7,33%</b>",
                    layout = OceanTagLayout.Medium(
                        icon = OceanIcons.CHECK_CIRCLE_SOLID
                    ),
                    type = OceanTagType.Positive
                ),
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Warning,
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(
                        icon = OceanIcons.X_CIRCLE_SOLID
                    ),
                    type = OceanTagType.Negative
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Complementary
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Neutral
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.NeutralPrimary
                )
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs)
        ) {
            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    type = OceanTagType.Positive,
                    layout = OceanTagLayout.Medium(
                        icon = OceanIcons.LOCK_CLOSED_OUTLINE,
                    )
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    type = OceanTagType.Warning,
                    layout = OceanTagLayout.Medium(
                        icon = OceanIcons.INFO_OUTLINE,
                    )
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    type = OceanTagType.Negative,
                    layout = OceanTagLayout.Medium(
                        icon = OceanIcons.PLACEHOLDER_SOLID,
                    )
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    type = OceanTagType.Complementary,
                    layout = OceanTagLayout.Medium(
                        icon = OceanIcons.PLACEHOLDER_SOLID,
                    )
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    type = OceanTagType.Neutral,
                    layout = OceanTagLayout.Medium(
                        icon = OceanIcons.PLACEHOLDER_SOLID,
                    )
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    type = OceanTagType.NeutralPrimary,
                    layout = OceanTagLayout.Medium(
                        icon = OceanIcons.PLACEHOLDER_SOLID,
                    )
                )
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs)
        ) {
            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    type = OceanTagType.Positive,
                    layout = OceanTagLayout.Small()
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    type = OceanTagType.Warning,
                    layout = OceanTagLayout.Small()
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    type = OceanTagType.Negative,
                    layout = OceanTagLayout.Small()
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    type = OceanTagType.Complementary,
                    layout = OceanTagLayout.Small()
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    type = OceanTagType.Neutral,
                    layout = OceanTagLayout.Small()
                )
            )

            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Label",
                    type = OceanTagType.NeutralPrimary,
                    layout = OceanTagLayout.Small()
                )
            )

            OceanTag(
                style = OceanTagStyle.Highlight(
                    label = "Label",
                    type = OceanTagType.Important,
                    layout = OceanTagLayout.Small()
                )
            )

            OceanTag(
                style = OceanTagStyle.Highlight(
                    label = "Label",
                    type = OceanTagType.Highlight,
                    layout = OceanTagLayout.Small()
                )
            )

        }
    }
}


@Composable
fun OceanTag(
    modifier: Modifier = Modifier,
    style: OceanTagStyle,
    enabled: Boolean = true
) {
    when (style) {
        is OceanTagStyle.Default -> {
            DefaultTag(
                modifier = modifier,
                style = style,
                layout = style.layout,
                enabled = enabled
            )
        }

        is OceanTagStyle.Highlight -> {
            HighlightTag(
                modifier = modifier,
                style = style,
            )
        }
    }
}

@Composable
private fun DefaultTag(
    modifier: Modifier,
    style: OceanTagStyle.Default,
    layout: OceanTagLayout,
    enabled: Boolean
) {
    when (layout) {
        is OceanTagLayout.Medium -> DefaultMediumTag(
            modifier = modifier,
            style = style,
            layout = layout,
            enabled
        )

        is OceanTagLayout.Small -> DefaultSmallTag(
            modifier = modifier,
            style = style,
            layout = layout,
            enabled = enabled
        )
    }
}

@Composable
private fun DefaultMediumTag(
    modifier: Modifier,
    style: OceanTagStyle.Default,
    layout: OceanTagLayout.Medium,
    enabled: Boolean
) {
    val textColor = getTextColor(
        type = if(enabled) style.type else OceanTagType.Neutral
    )
    val backgroundColor = getBackgroundColor(type = style.type)

    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .height(layout.height)
            .padding(horizontal = OceanSpacing.xxs),
        verticalAlignment = Alignment.CenterVertically
    ) {

        layout.icon?.let {
            OceanIcon(
                iconType = layout.icon,
                tint = textColor,
                modifier = Modifier
                    .padding(end = OceanSpacing.xxxs)
                    .size(16.dp),
            )
        }

        OceanText(
            text = style.label,
            color = textColor,
            fontSize = layout.fontSize
        )
    }
}

@Composable
private fun DefaultSmallTag(
    modifier: Modifier,
    style: OceanTagStyle.Default,
    layout: OceanTagLayout.Small,
    enabled: Boolean
) {
    val textColor = getTextColor(
        type = if(enabled) style.type else OceanTagType.Neutral
    )
    val backgroundColor = getBackgroundColor(type = style.type)

    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .height(layout.height)
            .padding(horizontal = OceanSpacing.xxxs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OceanText(
            text = style.label,
            color = textColor,
            fontSize = layout.fontSize
        )
    }
}

@Composable
private fun HighlightTag(
    modifier: Modifier,
    style: OceanTagStyle.Highlight,
) {
    val textColor = getTextColor(type = style.type)
    val backgroundColor = getBackgroundColor(type = style.type)

    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .height(style.layout.height)
            .padding(horizontal = OceanSpacing.xxxs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OceanText(
            text = style.label,
            color = textColor,
            fontSize = style.layout.fontSize
        )
    }
}

fun getIconDefault(
    type: OceanTagType
): OceanIcons? = when (type) {

    OceanTagType.Negative -> {
        OceanIcons.X_CIRCLE_SOLID
    }

    OceanTagType.Positive -> {
        OceanIcons.CHECK_CIRCLE_SOLID
    }

    OceanTagType.Warning -> {
        OceanIcons.EXCLAMATION_CIRCLE_SOLID
    }

    else -> null
}

@Composable
fun getBackgroundColor(type: OceanTagType): Color {
    return when (type) {
        OceanTagType.Neutral -> {
            OceanColors.interfaceLightUp
        }

        OceanTagType.NeutralPrimary -> {
            OceanColors.interfaceLightUp
        }

        OceanTagType.Complementary -> {
            OceanColors.complementaryPure.copy(alpha = 0.08f)
        }

        OceanTagType.Negative -> {
            OceanColors.statusNegativeUp
        }

        OceanTagType.Positive -> {
            OceanColors.statusPositiveUp
        }

        OceanTagType.Warning -> {
            OceanColors.statusWarningUp
        }

        OceanTagType.Important -> {
            OceanColors.highlightPure
        }

        OceanTagType.Highlight -> {
            OceanColors.brandPrimaryDown
        }
    }
}

@Composable
fun getTextColor(type: OceanTagType): Color {
    return when (type) {
        OceanTagType.Neutral -> {
            OceanColors.interfaceDarkUp
        }

        OceanTagType.NeutralPrimary -> {
            OceanColors.brandPrimaryDown
        }

        OceanTagType.Complementary -> {
            OceanColors.complementaryPure
        }

        OceanTagType.Negative -> {
            OceanColors.statusNegativePure
        }

        OceanTagType.Positive -> {
            OceanColors.statusPositiveDeep
        }

        OceanTagType.Warning -> {
            OceanColors.statusWarningDeep
        }

        OceanTagType.Important, OceanTagType.Highlight -> {
            OceanColors.interfaceLightPure
        }
    }
}

sealed interface OceanTagStyle {
    data class Default(
        val label: String,
        val layout: OceanTagLayout,
        val type: OceanTagType = OceanTagType.Warning,
    ) : OceanTagStyle

    data class Highlight(
        val label: String,
        val type: OceanTagType = OceanTagType.Warning,
        val layout: OceanTagLayout.Small
    ) : OceanTagStyle
}

sealed interface OceanTagLayout {
    data class Medium(
        val icon: OceanIcons? = null,
        val height: Dp = 20.dp,
        val fontSize: TextUnit = 12.sp
    ) : OceanTagLayout

    data class Small(
        val height: Dp = 16.dp,
        val fontSize: TextUnit = 10.sp,
    ) : OceanTagLayout
}
