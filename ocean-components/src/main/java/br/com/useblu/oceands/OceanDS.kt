package br.com.useblu.oceands

import android.content.Context
import br.com.useblu.oceands.tokens.FontFamilyToken
import br.com.useblu.oceands.tokens.FontToken
import br.com.useblu.oceands.tokens.SpacingToken
import br.com.useblu.oceands.tokens.oceandefaults.OceanFontFamilyToken
import br.com.useblu.oceands.tokens.oceandefaults.OceanFontToken
import br.com.useblu.oceands.tokens.oceandefaults.OceanSpacingToken

class OceanDS {
    companion object {
        internal lateinit var fontToken: FontToken
        internal lateinit var fontFamilyToken: FontFamilyToken
        internal lateinit var spacingToken: SpacingToken

        fun initialize(
            context: Context,
            fontToken: FontToken? = null,
            fontFamilyToken: FontFamilyToken? = null,
            spacingToken: SpacingToken? = null
        ) {
            this.fontToken = fontToken ?: OceanFontToken(context = context)
            this.fontFamilyToken = fontFamilyToken ?: OceanFontFamilyToken()
            this.spacingToken = spacingToken ?: OceanSpacingToken(context = context)
        }
    }
}
