package br.com.useblu.oceands.components.compose.expandabletextlisticonitem.model

import br.com.useblu.oceands.utils.OceanIcons

data class OceanExpandableTextListIconItemChild<ReferenceKey>(
    val icon: OceanIcons?,
    val showIconBackground: Boolean = false,
    val title: String,
    val description: String = "",
    val key: ReferenceKey
)
