package br.com.useblu.oceands.model.compose

import br.com.useblu.oceands.utils.OceanIcons

data class OceanHeaderAppAction<IdentifiableKey>(
    val key: IdentifiableKey,
    val icon: OceanIcons,
    val badgeCount: Int = 0,
    val action: (IdentifiableKey) -> Unit
)