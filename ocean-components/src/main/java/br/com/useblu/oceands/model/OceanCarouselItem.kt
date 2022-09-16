package br.com.useblu.oceands.model

data class OceanCarouselItem(
    val url: String,
    val action: () -> Unit = {}
)