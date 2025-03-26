package br.com.useblu.oceands.components.daterangefilter

internal data class OceanDateRangeSelectFilterUIState(
    val beginDate: String = "",
    val endDate: String = "",
    val errorBeginDate: String? = null,
    val errorEndDate: String? = null
)
