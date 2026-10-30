package br.com.useblu.oceands.ui.compose

sealed interface OceanOpacity {
    /** 0.8f */
    data object Semiopaque : OceanOpacity

    /** 0.64f */
    data object Intense : OceanOpacity

    /** 0.4f */
    data object Medium : OceanOpacity

    /** 0.16f */
    data object Light : OceanOpacity

    /** 0.08f */
    data object Semitransparent : OceanOpacity

    val value: Float
        get() = when (this) {
            Semiopaque -> 0.8f
            Intense -> 0.64f
            Medium -> 0.4f
            Light -> 0.16f
            Semitransparent -> 0.08f
        }
}
