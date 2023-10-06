package br.com.useblu.oceands.components.compose.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTag
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
    OceanTransactionListItem(
        primaryLabel = "Level 1",
        secondaryLabel = "Level 2",
        dimmedLabel = "Level 3",
        highlightedLabel = "Level 4",
        value = 10045.32,
        valueWithSignal = true,
        valueIsHighlighted = true,
        time = "Time",
        tagTitle = "Title",
        icon = OceanIcons.LOCK_CLOSED_SOLID
    )
}

@Composable
fun OceanTransactionListItem(
    highlightedLabel: String? = null,
    primaryLabel: String,
    secondaryLabel: String? = null,
    dimmedLabel: String? = null,
    value: Double? = null,
    valueIsHighlighted: Boolean = false,
    valueWithSignal: Boolean = false,
    tagTitle: String? = null,
    tagType: OceanTagType = OceanTagType.Warning,
    tagHideIcon: Boolean = false,
    time: String? = null,
    icon: OceanIcons? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = OceanColors.interfaceLightPure)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            OceanIcon(
                iconType = icon,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp)
            )
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
            if (value != null) {
                val color = if (valueIsHighlighted && value > 0) {
                    OceanColors.statusPositiveDeep
                } else OceanColors.interfaceDarkPure

                var formattedValue = FormatTypes.FORMAT_VALUE_WITH_SYMBOL.format(value.toString())
                if (valueWithSignal) {
                    if (value > 0)
                        formattedValue = "- $formattedValue"
                    else if (value < 0)
                        formattedValue = "+ $formattedValue"
                }
                Text(
                    text = formattedValue,
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
                    style = OceanTextStyle.caption
                )
            }
        }
    }
}