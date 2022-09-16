package br.com.useblu.oceands.model

import android.graphics.drawable.Drawable

data class OceanChipItem(
    val label: String,
    val id: String,
    val badge: Badge? = null,
    val icon: Drawable? = null,
    val hasClose: Boolean = false,
    var state: OceanChipItemState = OceanChipItemState.DEFAULT,
    val action: () -> Unit = {}
)