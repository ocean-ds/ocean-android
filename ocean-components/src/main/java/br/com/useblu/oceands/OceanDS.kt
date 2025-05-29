package br.com.useblu.oceands

import br.com.useblu.oceands.tokens.ColorToken
import br.com.useblu.oceands.tokens.FontFamilyToken
import br.com.useblu.oceands.tokens.FontToken
import br.com.useblu.oceands.tokens.SpacingToken

class OceanDS {
    companion object {
        internal var fontToken: FontToken? = null
        internal var fontFamilyToken: FontFamilyToken? = null
        internal var spacingToken: SpacingToken? = null
        internal var colorToken: ColorToken? = null

        fun initialize(
            fontToken: FontToken? = null,
            fontFamilyToken: FontFamilyToken? = null,
            spacingToken: SpacingToken? = null,
            colorToken: ColorToken? = null
        ) {
            this.fontToken = fontToken
            this.fontFamilyToken = fontFamilyToken
            this.spacingToken = spacingToken
            this.colorToken = colorToken
        }
    }
}
