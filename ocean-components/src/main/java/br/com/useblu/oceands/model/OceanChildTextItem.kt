package br.com.useblu.oceands.model

import android.graphics.drawable.Drawable

data class OceanChildTextItem(
    val image: Drawable?,
    val title: String?,
    val subTitle: String?,
    var isEdit: Boolean = false,
    var isRemove: Boolean = false
)
