package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.OceanDS

@Immutable
object OceanFontSize {
    private val token get() = OceanDS.fontToken

    /**
     * 12 sp (base)
     */
    val xxxs get() = token.xxxs

    /**
     * 14 sp (base)
     */
    val xxs get() = token.xxs

    /**
     * 16 sp (base)
     */
    val xs get() = token.xs

    /**
     * 20 sp (base)
     */
    val sm get() = token.sm

    /**
     * 24 sp (base)
     */
    val md get() = token.md

    /**
     * 32 sp (base)
     */
    val lg get() = token.lg

    /**
     * 40 sp (base)
     */
    val xl get() = token.xl

    /**
     * 48 sp (base)
     */
    val xxl get() = token.xxl

    /**
     * 64 sp (base)
     */
    val xxxl get() = token.xxxl

    /**
     * 80 sp (base)
     */
    val display get() = token.display

    /**
     * 96 sp (base)
     */
    val giant get() = token.giant
}
