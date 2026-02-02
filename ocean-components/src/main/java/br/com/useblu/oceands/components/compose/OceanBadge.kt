package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.model.Badge
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily

@Composable
fun OceanBadge(
    text: String,
    type: OceanBadgeType,
    size: OceanBadgeSize,
    modifier: Modifier = Modifier,
    prefix: String = ""
) {
    val backgroundColor = when (type) {
        OceanBadgeType.HIGHLIGHT -> OceanColors.highlightPure
        OceanBadgeType.PRIMARY -> OceanColors.brandPrimaryPure
        OceanBadgeType.PRIMARY_INVERTED -> OceanColors.interfaceLightPure
        OceanBadgeType.WARNING -> OceanColors.statusWarningDeep
        OceanBadgeType.DISABLED -> OceanColors.interfaceDarkUp
    }

    val formattedText = prefix + (
        text.toIntOrNull()?.let {
            if (it > 99) {
                "99+"
            } else {
                it.toString()
            }
        } ?: text
        )

    val count = formattedText.length

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .then(
                if (size != OceanBadgeSize.Dot) {
                    Modifier.defaultMinSize(
                        minWidth = size.getMinSize(),
                        minHeight = size.getMinSize()
                    )
                } else {
                    Modifier.size(size.getMinSize())
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (size != OceanBadgeSize.Dot) {
            val horizontalPadding = when (size) {
                OceanBadgeSize.Small -> when (count) {
                    2 -> 4.dp
                    3 -> 4.dp
                    else -> 5.dp
                }
                OceanBadgeSize.Medium -> when (count) {
                    2 -> 6.dp
                    3 -> 4.dp
                    else -> 8.dp
                }
                else -> 4.dp
            }

            Text(
                modifier = Modifier.padding(horizontal = horizontalPadding),
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

@Composable
fun OceanBadge(
    modifier: Modifier = Modifier,
    model: Badge,
    size: OceanBadgeSize = OceanBadgeSize.Small
) = OceanBadge(
    modifier = modifier,
    text = model.text.toString(),
    type = model.type,
    size = size
)

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
