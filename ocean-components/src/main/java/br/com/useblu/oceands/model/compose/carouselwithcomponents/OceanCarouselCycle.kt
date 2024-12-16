package br.com.useblu.oceands.model.compose.carouselwithcomponents

sealed class OceanCarouselCycle {
    data class Auto(val time: Long = 3000) : OceanCarouselCycle()
    data object Manual : OceanCarouselCycle()
}