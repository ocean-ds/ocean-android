package br.com.useblu.oceands.components.compose.expandabletextlisticonitem.model

import br.com.useblu.oceands.utils.OceanIconType

data class OceanExpandableTextListIconItemChild<ReferenceKey>(
    val icon: OceanIconType?,
    val showIconBackground: Boolean = false,
    val title: String,
    val description: String = "",
    val key: ReferenceKey
)
