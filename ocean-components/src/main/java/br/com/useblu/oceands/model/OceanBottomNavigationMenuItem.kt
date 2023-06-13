package br.com.useblu.oceands.model

data class OceanBottomNavigationMenuItem(
    val title: String,
    val activeIcon: String,
    val inactiveIcon: String,
    val onClickListener: () -> Unit
)
