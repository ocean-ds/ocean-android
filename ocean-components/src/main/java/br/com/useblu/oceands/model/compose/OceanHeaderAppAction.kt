package br.com.useblu.oceands.model.compose

import br.com.useblu.oceands.utils.OceanIconType

data class OceanHeaderAppAction<IdentifiableKey>(
    val key: IdentifiableKey,
    val icon: OceanIconType,
    val badgeCount: Int = 0,
    val action: (IdentifiableKey) -> Unit
)
