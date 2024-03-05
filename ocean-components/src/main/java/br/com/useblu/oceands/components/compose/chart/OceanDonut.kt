package br.com.useblu.oceands.components.compose.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.bindingadapters.setupChart
import br.com.useblu.oceands.model.OceanDonutItem
import br.com.useblu.oceands.model.OceanDonutModel
import br.com.useblu.oceands.ui.compose.OceanColors
import com.github.mikephil.charting.charts.PieChart

private val items = listOf(
    OceanDonutItem(
        title = "Item 1",
        valueFormatted = "25",
        value = 25.0f,
        color = R.color.ocean_color_interface_dark_down,
        subtitle = "First Subtitle",
        information = "lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        percent = "10%"
    ),
    OceanDonutItem(
        title = "Item 2",
        valueFormatted = "60",
        value = 60.0f,
        color = R.color.ocean_color_status_negative_pure,
        subtitle = "Second Subtitle",
        information = "lorem ipsum dolor sit amet, consectetur adipiscing.",
        percent = "20%"
    ),
    OceanDonutItem(
        title = "Item 3",
        valueFormatted = "75",
        value = 75.0f,
        color = R.color.ocean_color_status_warning_deep,
        subtitle = "Third Subtitle",
        information = "lorem ipsum dolor sit amet, consectetur.",
        percent = "30%"
    )
)

internal val donutModel = OceanDonutModel(
    title = "Title",
    label = "Label",
    items = items
)

@Preview
@Composable
private fun OceanDonutPreview() {
    Column(
        modifier = Modifier
            .background(OceanColors.interfaceLightPure)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OceanDonut(
            model = donutModel,
            modifier = Modifier.size(180.dp)
        )
    }
}


@Composable
fun OceanDonut(
    model: OceanDonutModel,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = ::PieChart,
        update = {
            it.setupChart(model)
        }
    )
}