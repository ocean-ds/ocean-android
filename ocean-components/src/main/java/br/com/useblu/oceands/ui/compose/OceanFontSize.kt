package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.OceanDS
import br.com.useblu.oceands.tokens.OceanFontSizeTokens

@Immutable
object OceanFontSize {
    private val tokens get() = OceanDS.fontSizeTokens ?: object : OceanFontSizeTokens {}

    /**
     * 12 sp (base)
     */
    val xxxs @Composable get() = tokens.xxxs

    /**
     * 14 sp (base)
     */
    val xxs @Composable get() = tokens.xxs

    /**
     * 16 sp (base)
     */
    val xs @Composable get() = tokens.xs

    /**
     * 20 sp (base)
     */
    val sm @Composable get() = tokens.sm

    /**
     * 24 sp (base)
     */
    val md @Composable get() = tokens.md

    /**
     * 32 sp (base)
     */
    val lg @Composable get() = tokens.lg

    /**
     * 40 sp (base)
     */
    val xl @Composable get() = tokens.xl

    /**
     * 48 sp (base)
     */
    val xxl @Composable get() = tokens.xxl

    /**
     * 64 sp (base)
     */
    val xxxl @Composable get() = tokens.xxxl

    /**
     * 80 sp (base)
     */
    val display @Composable get() = tokens.display

    /**
     * 96 sp (base)
     */
    val giant @Composable get() = tokens.giant
}
