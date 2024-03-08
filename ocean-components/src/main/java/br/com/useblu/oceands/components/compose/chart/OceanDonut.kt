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
import br.com.useblu.oceands.bindingadapters.setupChart
import br.com.useblu.oceands.model.chart.OceanChartModel
import br.com.useblu.oceands.ui.compose.OceanColors
import com.github.mikephil.charting.charts.PieChart


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
    model: OceanChartModel,
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