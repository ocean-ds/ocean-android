package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable

@Immutable
data class OceanBottomNavigationModel(
    val label: String,
    val activeIcon: String,
    val inactiveIcon: String,
    val onClickListener: () -> Unit
)
