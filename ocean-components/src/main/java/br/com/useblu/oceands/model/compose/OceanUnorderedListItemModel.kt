package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.utils.OceanIcons

@Immutable
data class OceanUnorderedListItemModel(
    val title: String,
    val iconType: OceanIcons,
    val showIconBackground: Boolean = false
)
