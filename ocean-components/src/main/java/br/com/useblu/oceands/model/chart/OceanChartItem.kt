package br.com.useblu.oceands.model.chart

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

data class OceanChartItem(
    val title: String = "",
    val subtitle: String = "",
    val value: Float = 0f,
    val valueFormatted: String = "",
    val information: String = "",
    val selected: Boolean = true,
    val colorResId: Int? = null,
    val color: Color? = null
) {
    val colorArgb: Int
        get() = colorResId ?: color?.toArgb() ?: Color.Unspecified.toArgb()
}
