package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
    Row {
        Column {
            OceanTag(
                label = "Label",
                type = OceanTagType.Positive
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                type = OceanTagType.Warning
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                type = OceanTagType.Negative
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                type = OceanTagType.Complementary
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                type = OceanTagType.Neutral
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                type = OceanTagType.NeutralPrimary
            )
        }

        OceanSpacing.StackXS()

        Column {
            OceanTag(
                label = "Label",
                icon = OceanIcons.LOCK_CLOSED_OUTLINE,
                type = OceanTagType.Positive
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                icon = OceanIcons.INFO_OUTLINE,
                type = OceanTagType.Warning
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                icon = OceanIcons.PLACEHOLDER_SOLID,
                type = OceanTagType.Negative
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                icon = OceanIcons.PLACEHOLDER_SOLID,
                type = OceanTagType.Complementary
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                icon = OceanIcons.PLACEHOLDER_SOLID,
                type = OceanTagType.Neutral
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                icon = OceanIcons.PLACEHOLDER_SOLID,
                type = OceanTagType.NeutralPrimary
            )
        }

        OceanSpacing.StackXS()

        Column {
            OceanTag(
                label = "Label",
                type = OceanTagType.Positive,
                isSmall = true
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                type = OceanTagType.Warning,
                isSmall = true
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                type = OceanTagType.Negative,
                isSmall = true
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                type = OceanTagType.Complementary,
                isSmall = true
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                type = OceanTagType.Neutral,
                isSmall = true
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                type = OceanTagType.NeutralPrimary,
                isSmall = true
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                type = OceanTagType.Important,
                isSmall = true
            )

            OceanSpacing.StackXXXS()

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
    label: String,
    type: OceanTagType,
    icon: OceanIcons? = null,
    isSmall: Boolean = false
) {
    val height = if (isSmall) 16.dp else 20.dp
    val textColor = getTextColor(type = type)
    val backgroundColor = getBackgroundColor(type = type)

    Row(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .height(height),
        verticalAlignment = Alignment.CenterVertically

    ) {

        if (icon != null) {
            Spacer(modifier = Modifier.size(6.dp))

            OceanIcon(
                iconType = icon,
                tint = textColor,
                modifier = Modifier.size(16.dp)
            )
        } else if (!isSmall) {
            OceanSpacing.StackXXXS()
        }

        OceanSpacing.StackXXXS()

        Text(
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
