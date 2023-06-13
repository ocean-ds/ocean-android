package br.com.useblu.oceands.model

import android.content.Context
import android.view.ViewGroup.LayoutParams

data class OceanShortcutItem(
    val icon: String? = null,
    val label: String,
    val count: String? = null,
    val subTitle: String? = null,
    val badgeType: OceanBadgeType? = null,
    val action: () -> Unit = {},
    val size: OceanShortcutCardSize = OceanShortcutCardSize.Small,
    val viewMode: OceanShortcutLayoutMode = OceanShortcutLayoutMode.Vertical,
    val blocked: Boolean = false
) {
    fun hasSubtitle() = size == OceanShortcutCardSize.Medium

    fun showBadge() = !blocked && count != null

    fun getCardHeight(context: Context): Int {
        return (size.height * context.resources.displayMetrics.density).toInt()
    }

    fun getCardWidth(context: Context): Int {
        return (size.width * context.resources.displayMetrics.density).toInt()
    }
}

enum class OceanShortcutCardSize(val width: Int, val height: Int) {
    TinyVertical(80, 80),
    TinyHorizontal(100, 50),
    Small(116, 104),
    Medium(156, 130)
}

enum class OceanShortcutLayoutMode {
    Vertical,
    Horizontal
}