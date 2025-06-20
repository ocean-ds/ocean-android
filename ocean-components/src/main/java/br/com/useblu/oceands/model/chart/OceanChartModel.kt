package br.com.useblu.oceands.model.chart

data class OceanChartModel(
    val title: String = "",
    val label: String = "",
    val items: List<OceanChartItem> = emptyList(),
    val onItemSelected: (index: Int) -> Unit = {},
    val onNothingSelected: () -> Unit = {},
    val showDivider: Boolean = true
)
