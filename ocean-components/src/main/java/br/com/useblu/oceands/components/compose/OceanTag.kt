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
import br.com.useblu.oceands.model.OceanIconType
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing


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
        }

        OceanSpacing.StackXS()

        Column {
            OceanTag(
                label = "Label",
                iconToken = OceanIconType.PLACEHOLDER_SOLID,
                type = OceanTagType.Positive
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                iconToken = OceanIconType.PLACEHOLDER_SOLID,
                type = OceanTagType.Warning
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                iconToken = OceanIconType.PLACEHOLDER_SOLID,
                type = OceanTagType.Negative
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                iconToken = OceanIconType.PLACEHOLDER_SOLID,
                type = OceanTagType.Complementary
            )

            OceanSpacing.StackXXXS()

            OceanTag(
                label = "Label",
                iconToken = OceanIconType.PLACEHOLDER_SOLID,
                type = OceanTagType.Neutral
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
        }
    }
}

@Composable
fun OceanTag(
    label: String,
    type: OceanTagType,
    iconToken: OceanIconType? = null,
    isSmall: Boolean = false
) {
    val size = if (isSmall) 16.dp else 20.dp
    val textColor = getTextColor(type = type)
    val backgroundColor = getBackgroundColor(type = type)

    Row(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .height(size),
        verticalAlignment = Alignment.CenterVertically

    ) {

        if (iconToken != null) {
            Spacer(modifier = Modifier.size(6.dp))

            OceanIcon(
                iconType = iconToken,
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
            fontSize = if (isSmall) 10.sp else 12.sp
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
        OceanTagType.Complementary -> {
            Color(0xFFEEFAFA)
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
    }
}
@Composable
fun getTextColor(type: OceanTagType): Color {
    return when (type) {
        OceanTagType.Neutral -> {
            OceanColors.interfaceDarkUp
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
    }
}
