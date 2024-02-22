package br.com.useblu.oceands.components.compose.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.compose.OceanUnorderedListItemModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
fun OceanCalloutListPreview() {
    val model = listOf(
        OceanUnorderedListItemModel(
            title = "Mais segurança: aprove, na hora, suas transações feitas pelo app",
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
    LazyColumn(
        modifier = modifier
            .background(
                color = OceanColors.interfaceLightUp,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                vertical = 12.dp
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(models) {item ->
            OceanUnorderedListItem(item)
        }
    }
}
