package br.com.useblu.oceands.components.compose.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import br.com.useblu.oceands.R
import br.com.useblu.oceands.bindingadapters.getColor
import br.com.useblu.oceands.extensions.dpFloat
import br.com.useblu.oceands.model.chart.OceanChartItem
import br.com.useblu.oceands.model.chart.OceanChartModel
import br.com.useblu.oceands.ui.compose.OceanColors
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.DefaultValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

@Preview
@Composable
private fun OceanChartBarPreview() {
    Column(
        modifier = Modifier
            .background(OceanColors.interfaceLightPure)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OceanChartBar(
            model = donutModel.copy(
                items = donutModel.items.map {
                    it.copy(title = "Jul/23")
                }
            ),
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        )
    }

}

@Composable
fun OceanChartBar(
    model: OceanChartModel,
    modifier: Modifier = Modifier
) {
    var windowWidth by remember { mutableIntStateOf(0) }

    AndroidView(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged {
                windowWidth = it.width
            },
        factory = {
            BarChart(it).apply {
                setupChart(model)
            }
        },
        update = {
            it.updateChartModel(model, windowWidth)
        }
    )
}

private fun BarChart.setupChart(model: OceanChartModel) {
    description.isEnabled = false
    legend.isEnabled = false

    isHighlightPerTapEnabled = model.items.isNotEmpty()

    minOffset = 0f

    setPinchZoom(false)
    isDoubleTapToZoomEnabled = false

    axisRight.isEnabled = false

    axisLeft.setDrawAxisLine(false)
    axisLeft.setDrawLabels(false)
    axisLeft.setDrawGridLines(false)
    axisLeft.setDrawZeroLine(true)
    axisLeft.axisMinimum = 0f
    axisLeft.zeroLineColor = ContextCompat.getColor(context, R.color.ocean_color_interface_light_down)

    xAxis.setDrawAxisLine(false)
    xAxis.setDrawGridLines(false)
    xAxis.valueFormatter = IndexAxisValueFormatter(model.items.map { it.title })

    xAxis.textSize = 12f
    xAxis.position = XAxis.XAxisPosition.BOTTOM
    xAxis.setLabelCount(model.items.size, false)
    xAxis.setCenterAxisLabels(false)
    xAxis.textColor = ContextCompat.getColor(context, R.color.ocean_color_interface_dark_up)
    xAxis.typeface = ResourcesCompat.getFont(context, R.font.font_family_base_medium)
}

fun BarChart.updateChartModel(
    model: OceanChartModel,
    windowWidth: Int
) {
    if (windowWidth == 0) return

    val dataSet = buildDataSet(
        model = model,
        selected = if (model.items.all { it.selected }) {
            null
        } else {
            val selectedIndex = model.items.indexOfFirst { it.selected }
            highlightValue(selectedIndex.toFloat(), 0, false)
            model.items.firstOrNull { it.selected }
        }
    )

    data = BarData(dataSet)
    data.barWidth = calculateBarWidth(windowWidth, model.items.size)

    setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
        override fun onValueSelected(e: Entry?, h: Highlight?) {
            val index = h?.x?.toInt() ?: return

            model.onItemSelected(index)
        }

        override fun onNothingSelected() {
            model.onNothingSelected()
        }
    })

    invalidate()
}

private fun calculateBarWidth(
    windowWidthInPx: Int,
    itemsLength: Int,
    barWidthInDp: Int = 16
) = (barWidthInDp.dpFloat / windowWidthInPx) * itemsLength

private fun BarChart.buildDataSet(
    model: OceanChartModel,
    selected: OceanChartItem? = null,
): BarDataSet {
    val entries = model.items.mapIndexed { index, it ->
        BarEntry(index.toFloat(), it.value)
    }

    val dataSet = BarDataSet(entries, "")

    dataSet.highLightAlpha = 0

    dataSet.valueFormatter = DefaultValueFormatter(0)
    dataSet.valueTextSize = 10f

    dataSet.setValueTextColors(
        listOf(ContextCompat.getColor(context, R.color.ocean_color_interface_dark_down))
    )

    dataSet.colors = model.items.map {
        if (selected == null) {
            ContextCompat.getColor(context, it.color)
        } else {
            val alpha = if (selected == it) {
                255
            } else {
                41
            }

            getColor(resId = it.color, alpha = alpha)
        }
    }

    return dataSet
}