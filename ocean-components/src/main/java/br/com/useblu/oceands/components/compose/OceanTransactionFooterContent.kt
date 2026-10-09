package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListReadOnly
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListState
import br.com.useblu.oceands.components.compose.transactionlist.TransactionListDensity
import br.com.useblu.oceands.components.compose.transactionlist.TransactionListIconColor
import br.com.useblu.oceands.model.compose.OceanIconModel
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

enum class OceanTransactionFooterType {
    Default,
    Highlight
}

data class OceanTransactionFooterItem(
    val content: ContentListStyle,
    val amount: ContentListStyle.Amount? = null,
    val icon: OceanIconModel? = null,
    val state: OceanTransactionListState = OceanTransactionListState.Default,
    val iconColor: TransactionListIconColor = TransactionListIconColor.Default
)

data class OceanTransactionFooterTotal(
    val label: String,
    val value: String
)

@Composable
fun OceanTransactionFooter(
    items: List<OceanTransactionFooterItem>,
    total: OceanTransactionFooterTotal,
    button: OceanButtonModel,
    modifier: Modifier = Modifier,
    type: OceanTransactionFooterType = OceanTransactionFooterType.Default,
    notice: String? = null
) {
    val background = when (type) {
        OceanTransactionFooterType.Default -> OceanColors.interfaceLightPure
        OceanTransactionFooterType.Highlight -> OceanColors.interfaceLightUp
    }
    val shape = if (type == OceanTransactionFooterType.Highlight) {
        OceanBorderRadius.MD.topCorners.shape()
    } else {
        RectangleShape
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(background)
    ) {
        if (type == OceanTransactionFooterType.Default) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(OceanColors.interfaceLightDown)
            )
        }

        if (notice != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(OceanColors.statusPositiveUp)
            ) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(OceanColors.statusPositiveDeep.copy(alpha = 0.12f))
                )
                OceanText(
                    text = notice,
                    modifier = Modifier.padding(OceanSpacing.xs),
                    style = OceanTextStyle.paragraph
                )
            }
            Spacer(modifier = Modifier.height(OceanSpacing.xs))
        }

        items.forEachIndexed { index, item ->
            OceanTransactionListReadOnly(
                content = item.content,
                amount = item.amount,
                icon = item.icon,
                state = item.state,
                iconColor = item.iconColor,
                density = if (index == 0) {
                    TransactionListDensity.Default
                } else {
                    TransactionListDensity.Compact
                },
                showDivider = index == 0 && items.size > 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(background)
            )
        }

        OceanDivider(
            modifier = Modifier.padding(horizontal = OceanSpacing.xs)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = OceanSpacing.xs, vertical = OceanSpacing.xs),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OceanText(
                text = total.label,
                style = OceanTextStyle.paragraph
            )
            OceanText(
                text = total.value,
                style = OceanTextStyle.paragraph.copy(
                    fontFamily = OceanFontFamily.BaseBold,
                    color = OceanColors.interfaceDarkDeep
                )
            )
        }

        Spacer(modifier = Modifier.height(OceanSpacing.md))
        OceanButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = OceanSpacing.xs),
            button = button
        )
        Spacer(modifier = Modifier.height(OceanSpacing.xs))
    }
}
