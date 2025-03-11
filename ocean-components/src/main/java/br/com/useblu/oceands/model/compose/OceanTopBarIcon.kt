package br.com.useblu.oceands.model.compose

import br.com.useblu.oceands.utils.OceanIcons

data class OceanTopBarIcon(
    val icon: OceanIcons,
    val onClick: () -> Unit
)
