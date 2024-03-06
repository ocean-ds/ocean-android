package br.com.useblu.oceands.components.compose.chart

import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.chart.OceanChartItem
import br.com.useblu.oceands.model.chart.OceanChartModel

private val items = listOf(
    OceanChartItem(
        title = "Item 1",
        valueFormatted = "25",
        value = 25.0f,
        color = R.color.ocean_color_interface_dark_down,
        subtitle = "First Subtitle",
        information = "lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        percent = "10%"
    ),
    OceanChartItem(
        title = "Item 2",
        valueFormatted = "60",
        value = 60.0f,
        color = R.color.ocean_color_status_negative_pure,
        subtitle = "Second Subtitle",
        information = "lorem ipsum dolor sit amet, consectetur adipiscing.",
        percent = "20%"
    ),
    OceanChartItem(
        title = "Item 3",
        valueFormatted = "75",
        value = 75.0f,
        color = R.color.ocean_color_status_warning_deep,
        subtitle = "Third Subtitle",
        information = "lorem ipsum dolor sit amet, consectetur.",
        percent = "30%"
    )
)

internal val donutModel = OceanChartModel(
    title = "Title",
    label = "Label",
    items = items
)