package br.com.useblu.oceands.components.compose.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.bindingadapters.getCenterTextStyled
import br.com.useblu.oceands.bindingadapters.getColor
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


@Preview
@Composable
private fun OceanChartScorePreview() {
    Column(
        modifier = Modifier
            .background(OceanColors.interfaceLightPure)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {

        OceanChartScore(
            title = "Title",
            description = "Description",
            value = 100f
        )

        OceanSpacing.StackXL()


        OceanChartScore(
            title = "Title",
            description = "Description",
            value = 200f
        )

        OceanSpacing.StackXL()

        OceanChartScore(
            title = "Title",
            description = "Description",
            value = 300f
        )

        OceanSpacing.StackXL()

        OceanChartScore(
            title = "Title",
            description = "Description",
            value = 301f
        )

        OceanSpacing.StackXL()

        OceanChartScore(
            title = "Title",
            description = "Description",
            value = 400f
        )

        OceanSpacing.StackXL()

        OceanChartScore(
            title = "Title",
            description = "Description",
            value = 500f
        )

        OceanSpacing.StackXL()

        OceanChartScore(
            title = "Title",
            description = "Description",
            value = 600f
        )

        OceanSpacing.StackXL()

        OceanChartScore(
            title = "Title",
            description = "Description",
            value = 700f
        )

        OceanSpacing.StackXL()

        OceanChartScore(
            title = "Title",
            description = "Description",
            value = 800f
        )

        OceanSpacing.StackXL()

        OceanChartScore(
            title = "Title",
            description = "Description",
            value = 900f
        )

        OceanSpacing.StackXL()

        OceanChartScore(
            title = "Title",
            description = "Description",
            value = 1000f
        )

        OceanSpacing.StackXL()

        OceanChartScore(
            title = "Title",
            description = "Description",
            value = 500f,
            minValue = 300f
        )

    }
}

@Composable
fun OceanChartScore(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    value: Float = 0f,
    minValue: Float = 0f,
    maxValue: Float = 1000f,
    showAnimation: Boolean = false
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OceanText(
            text = title,
            style = OceanTextStyle.heading4,
            color = OceanColors.interfaceDarkDeep
        )

        OceanSpacing.StackXXXS()

        OceanText(
            text = description,
            style = OceanTextStyle.description,
            color = OceanColors.interfaceDarkUp
        )

        OceanSpacing.StackXS()

        AndroidView(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(180.dp),
            factory = ::PieChart,
            update = {
                it.setupChartScore(
                    value = value,
                    showAnimation = showAnimation,
                    minValue = minValue,
                    maxValue = maxValue
                )
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 90.dp)
                .offset(y = (-85).dp),
        ) {
            OceanText(
                modifier = Modifier.weight(1f),
                text = minValue.toInt().toString(),
                textAlign = TextAlign.Center,
                style = OceanTextStyle.caption,
                color = OceanColors.interfaceDarkDown
            )

            OceanSpacing.StackXXXL()

            OceanText(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = maxValue.toInt().toString(),
                style = OceanTextStyle.caption,
                color = OceanColors.interfaceDarkDown
            )
        }
    }
}

fun PieChart.setupChartScore(
    showAnimation: Boolean,
    value: Float,
    minValue: Float,
    maxValue: Float
) {
    description.isEnabled = false
    legend.isEnabled = false
    isDrawHoleEnabled = true

    setHoleColor(android.R.color.transparent)
    setTransparentCircleAlpha(0)
    holeRadius = 64f
    maxAngle = 180f

    rotationAngle = 180f
    isRotationEnabled = false

    if (showAnimation) {
        animateY(1500, Easing.EaseInOutQuad)
    }

    var score = 0.0f
    val scoreRemainder = maxValue - value

    if (value > minValue) {
        score = value - minValue
    }

    setDrawCenterText(true)
    centerText = getCenterTextStyled(
        title = if (value > minValue) value.toInt().toString() else "",
        label = ""
    )

    data = PieData().apply {
        val pieEntryScore = PieEntry(score)
        val pieEntryRemainder = PieEntry(scoreRemainder)

        val pieEntries = listOf(
            pieEntryScore,
            pieEntryRemainder
        )

        val pieDataSet = PieDataSet(pieEntries, "")
        val colorScore = getColorScore(value)

        pieDataSet.colors = listOf(
            colorScore,
            getColor(R.color.ocean_color_interface_light_deep, 255)
        )

        dataSet = pieDataSet
        setValueTextColor(android.R.color.transparent)
    }

    invalidate()
}

private fun PieChart.getColorScore(value: Float): Int {
    val colorId = when (value) {
        in 0f..300f -> {
            R.color.ocean_color_interface_light_deep
        }

        in 301f..500f -> {
            R.color.ocean_color_status_negative_deep
        }

        in 501f..600f -> {
            R.color.ocean_color_status_negative_pure
        }

        in 601f..700f -> {
            R.color.ocean_color_status_warning_pure
        }

        in 701f..900f -> {
            R.color.ocean_color_status_positive_pure
        }

        else -> {
            R.color.ocean_color_status_positive_deep
        }
    }

    return getColor(colorId, 255)
}