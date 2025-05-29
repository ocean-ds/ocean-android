package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.OceanDS
import br.com.useblu.oceands.tokens.oceandefaults.OceanFontToken

@Immutable
object OceanFontSize {
    private val token get() = OceanDS.fontToken ?: OceanFontToken

    /**
     * 12 sp (base)
     */
    val xxxs @Composable get() = token.xxxs

    /**
     * 14 sp (base)
     */
    val xxs @Composable get() = token.xxs

    /**
     * 16 sp (base)
     */
    val xs @Composable get() = token.xs

    /**
     * 20 sp (base)
     */
    val sm @Composable get() = token.sm

    /**
     * 24 sp (base)
     */
    val md @Composable get() = token.md

    /**
     * 32 sp (base)
     */
    val lg @Composable get() = token.lg

    /**
     * 40 sp (base)
     */
    val xl @Composable get() = token.xl

    /**
     * 48 sp (base)
     */
    val xxl @Composable get() = token.xxl

    /**
     * 64 sp (base)
     */
    val xxxl @Composable get() = token.xxxl

    /**
     * 80 sp (base)
     */
    val display @Composable get() = token.display

    /**
     * 96 sp (base)
     */
    val giant @Composable get() = token.giant
}
