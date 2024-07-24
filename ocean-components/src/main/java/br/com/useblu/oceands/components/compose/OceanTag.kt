package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
                label = "Label",
                type = OceanTagType.Positive
            )

            OceanTag(
                label = "Label",
                type = OceanTagType.Warning
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
        if (!isSmall && showIcon) {
            Spacer(modifier = Modifier.size(6.dp))

            val finalIcon = icon ?: getIconDefault(type)

            if (finalIcon != null) {
                OceanIcon(
                    iconType = finalIcon,
                    tint = textColor,
                    modifier = Modifier.size(16.dp)
                )
            }
            OceanSpacing.StackXXXS()
        }

        if (isSmall) {
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
