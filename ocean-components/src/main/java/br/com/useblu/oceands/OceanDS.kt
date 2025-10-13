package br.com.useblu.oceands

import androidx.compose.ui.text.input.KeyboardType
import br.com.useblu.oceands.tokens.OceanColorTokens
import br.com.useblu.oceands.tokens.OceanFontFamilyTokens
import br.com.useblu.oceands.tokens.OceanFontSizeTokens
import br.com.useblu.oceands.tokens.OceanSpacingTokens

class OceanDS {
    companion object {
        internal var fontSizeTokens: OceanFontSizeTokens? = null
        internal var fontFamilyTokens: OceanFontFamilyTokens? = null
        internal var spacingTokens: OceanSpacingTokens? = null
        internal var colorTokens: OceanColorTokens? = null

        internal var disabledKeyboards: Set<KeyboardType> = emptySet()

        fun usesPhysicalNumericKeyboard(): Boolean = disabledKeyboards.contains(KeyboardType.Number)

        fun initialize(
            fontSizeTokens: OceanFontSizeTokens? = null,
            fontFamilyTokens: OceanFontFamilyTokens? = null,
            spacingTokens: OceanSpacingTokens? = null,
            colorTokens: OceanColorTokens? = null,
            disabledKeyboards: Set<KeyboardType> = emptySet()
        ) {
            this.fontSizeTokens = fontSizeTokens
            this.fontFamilyTokens = fontFamilyTokens
            this.spacingTokens = spacingTokens
            this.colorTokens = colorTokens
            this.disabledKeyboards = disabledKeyboards
        }
    }
}
