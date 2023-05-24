package br.com.useblu.oceands.model

import androidx.annotation.ColorRes

data class OceanUnorderedListItem(
    val title: String,
    val icon: String,
    val needsRoundBackgroundIcon: Boolean,
    val needTrailingSpacer: Boolean = false,
    val needLeadingSpacer: Boolean = false,
    @ColorRes val iconColor: Int? = null,
    @ColorRes val roundBackgroundColor: Int? = null
)
