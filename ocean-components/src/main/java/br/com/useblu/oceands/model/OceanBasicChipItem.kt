package br.com.useblu.oceands.model

import android.graphics.drawable.Drawable

data class OceanBasicChipItem(
    val label: String,
    val id: String,
    val badge: Badge? = null,
    val icon: Drawable? = null,
    var state: OceanChipItemState = OceanChipItemState.DEFAULT,
    var action: () -> Unit = {}
)