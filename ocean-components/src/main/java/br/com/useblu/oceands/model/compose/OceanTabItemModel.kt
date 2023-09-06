package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable

@Immutable
data class OceanTabItemModel(
    val label: String,
    val counter: Int? = null
)