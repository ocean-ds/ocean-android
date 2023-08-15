package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.utils.OceanIcons

@Immutable
data class OceanBottomNavigationModel(
    val label: String,
    val activeIcon: OceanIcons,
    val inactiveIcon: OceanIcons,
    val onClickListener: () -> Unit
)
