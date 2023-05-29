package br.com.useblu.oceands.bindingadapters

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R
import br.com.useblu.oceands.extensions.dp
import br.com.useblu.oceands.model.OceanDonutItem
import br.com.useblu.oceands.model.OceanDonutModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

@BindingAdapter("donut_model")
fun PieChart.setupDonutModel(model: OceanDonutModel?) {
    model?.let {
        this.setupChart(model)
    }
}

private fun PieChart.setupChart(model: OceanDonutModel) {
    description.isEnabled = false
    legend.isEnabled = false

    isDrawHoleEnabled = true
    setHoleColor(android.R.color.transparent)
    setTransparentCircleAlpha(0)
    holeRadius = 64f

    rotationAngle = 270f
    isRotationEnabled = false

    isHighlightPerTapEnabled = model.items.isNotEmpty()

    setDrawCenterText(true)
    centerText = getCenterTextStyled(model.title, model.label)

    data = PieData().apply {
        dataSet = buildPieDataSet(
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
        setValueTextColor(android.R.color.transparent)
    }



    setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
        override fun onValueSelected(e: Entry?, h: Highlight?) {
            val index = h?.x?.toInt() ?: return
            val item = model.items.getOrNull(index) ?: return

            centerText = getCenterTextStyled(
                item.valueFormatted,
                item.title
            )

            repaint(item)

            model.onItemSelected(item)
        }

        private fun repaint(selected: OceanDonutItem? = null) {
            data = PieData().apply {
                dataSet = buildPieDataSet(model, selected)
                setValueTextColor(android.R.color.transparent)
            }
        }

        override fun onNothingSelected() {
            centerText = getCenterTextStyled(model.title, model.label)
            model.onNothingSelected()
            repaint()
        }
    })

    invalidate()
}

private fun PieChart.buildPieDataSet(
    model: OceanDonutModel,
    selected: OceanDonutItem? = null,
): PieDataSet {
    val pieEntries = model.items.map {
        PieEntry(it.value)
    }.ifEmpty { listOf(PieEntry(1f)) }

    val pieDataSet = PieDataSet(pieEntries, "")
    pieDataSet.selectionShift = 0f

    pieDataSet.colors = model.items.map {
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

    return pieDataSet
}

private fun PieChart.getColor(
    resId: Int,
    alpha: Int
): Int {
    val originalColor = ContextCompat.getColor(context, resId)
    return Color.argb(alpha, originalColor.red, originalColor.green, originalColor.blue)
}

private fun PieChart.getCenterTextStyled(
    title: String,
    label: String
): SpannableString {
    return SpannableString(title + "\n" + label).apply {
        setSpan(
            AbsoluteSizeSpan(20.dp),
            0,
            title.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_dark_deep
                )
            ),
            0,
            title.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_dark_down
                )
            ),
            title.length,
            title.length + label.length + 1,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        setSpan(
            AbsoluteSizeSpan(14.dp),
            title.length,
            title.length + label.length + 1,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}