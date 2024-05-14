package br.com.useblu.oceands.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.components.compose.OceanChip
import br.com.useblu.oceands.model.Badge
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanBasicChip
import br.com.useblu.oceands.model.OceanChip
import br.com.useblu.oceands.model.OceanChipFilterOptions
import br.com.useblu.oceands.model.OceanChipItemState
import br.com.useblu.oceands.model.OceanFilterChip
import br.com.useblu.oceands.utils.OceanIcons

@Composable
@Preview
fun OceanFilterBarPreview() {
    OceanFilterBar(
        filterList = listOf(
            OceanBasicChip(
                id = "0",
                label = "Todos",
                badge = Badge(
                    text = 2,
                    type = OceanBadgeType.PRIMARY_INVERTED
                ),
                state = OceanChipItemState.DEFAULT_ACTIVE,
                onClick = {},
            ),
            OceanBasicChip(
                id = "1",
                label = "Pendentes",
                badge = Badge(
                    text = 2,
                    type = OceanBadgeType.PRIMARY
                ),
                state = OceanChipItemState.HOVER_INACTIVE,
                onClick = {},
            ),
            OceanFilterChip(
                id = "2",
                label = "Canceladas",
                badge = null,
                state = OceanChipItemState.HOVER_INACTIVE,
                filterOptions = OceanChipFilterOptions.SingleChoice("", emptyList(), {})
            ),
            OceanBasicChip(
                id = "3",
                label = "Todos os filtros",
                badge = Badge(
                    text = 2,
                    type = OceanBadgeType.PRIMARY
                ),
                icon = OceanIcons.ADJUSTMENTS_OUTLINE,
                state = OceanChipItemState.HOVER_INACTIVE,
                onClick = {}
            )
        )
    )
}

@Composable
fun OceanFilterBar(
    modifier: Modifier = Modifier,
    filterList: List<OceanChip>,
    showDividerBeforeLastItem: Boolean = true
) {
    Column(
        modifier = modifier
            .background(color = colorResource(R.color.ocean_color_interface_light_pure))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .height(32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            filterList.forEachIndexed { index, itemModel ->
                if (index == filterList.size - 1 && showDividerBeforeLastItem) {
                    VerticalDivider(
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight(),
                        thickness = 1.dp,
                        color = colorResource(R.color.ocean_color_interface_light_down)
                    )
                }
                OceanChip(
                    model = itemModel
                )
            }
        }
    }
}
