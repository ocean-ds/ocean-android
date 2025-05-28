package br.com.useblu.oceands.tokens.oceandefaults

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import br.com.useblu.oceands.R
import br.com.useblu.oceands.tokens.FontFamilyToken

internal class OceanFontFamilyToken : FontFamilyToken {
    override val baseBold: FontFamily = FontFamily(Font(R.font.font_family_base_bold))
    override val baseExtraBold: FontFamily = FontFamily(Font(R.font.font_family_base_extrabold))
    override val baseLight: FontFamily = FontFamily(Font(R.font.font_family_base_light))
    override val baseMedium: FontFamily = FontFamily(Font(R.font.font_family_base_medium))
    override val baseRegular: FontFamily = FontFamily(Font(R.font.font_family_base_regular))
    override val highlightBold: FontFamily = FontFamily(Font(R.font.font_family_highlight_bold))
    override val highlightExtraBold: FontFamily = FontFamily(Font(R.font.font_family_highlight_extrabold))
    override val highlightLight: FontFamily = FontFamily(Font(R.font.font_family_highlight_light))
    override val highlightMedium: FontFamily = FontFamily(Font(R.font.font_family_highlight_medium))
    override val highlightRegular: FontFamily = FontFamily(Font(R.font.font_family_highlight_regular))
}
