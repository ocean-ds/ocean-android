package br.com.useblu.oceands.model

import androidx.compose.runtime.Immutable

@Immutable
data class OceanCarouselItem(
    val url: String,
    val action: () -> Unit = {}
)