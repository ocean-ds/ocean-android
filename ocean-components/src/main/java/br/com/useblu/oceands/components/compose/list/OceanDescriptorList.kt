package br.com.useblu.oceands.components.compose.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.model.OceanDescriptorListItem
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
private fun OceanDescriptorListPreview() {
    OceanDescriptorList(
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure)
            .padding(16.dp),
        items = listOf(
            OceanDescriptorListItem(
                title = "Title",
                value = "Value",
                color = "colorstatuspositivedeep"
            ),
            OceanDescriptorListItem(
                isDivider = true,
            ),
            OceanDescriptorListItem(
                title = "Title",
                value = "Value",
                isStrike = true,
                newValue = "De graça",
                color = "colorstatuspositivedeep"
            ),
            OceanDescriptorListItem(
                title = "Title",
                value = "Value",
                isBold = true,
                newValue = "New Value",
                color = "colorstatuspositivedeep"
            ),
            OceanDescriptorListItem(
                title = "Title",
                value = "Value",
                isBold = true,
                newValue = "New Value",
                color = "colorstatuspositivedeep",
                icon = OceanIcons.BRIEFCASE_OUTLINE.token
            )
        )
    )
}

@Composable
fun OceanDescriptorList(
    modifier: Modifier = Modifier,
    items: List<OceanDescriptorListItem>
) {
    Column(
        modifier = modifier
    ) {
        items.forEach {
            if (it.isDivider == true) {
                OceanDivider(modifier = Modifier.padding(top = 16.dp))
            } else {
                OceanDescriptorItem(item = it)
            }
        }
    }
}

@Composable
fun OceanDescriptorItem(
    modifier: Modifier = Modifier,
    item: OceanDescriptorListItem
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        OceanText(
            text = item.title ?: "",
            style = OceanTextStyle.description
        )

        DescriptorValuesRow(item)
    }
}

@Composable
private fun DescriptorValuesRow(item: OceanDescriptorListItem) {
    val textColor = if (item.color != null) {
        OceanColors.fromString(color = item.color)
    } else OceanColors.interfaceDarkDown

    val valueStyle = if (item.isBold == true) {
        OceanTextStyle.heading3
    } else OceanTextStyle.paragraph

    val valueDecoration = if (item.isStrike == true) {
        TextDecoration.LineThrough
    } else null

    val valueColor = if (item.newValue == null) {
        textColor
    } else OceanColors.interfaceDarkDown

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!item.icon.isNullOrBlank()) {
            OceanIcon(
                iconType = OceanIcons.fromToken(item.icon),
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(16.dp)
            )
        }

        OceanText(
            text = item.value ?: "",
            style = valueStyle,
            textDecoration = valueDecoration,
            modifier = Modifier
                .padding(end = 4.dp),
            color = valueColor
        )

        if (!item.newValue.isNullOrBlank()) {
            OceanText(
                text = item.newValue,
                style = OceanTextStyle.paragraph,
                color = textColor
            )
        }
    }
}