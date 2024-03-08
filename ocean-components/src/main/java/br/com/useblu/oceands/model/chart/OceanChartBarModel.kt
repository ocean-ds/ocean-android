package br.com.useblu.oceands.model.chart

import androidx.annotation.ColorRes
import br.com.useblu.oceands.R

data class OceanChartBarModel(
    @ColorRes val color: Int = R.color.ocean_color_brand_primary_down,
    @ColorRes val highlightColor: Int = R.color.ocean_color_brand_primary_pure,
    val items: List<OceanChartBarItem> = emptyList()
)
