package br.com.useblu.oceands.model

import android.graphics.drawable.Drawable

data class OceanParentTextItem(
    val image: Drawable?,
    val title: String?,
    val subTitle: String?,
    val children: List<OceanChildTextItem> = emptyList()
)

