package br.com.useblu.oceands.components.compose.chart

import android.graphics.Color
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
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import br.com.useblu.oceands.R
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
                    val item = it.copy(subtitle = "Jul/23")
                    listOf(item)
                }.flatten()
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
            it.setChartModel(model, windowWidth)
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

    axisLeft.isEnabled = false
    axisRight.isEnabled = false

    xAxis.setDrawAxisLine(false)
    xAxis.setDrawGridLines(false)
    xAxis.valueFormatter = IndexAxisValueFormatter(model.items.map { it.subtitle })
    xAxis.position = XAxis.XAxisPosition.BOTTOM
    xAxis.setLabelCount(model.items.size, false)
    xAxis.setCenterAxisLabels(false)
    xAxis.textColor = ContextCompat.getColor(context, R.color.ocean_color_interface_dark_up)
    xAxis.typeface = ResourcesCompat.getFont(context, R.font.font_family_base_medium)
}

fun BarChart.setChartModel(
    model: OceanChartModel,
    windowWidth: Int
) {
    if (windowWidth == 0) return

    val dataSet = buildDataSet(
        model = model,
        selected = if (model.items.all { it.selected }) {
            null
        } else {
            highlightValue(model.items.indexOfFirst {
                it.selected
            }.toFloat(), 0, false)
            model.items.firstOrNull { it.selected }
        }
    )

    data = BarData(dataSet)
    data.barWidth = (16.dpFloat / windowWidth) * model.items.size

    setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
        override fun onValueSelected(e: Entry?, h: Highlight?) {
            val index = h?.x?.toInt() ?: return
            val item = model.items.getOrNull(index) ?: return

            repaint(item)

            model.onItemSelected(item)
        }

        private fun repaint(selected: OceanChartItem? = null) {
            data = BarData(buildDataSet(model, selected))
            data.barWidth = (16.dpFloat/windowWidth) * model.items.size
        }

        override fun onNothingSelected() {
            model.onNothingSelected()
            repaint()
        }
    })

    invalidate()
}

private fun BarChart.buildDataSet(
    model: OceanChartModel,
    selected: OceanChartItem? = null,
): BarDataSet {
    val entries = model.items.mapIndexed { index, it ->
        BarEntry(index.toFloat(), it.value)
    }.ifEmpty { listOf(BarEntry(1f,0f)) }

    val dataSet = BarDataSet(entries, "")

    dataSet.valueFormatter = DefaultValueFormatter(0)

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
    }.ifEmpty {
        listOf(
            ContextCompat.getColor(context, R.color.ocean_color_interface_light_deep)
        )
    }

    return dataSet
}

private fun BarChart.getColor(
    resId: Int,
    alpha: Int
): Int {
    val originalColor = ContextCompat.getColor(context, resId)
    return Color.argb(alpha, originalColor.red, originalColor.green, originalColor.blue)
}