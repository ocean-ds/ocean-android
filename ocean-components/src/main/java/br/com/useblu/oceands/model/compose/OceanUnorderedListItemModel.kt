package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.model.OceanIconType

@Immutable
data class OceanUnorderedListItemModel(
    val title: String,
    val iconType: OceanIconType,
    val showIconBackground: Boolean = false
)
