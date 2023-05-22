package br.com.useblu.oceands.client.ui.chartcard

import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.model.OceanDonutItem
import br.com.useblu.oceands.model.OceanDonutModel

class ChartCardViewModel : ViewModel() {

    val items = listOf(
        OceanDonutItem(
            title = "Item 1",
            valueFormatted = "25",
            value = 25.0f,
            color = R.color.colorPrimary,
            subtitle = "Subtitle 1",
            information = "lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            percent = "10%"
        ),
        OceanDonutItem(
            title = "Item 2",
            valueFormatted = "75",
            value = 75.0f,
            color = R.color.ocean_color_status_negative_pure,
            subtitle = "Subtitle 2",
            information = "lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            percent = "20%"
        ),
        OceanDonutItem(
            title = "Item 3",
            valueFormatted = "75",
            value = 75.0f,
            color = R.color.ocean_color_status_neutral_deep,
            subtitle = "Subtitle 3",
            information = "lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            percent = "30%"
        ),
        OceanDonutItem(
            title = "Item 4",
            valueFormatted = "70",
            value = 70.0f,
            color = R.color.colorAccent,
            subtitle = "Subtitle 4",
            information = "lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            percent = "40%"
        ),
        OceanDonutItem(
            title = "Item 5",
            valueFormatted = "60",
            value = 60.0f,
            color = R.color.ocean_color_brand_primary_pure,
            subtitle = "Subtitle 5",
            information = "lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            percent = "50%"
        ),
    )

    val model = OceanDonutModel(
        title = "Title",
        label = "Label",
        items = items,
        onItemSelected = {
        },
        onNothingSelected = {
        }
    )

    fun click() {
        println("Click")
    }
}

