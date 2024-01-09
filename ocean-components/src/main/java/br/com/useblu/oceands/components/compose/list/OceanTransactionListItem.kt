package br.com.useblu.oceands.components.compose.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTag
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.extensions.oceanFormatWithCurrency
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.FormatTypes
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun OceanTransactionListItemPreview() {
    Column {
        OceanTransactionListItem(
            primaryLabel = "Level 1",
            secondaryLabel = "Level 2",
            dimmedLabel = "Level 3",
            highlightedLabel = "Level 4",
            primaryValue = 10045.32,
            secondaryValue = 10002.78,
            valueWithSignal = true,
            valueIsHighlighted = true,
            time = "Time",
            tagTitle = "Title",
            icon = OceanIcons.LOCK_CLOSED_SOLID
        )
        OceanTransactionListItem(
            primaryLabel = "Level 1",
            secondaryLabel = "Level 2",
            dimmedLabel = "Level 3",
            highlightedLabel = "Level 4",
            primaryValue = -10045.32,
            valueWithSignal = true,
            valueIsHighlighted = true,
            time = "Time",
            tagTitle = "Title",
            icon = OceanIcons.LOCK_CLOSED_SOLID
        )
        OceanTransactionListItem(
            primaryLabel = "Level 1",
            secondaryLabel = "Level 2",
            dimmedLabel = "Level 3",
            highlightedLabel = "Level 4",
            primaryValue = -10045.32,
            valueWithSignal = true,
            valueIsHighlighted = true,
            valueIsCanceled = true,
            time = "Time",
            tagTitle = "Canceled",
            tagType = OceanTagType.Negative,
            icon = OceanIcons.LOCK_CLOSED_SOLID
        )
        OceanTransactionListItem(
            primaryLabel = "Level 1",
            secondaryLabel = "Level 2",
            dimmedLabel = "Level 3",
            highlightedLabel = "Level 4",
            primaryValue = 10045.32,
            valueWithSignal = true,
            valueIsHighlighted = true,
            valueIsCanceled = true,
            time = "Time",
            tagTitle = "Canceled",
            tagType = OceanTagType.Negative,
            icon = OceanIcons.LOCK_CLOSED_SOLID
        )
    }
}

@Composable
fun OceanTransactionListItem(
    primaryLabel: String,
    modifier: Modifier = Modifier,
    secondaryLabel: String? = null,
    dimmedLabel: String? = null,
    highlightedLabel: String? = null,
    primaryValue: Double? = null,
    secondaryValue: Double? = null,
    valueIsHighlighted: Boolean = false,
    valueWithSignal: Boolean = false,
    valueIsCanceled: Boolean = false,
    tagTitle: String? = null,
    tagType: OceanTagType = OceanTagType.Warning,
    time: String? = null,
    icon: OceanIcons? = null
) {
    Column {
        Row(
            modifier = modifier
                .background(color = OceanColors.interfaceLightPure)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .iconContainerBackground(true)
                        .size(40.dp)
                ) {
                    OceanIcon(
                        iconType = icon,
                        tint = OceanColors.interfaceDarkUp,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(24.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                if (highlightedLabel != null) {
                    Text(
                        text = highlightedLabel,
                        color = OceanColors.brandPrimaryDeep,
                        style = OceanTextStyle.captionBold
                    )

                    OceanSpacing.StackXXS()
                }

                Text(
                    text = primaryLabel,
                    fontSize = 16.sp,
                    fontFamily = OceanFontFamily.BaseRegular,
                    color = OceanColors.interfaceDarkPure
                )

                if (secondaryLabel != null) {
                    OceanSpacing.StackXXXS()
                    Text(
                        text = secondaryLabel,
                        style = OceanTextStyle.description
                    )
                }

                if (dimmedLabel != null) {
                    OceanSpacing.StackXXS()
                    Text(
                        text = dimmedLabel,
                        style = OceanTextStyle.caption
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                if (primaryValue != null) {
                    val color = when {
                        valueIsHighlighted && primaryValue > 0 -> OceanColors.statusPositiveDeep
                        valueIsHighlighted && valueIsCanceled -> OceanColors.interfaceDarkUp
                        else -> OceanColors.interfaceDarkPure
                    }

                    var formattedValue = FormatTypes.FORMAT_VALUE_WITH_SYMBOL.format(primaryValue.toString())

                    if (valueWithSignal) {
                        if (primaryValue >= 0) {
                            formattedValue = "+ $formattedValue"
                        }
                    } else {
                        formattedValue = formattedValue.replace("-", "")
                    }
                    Text(
                        text = formattedValue,
                        color = color,
                        fontFamily = OceanFontFamily.BaseMedium,
                        style = OceanTextStyle.description
                    )
                }
                if (secondaryValue != null) {
                    val color = OceanColors.interfaceDarkDown
                    Text(
                        text = secondaryValue.oceanFormatWithCurrency(),
                        color = color,
                        fontFamily = OceanFontFamily.BaseMedium,
                        style = OceanTextStyle.description
                    )
                }

                if (tagTitle != null) {
                    OceanSpacing.StackXXXS()
                    OceanTag(
                        label = tagTitle,
                        type = tagType
                    )
                }

                if (time != null) {
                    OceanSpacing.StackXXXS()
                    Text(
                        text = time,
                        style = OceanTextStyle.caption,
                        fontFamily = OceanFontFamily.BaseMedium
                    )
                }
            }
        }

        Divider(color = OceanColors.interfaceLightDown)
    }
}
