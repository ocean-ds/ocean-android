package br.com.useblu.oceands

import br.com.useblu.oceands.tokens.FontFamilyToken
import br.com.useblu.oceands.tokens.FontToken
import br.com.useblu.oceands.tokens.SpacingToken
import br.com.useblu.oceands.tokens.oceandefaults.OceanFontFamilyToken
import br.com.useblu.oceands.tokens.oceandefaults.OceanFontToken
import br.com.useblu.oceands.tokens.oceandefaults.OceanSpacingToken

class OceanDS {
    companion object {
        private var _fontToken: FontToken? = null
        private var _fontFamilyToken: FontFamilyToken? = null
        private var _spacingToken: SpacingToken? = null
        internal val fontToken: FontToken get() = _fontToken ?: OceanFontToken
        internal val fontFamilyToken: FontFamilyToken get() = _fontFamilyToken ?: OceanFontFamilyToken
        internal val spacingToken: SpacingToken get() = _spacingToken ?: OceanSpacingToken

        fun initialize(
            fontToken: FontToken? = null,
            fontFamilyToken: FontFamilyToken? = null,
            spacingToken: SpacingToken? = null
        ) {
            _fontToken = fontToken
            _fontFamilyToken = fontFamilyToken
            _spacingToken = spacingToken
        }
    }
}
