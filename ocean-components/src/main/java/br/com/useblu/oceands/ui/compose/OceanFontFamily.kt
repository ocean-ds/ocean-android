package br.com.useblu.oceands.ui.compose

import br.com.useblu.oceands.OceanDS
import br.com.useblu.oceands.tokens.OceanFontFamilyTokens

object OceanFontFamily {
    private val tokens get() = OceanDS.fontFamilyTokens ?: object : OceanFontFamilyTokens {}

    val BaseBold = tokens.baseBold

    val BaseExtraBold = tokens.baseExtraBold

    val BaseLight = tokens.baseLight

    val BaseMedium = tokens.baseMedium

    val BaseRegular = tokens.baseRegular

    val HighlightBold = tokens.highlightBold

    val HighlightExtraBold = tokens.highlightExtraBold

    val HighlightLight = tokens.highlightLight

    val HighlightMedium = tokens.highlightMedium

    val HighlightRegular = tokens.highlightRegular
}
