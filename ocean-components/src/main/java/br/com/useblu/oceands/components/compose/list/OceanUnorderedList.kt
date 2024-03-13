package br.com.useblu.oceands.components.compose.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.model.compose.OceanUnorderedListItemModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons


@Preview(showBackground = true)
@Composable
fun OceanUnorderedListItemPreview() {
    val models = listOf(
        OceanUnorderedListItemModel(
            title = "Mais segurança: aprove, na hora, suas transações feitas pelo app",
            description = ">>>>>Descrição do item<<<<<",
            iconType = OceanIcons.SHIELD_CHECK_OUTLINE,
            showIconBackground = false
        ),
        OceanUnorderedListItemModel(
            title = "Mais segurança: aprove, na hora, suas transações feitas pelo app",
            iconType = OceanIcons.SHIELD_CHECK_OUTLINE,
            showIconBackground = false
        ),
        OceanUnorderedListItemModel(
            title = "Mais segurança: aprove, na hora, suas transações feitas pelo app",
            style = OceanTextStyle.heading5,
            iconType = OceanIcons.SHIELD_CHECK_OUTLINE,
            showIconBackground = false
        ),
        OceanUnorderedListItemModel(
            title = "<b>Mais segurança:</b> aprove, na hora, suas transações feitas pelo app",
            iconType = OceanIcons.SHIELD_CHECK_OUTLINE,
            showIconBackground = false
        )
    )

    OceanUnorderedList(models)
}

@Composable
fun OceanUnorderedList(
    models: List<OceanUnorderedListItemModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(models) { item ->
            OceanUnorderedListItem(item)
        }
    }
}

@Composable
fun OceanUnorderedListItem(
    model: OceanUnorderedListItemModel
) {
    OceanUnorderedListItem(
        title = model.title,
        description = model.description,
        iconType = model.iconType,
        style = model.style ?: OceanTextStyle.description,
        showIconBackground = model.showIconBackground
    )
}

@Composable
fun OceanUnorderedListItem(
    title: String,
    description: String = "",
    iconType: OceanIcons,
    style: TextStyle = OceanTextStyle.description,
    showIconBackground: Boolean = false
) {
    Row(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 4.dp
        ),
        verticalAlignment = Alignment.Top
    ) {
        val iconSize = if (showIconBackground) 16.dp else 24.dp

        Box(
            modifier = Modifier
                .size(24.dp)
                .iconContainerBackground(showIconBackground)
        ) {
            OceanIcon(
                iconType = iconType,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(iconSize),
                tint = OceanColors.brandPrimaryDown
            )
        }

        OceanSpacing.StackXXS()

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            OceanText(
                text = title,
                style = style
            )

            if (description.isNotEmpty()) {
                OceanText(
                    text = description,
                    style = OceanTextStyle.description
                )
            }
        }
    }
}