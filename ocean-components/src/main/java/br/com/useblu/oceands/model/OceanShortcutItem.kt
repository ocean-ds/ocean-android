package br.com.useblu.oceands.model

data class OceanShortcutItem(
    val iconUrl: String? = null,
    val label: String,
    val count: String? = null,
    val badgeType: OceanBadgeType? = null,
    val action: () -> Unit = {}
)