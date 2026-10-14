package br.com.useblu.oceands.components.compose.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            description = "Para começar ",
            style = OceanTextStyle.heading5,
            iconType = OceanIcons.CHEVRON_RIGHT_SOLID,
            showIconBackground = true
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

    OceanUnorderedList(models = models, verticalAlignment = Alignment.CenterVertically)
}

@Composable
fun OceanUnorderedList(
    models: List<OceanUnorderedListItemModel>,
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.Top

) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
    ) {
        models.forEach { item ->
            OceanUnorderedListItem(
                model = item,
                verticalAlignment = verticalAlignment
            )
        }
    }
}

@Composable
fun OceanUnorderedListItem(
    model: OceanUnorderedListItemModel,
    verticalAlignment: Alignment.Vertical = Alignment.Top
) {
    OceanUnorderedListItem(
        title = model.title,
        description = model.description,
        iconType = model.iconType,
        style = model.style ?: OceanTextStyle.description,
        showIconBackground = model.showIconBackground,
        iconColor = model.iconColor,
        roundBackgroundColor = model.roundBackgroundColor,
        verticalAlignment = verticalAlignment
    )
}

@Composable
fun OceanUnorderedListItem(
    title: String,
    description: String = "",
    iconType: OceanIcons,
    style: TextStyle = OceanTextStyle.description,
    showIconBackground: Boolean = false,
    iconColor: Color? = null,
    roundBackgroundColor: Color? = null,
    verticalAlignment: Alignment.Vertical = Alignment.Top
) {
    Row(
        modifier = Modifier.padding(
            horizontal = OceanSpacing.xs,
            vertical = OceanSpacing.xxxs
        ),
        verticalAlignment = verticalAlignment
    ) {
        val iconSize = if (showIconBackground) 16.dp else 24.dp

        Box(
            modifier = Modifier
                .size(24.dp)
                .iconContainerBackground(
                    showBackground = showIconBackground,
                    color = roundBackgroundColor ?: OceanColors.interfaceLightUp
                )
        ) {
            OceanIcon(
                iconType = iconType,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(iconSize),
                tint = iconColor ?: OceanColors.brandPrimaryDown
            )
        }

        OceanSpacing.StackXXS()

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            OceanText(
                text = title,
                style = style
            )

            if (description.isNotEmpty()) {
                OceanSpacing.StackXXXS()

                OceanText(
                    text = description,
                    style = OceanTextStyle.description
                )
            }
        }
    }
}
