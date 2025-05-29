package br.com.useblu.oceands.ui.compose

import br.com.useblu.oceands.OceanDS
import br.com.useblu.oceands.tokens.oceandefaults.OceanFontFamilyToken

object OceanFontFamily {
    private val token get() = OceanDS.fontFamilyToken ?: OceanFontFamilyToken

    val BaseBold = token.baseBold

    val BaseExtraBold = token.baseExtraBold

    val BaseLight = token.baseLight

    val BaseMedium = token.baseMedium

    val BaseRegular = token.baseRegular

    val HighlightBold = token.highlightBold

    val HighlightExtraBold = token.highlightExtraBold

    val HighlightLight = token.highlightLight

    val HighlightMedium = token.highlightMedium

    val HighlightRegular = token.highlightRegular
}
