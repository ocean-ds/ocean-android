package br.com.useblu.oceands.model

import androidx.annotation.ColorRes

data class OceanDonutItem(
    val label: String,
    val value: Double,
    @ColorRes val color: Int
)
