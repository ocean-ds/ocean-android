package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons


@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    heightDp = 300
)
@Composable
private fun OceanChildListItemPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs),
        modifier = Modifier
            .background(color = OceanColors.interfaceLightDeep)
            .verticalScroll(rememberScrollState())
    ) {
        OceanChildListItem(
            style = ChildListItemStyle.Child(
                icon = OceanIcons.PLACEHOLDER_SOLID,
                description = "Description",
                value = "R$ 1.000.000,00",
                type = TransactionType.DEFAULT
            )
        )

        OceanChildListItem(
            style = ChildListItemStyle.Child(
                icon = OceanIcons.PLACEHOLDER_SOLID,
                description = "Description",
                value = "R$ 1.000.000,00",
                type = TransactionType.INFLOW
            )
        )

        OceanChildListItem(
            style = ChildListItemStyle.Child(
                icon = OceanIcons.PLACEHOLDER_SOLID,
                description = "Description",
                value = "R$ 1.000.000,00",
                type = TransactionType.OUTFLOW
            )
        )

        OceanChildListItem(
            style = ChildListItemStyle.Child(
                icon = OceanIcons.PLACEHOLDER_SOLID,
                description = "Description",
                value = "R$ 1.000.000,00",
                type = TransactionType.CANCELED
            )
        )

        OceanChildListItem(
            isLoading = true,
            style = ChildListItemStyle.Child(
                icon = OceanIcons.PLACEHOLDER_SOLID,
                description = "Description",
                value = "R$ 1.000.000,00",
                type = TransactionType.OUTFLOW
            )
        )
    }
}

@Composable
fun OceanChildListItem(
    modifier: Modifier = Modifier,
    style: ChildListItemStyle.Child,
    isLoading: Boolean = false,
) {
    if(isLoading) {
        ChildListItemSkeleton()
        return
    }

    val (color, value) = when (style.type) {
        TransactionType.DEFAULT -> OceanColors.interfaceDarkPure to style.value
        TransactionType.OUTFLOW -> OceanColors.interfaceDarkPure to "- ${style.value}"
        TransactionType.INFLOW -> OceanColors.statusPositiveDeep to "+ ${style.value}"
        TransactionType.CANCELED -> OceanColors.interfaceDarkUp to style.value
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(OceanColors.interfaceLightPure)
            .padding(OceanSpacing.xxsExtra)
    ) {
        Image(
            painter = painterResource(id = style.icon.icon),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .padding(end = OceanSpacing.xxsExtra)
                .weight(0.2f)
        )
        OceanText(
            text = style.description,
            style = OceanTextStyle.description,
            modifier = Modifier
                .padding(end = OceanSpacing.xxs)
                .weight(1f)
        )
        OceanText(
            text = value,
            style = OceanTextStyle.paragraph.copy(
                color = color
            )
        )
    }
}

@Composable
private fun ChildListItemSkeleton() {
    Column(
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure)
            .padding(horizontal = OceanSpacing.xs)
            .padding(top = OceanSpacing.xxsExtra, bottom = OceanSpacing.xxsExtra)
    ) {
        OceanShimmering { brush ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(OceanSpacing.md),
                modifier = Modifier
                    .background(OceanColors.interfaceLightPure)
            ) {
                Spacer(
                    modifier = Modifier
                        .background(brush, RoundedCornerShape(4.dp))
                        .height(16.dp)
                        .width(120.dp)
                        .weight(2f)
                )
                Spacer(
                    modifier = Modifier
                        .background(brush, RoundedCornerShape(4.dp))
                        .height(16.dp)
                        .width(80.dp)
                        .weight(1f)
                )
            }
        }
    }
}

class ChildScope

sealed interface ChildListItemStyle {
    data class Child(
        val icon: OceanIcons,
        val description: String,
        val value: String,
        val type: TransactionType,
    ) : ChildListItemStyle
}