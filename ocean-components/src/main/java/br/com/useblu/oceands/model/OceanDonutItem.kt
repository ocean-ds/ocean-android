package br.com.useblu.oceands.model

import androidx.annotation.ColorRes

data class OceanDonutItem(
    val label: String = "",
    val value: Float = 0f,
    val formattedValue: String = "",
    @ColorRes val color: Int
)
