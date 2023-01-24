package br.com.useblu.oceands.model

import android.content.Context
import android.view.ViewGroup.LayoutParams

data class OceanShortcutItem(
    val iconUrl: String? = null,
    val label: String,
    val count: String? = null,
    val subTitle: String? = null,
    val badgeType: OceanBadgeType? = null,
    val action: () -> Unit = {},
    val size: OceanShortcutCardSize = OceanShortcutCardSize.Small,
    val viewMode: OceanShortcutViewMode = OceanShortcutViewMode.None
) {
    fun hasSubtitle() = size == OceanShortcutCardSize.Medium

    fun getCardHeight(context: Context): Int {
        return (size.height * context.resources.displayMetrics.density).toInt()
    }

    fun getCardWidth(context: Context): Int {
        return when (viewMode) {
            OceanShortcutViewMode.Vertical -> {
                return LayoutParams.MATCH_PARENT
            }
            else -> (size.width * context.resources.displayMetrics.density).toInt()
        }
    }
}

enum class OceanShortcutCardSize(val width: Int, val height: Int) {
    Tiny(80, 80),
    Small(116, 104),
    Medium(156, 130)
}

enum class OceanShortcutViewMode {
    Vertical,
    Horizontal,
    None
}