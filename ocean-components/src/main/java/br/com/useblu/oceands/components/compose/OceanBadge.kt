package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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
                text = "1",
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
    }

    val formattedText = text.toIntOrNull()?.let {
        if (it > 99) {
            "99+"
        } else {
            it.toString()
        }
    } ?: text

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (size != OceanBadgeSize.Dot) {
            val padding = if (formattedText.length == 1) {
                7.dp
            } else {
                4.dp
            }

            Text(
                modifier = Modifier.padding(
                    horizontal = padding
                ),
                text = formattedText,
                textAlign = TextAlign.Center,
                fontSize = size.getTextSize(),
                fontFamily = OceanFontFamily.BaseExtraBold,
                color = if (type != OceanBadgeType.PRIMARY_INVERTED) {
                    OceanColors.interfaceLightPure
                } else {
                    OceanColors.brandPrimaryPure
                }
            )
        }
    }
}

sealed interface OceanBadgeSize {
    fun getTextSize() = 10.sp
    fun getMinSize() = 8.dp

    data object Dot : OceanBadgeSize
    data object Small : OceanBadgeSize {
        override fun getMinSize() = 16.dp
    }

    data object Medium : OceanBadgeSize {
        override fun getMinSize() = 24.dp
        override fun getTextSize() = 12.sp
    }
}
