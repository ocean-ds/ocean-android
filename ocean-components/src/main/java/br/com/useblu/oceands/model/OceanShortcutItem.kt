package br.com.useblu.oceands.model

data class OceanShortcutItem(
    val icon: String? = null,
    val label: String,
    val count: String? = null,
    val subTitle: String? = null,
    val badgeType: OceanBadgeType? = null,
    val action: () -> Unit = {},
    val layoutMode: OceanShortcutLayoutMode = OceanShortcutLayoutMode.Vertical,
    val blocked: Boolean = false
) {
    fun showBadge() = !blocked && count != null
}

enum class OceanShortcutLayoutMode {
    Vertical,
    Horizontal
}