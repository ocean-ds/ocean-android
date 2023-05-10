package br.com.useblu.oceands.model

data class OceanDonutModel(
    val title: String = "",
    val label: String = "",
    val items: List<OceanDonutItem> = emptyList(),
    val onItemSelected: (OceanDonutItem) -> Unit = {},
    val onNothingSelected: () -> Unit = {}
)
