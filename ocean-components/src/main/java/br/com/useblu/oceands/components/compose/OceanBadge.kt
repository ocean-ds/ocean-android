package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily


@Preview(showBackground = true)
@Composable
fun OceanBadgePreview() {
    Row {
        Column {
            OceanBadge(
                text = "120",
                OceanBadgeType.PRIMARY,
                OceanBadgeSize.Small
            )

            OceanBadge(
                text = "12",
                OceanBadgeType.DISABLED,
                OceanBadgeSize.Small
            )

            OceanBadge(
                text = "1",
                OceanBadgeType.HIGHLIGHT,
                OceanBadgeSize.Small
            )

            OceanBadge(
                text = "",
                type = OceanBadgeType.HIGHLIGHT,
                size = OceanBadgeSize.Dot
            )
        }

        Column {
            OceanBadge(
                text = "120",
                type = OceanBadgeType.WARNING,
                size = OceanBadgeSize.Medium
            )

            OceanBadge(
                text = "12",
                type = OceanBadgeType.PRIMARY_INVERTED,
                size = OceanBadgeSize.Medium
            )

            OceanBadge(
                text = "1",
                type = OceanBadgeType.HIGHLIGHT,
                size = OceanBadgeSize.Medium
            )
        }
    }
}

@Composable
fun OceanBadge(
    text: String,
    type: OceanBadgeType,
    size: OceanBadgeSize
) {
    val backgroundColor = when (type) {
        OceanBadgeType.HIGHLIGHT -> OceanColors.highlightPure
        OceanBadgeType.PRIMARY -> OceanColors.brandPrimaryPure
        OceanBadgeType.PRIMARY_INVERTED -> OceanColors.interfaceLightPure
        OceanBadgeType.WARNING -> OceanColors.statusWarningDeep
        OceanBadgeType.DISABLED -> OceanColors.interfaceDarkUp
        OceanBadgeType.CHIP_HOVER -> OceanColors.interfaceLightPure
    }

    val formattedText = text.toIntOrNull()?.let {
        if (it > 99) {
            "99+"
        } else {
            it.toString()
        }
    } ?: text

    Row(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(40.dp)
            )
            .height(size.getMinSize())
            .defaultMinSize(minWidth = size.getMinSize()),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (size != OceanBadgeSize.Dot) {
            val padding = if (size is OceanBadgeSize.Small && formattedText.length <= 1) {
                0.dp
            } else {
                4.dp
            }

            Text(
                text = formattedText,
                fontSize = size.getTextSize(),
                fontFamily = OceanFontFamily.BaseExtraBold,
                modifier = Modifier.padding(horizontal = padding),
                color = if (type != OceanBadgeType.PRIMARY_INVERTED) OceanColors.interfaceLightPure else OceanColors.brandPrimaryPure
            )
        }
    }
}

sealed interface OceanBadgeSize {
    fun getTextSize() = 10.sp
    fun getMinSize() = 8.dp
    object Dot: OceanBadgeSize
    object Small: OceanBadgeSize {
        override fun getMinSize() = 16.dp
    }
    object Medium: OceanBadgeSize {
        override fun getMinSize() = 24.dp
        override fun getTextSize() = 12.sp
    }
}