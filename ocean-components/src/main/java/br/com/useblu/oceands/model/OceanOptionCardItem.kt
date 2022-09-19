package br.com.useblu.oceands.model

import android.graphics.drawable.Drawable

data class OceanOptionCardItem(
    val data: Any,
    val icon: String? = null,
    val iconDrawable: Drawable? = null,
    val heightSize: OceanOptionCardSize = OceanOptionCardSize.MEDIUM,
    val title: String? = "",
    val subTitle: String? = "",
    val disabled: Boolean = false,
    val recommend: Boolean = false,
    val recommendColor: String? = null,
    val recommendDescription: String? = "",
)

enum class OptionsCardState {
    ENABLED, DISABLED, RECOMMEND
}

enum class OceanOptionCardSize(val size: Int = 0) {
    SMALL(64), MEDIUM(96), LARGE(180)
}
