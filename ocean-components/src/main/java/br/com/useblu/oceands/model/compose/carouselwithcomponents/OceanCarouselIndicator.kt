package br.com.useblu.oceands.model.compose.carouselwithcomponents

enum class OceanCarouselIndicator {
    PAGE {
        override fun canShowPages(count: Int): Boolean {
            return count > 1
        }
    },
    NONE {
        override fun canShowPages(count: Int): Boolean {
            return false
        }
    };

    abstract fun canShowPages(count: Int): Boolean
}