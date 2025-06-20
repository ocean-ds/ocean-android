package br.com.useblu.oceands.components.compose.chart

import androidx.compose.runtime.Composable
import br.com.useblu.oceands.model.chart.OceanChartItem
import br.com.useblu.oceands.model.chart.OceanChartModel
import br.com.useblu.oceands.ui.compose.OceanColors

@Composable
fun previewDonutModel(): OceanChartModel {
    val items = listOf(
        OceanChartItem(
            title = "Item 1",
            valueFormatted = "25",
            value = 25.0f,
            color = OceanColors.interfaceDarkDown,
            subtitle = "First Subtitle",
            information = "lorem ipsum dolor sit amet, consectetur adipiscing elit."
        ),
        OceanChartItem(
            title = "Item 2",
            valueFormatted = "60",
            value = 60.0f,
            color = OceanColors.statusNegativePure,
            subtitle = "Second Subtitle",
            information = "lorem ipsum dolor sit amet, consectetur adipiscing."
        ),
        OceanChartItem(
            title = "Item 3",
            valueFormatted = "75",
            value = 75.0f,
            color = OceanColors.statusWarningDeep,
            subtitle = "Third Subtitle",
            information = "lorem ipsum dolor sit amet, consectetur."
        )
    )

    return OceanChartModel(
        title = "Title",
        label = "Label",
        items = items,
        showDivider = false
    )
}
