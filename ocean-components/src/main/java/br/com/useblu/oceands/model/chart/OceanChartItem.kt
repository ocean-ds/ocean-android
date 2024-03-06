package br.com.useblu.oceands.model.chart

import androidx.annotation.ColorRes

data class OceanChartItem(
    val title: String = "",
    val subtitle: String = "",
    val value: Float = 0f,
    val valueFormatted: String = "",
    val percent: String = "",
    val information: String = "",
    val selected: Boolean = true,
    @ColorRes val color: Int
)
