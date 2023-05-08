package br.com.useblu.oceands.client.ui.donut

import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.model.OceanDonutItem

class DonutViewModel : ViewModel() {
    val items = listOf(
        OceanDonutItem(
            label = "Item 1",
            value = 25.0,
            color = R.color.colorPrimary
        ),
        OceanDonutItem(
            label = "Item 2",
            value = 75.0,
            color = R.color.colorPrimaryDark
        ),
        OceanDonutItem(
            label = "Item 2",
            value = 75.0,
            color = R.color.cardview_shadow_start_color
        ),
        OceanDonutItem(
            label = "Item 2",
            value = 75.0,
            color = R.color.colorAccent
        ),
        OceanDonutItem(
            label = "Item 2",
            value = 75.0,
            color = R.color.ocean_color_brand_primary_pure
        ),
    )
}
