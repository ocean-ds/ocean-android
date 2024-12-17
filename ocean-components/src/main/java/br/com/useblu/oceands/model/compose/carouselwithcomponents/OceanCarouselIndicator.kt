package br.com.useblu.oceands.model.compose.carouselwithcomponents

enum class OceanCarouselIndicator {
    PAGE,
    NONE;

    fun canShowPages(count: Int): Boolean {
        return when(this) {
            PAGE -> count > 1
            NONE -> false
        }
    }
}