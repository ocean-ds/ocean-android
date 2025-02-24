package br.com.useblu.oceands.components.compose.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.compose.OceanUnorderedListItemModel
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
fun OceanCalloutListPreview() {
    val model = listOf(
        OceanUnorderedListItemModel(
            title = "Mais segurança: aprove, na hora, suas transações feitas pelo app",
            description = "Descrição do item: <b> detalhadamente! </b>",
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
            iconType = OceanIcons.SHIELD_CHECK_OUTLINE,
            showIconBackground = false
        )
    )

    OceanCalloutList(models = model)
}

@Composable
fun OceanCalloutList(
    models: List<OceanUnorderedListItemModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .borderBackground(
                color = OceanColors.interfaceLightUp,
                borderRadius = OceanBorderRadius.SM.allCorners
            )
            .padding(
                vertical = 12.dp
            ),
        verticalArrangement = Arrangement.spacedBy( OceanSpacing.xxs)
    ) {
        models.forEach { item ->
            OceanUnorderedListItem(item)
        }
    }
}
