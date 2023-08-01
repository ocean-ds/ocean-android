package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable

@Immutable
data class OceanUnorderedListItemModel(
    val title: String,
    val iconToken: String,
    val showIconBackground: Boolean = false
)
