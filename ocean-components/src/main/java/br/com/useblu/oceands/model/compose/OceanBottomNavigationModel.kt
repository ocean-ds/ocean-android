package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.model.OceanIconType

@Immutable
data class OceanBottomNavigationModel(
    val label: String,
    val activeIcon: OceanIconType,
    val inactiveIcon: OceanIconType,
    val onClickListener: () -> Unit
)
