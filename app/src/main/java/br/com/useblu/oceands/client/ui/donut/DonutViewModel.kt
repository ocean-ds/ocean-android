package br.com.useblu.oceands.client.ui.donut

import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.model.OceanDonutItem
import br.com.useblu.oceands.model.OceanDonutModel

class DonutViewModel : ViewModel() {
    val donutModel = OceanDonutModel(
        title = "Donut R$",
        label = "Label teste",
        items = listOf(
            OceanDonutItem(
                label = "Item 1",
                title = "R$ 25,00",
                value = 25.0,
                color = R.color.colorPrimary
            ),
            OceanDonutItem(
                label = "Item 2",
                title = "R$ 75,00",
                value = 75.0,
                color = R.color.colorPrimaryDark
            ),
            OceanDonutItem(
                label = "Item 3",
                title = "R$ 75,00",
                value = 75.0,
                color = R.color.cardview_shadow_start_color
            ),
            OceanDonutItem(
                label = "Item 4",
                title = "R$ 70,00",
                value = 70.0,
                color = R.color.colorAccent
            ),
            OceanDonutItem(
                label = "Item 5",
                title = "R$ 60,00",
                value = 60.0,
                color = R.color.ocean_color_brand_primary_pure
            ),
        )
    )

}
