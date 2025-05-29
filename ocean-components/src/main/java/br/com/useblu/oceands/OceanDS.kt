package br.com.useblu.oceands

import br.com.useblu.oceands.tokens.OceanColorTokens
import br.com.useblu.oceands.tokens.OceanFontFamilyTokens
import br.com.useblu.oceands.tokens.OceanFontSizeTokens
import br.com.useblu.oceands.tokens.OceanSpacingTokens

class OceanDS {
    companion object {
        internal var fontTokens: OceanFontSizeTokens? = null
        internal var fontFamilyTokens: OceanFontFamilyTokens? = null
        internal var spacingTokens: OceanSpacingTokens? = null
        internal var colorTokens: OceanColorTokens? = null

        fun initialize(
            fontTokens: OceanFontSizeTokens? = null,
            fontFamilyTokens: OceanFontFamilyTokens? = null,
            spacingTokens: OceanSpacingTokens? = null,
            colorTokens: OceanColorTokens? = null
        ) {
            this.fontTokens = fontTokens
            this.fontFamilyTokens = fontFamilyTokens
            this.spacingTokens = spacingTokens
            this.colorTokens = colorTokens
        }
    }
}
