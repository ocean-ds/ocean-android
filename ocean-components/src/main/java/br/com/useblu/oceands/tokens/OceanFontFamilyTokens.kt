package br.com.useblu.oceands.tokens

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import br.com.useblu.oceands.R

interface OceanFontFamilyTokens {
    val baseBold: FontFamily
        get() = FontFamily(Font(R.font.font_family_base_bold))
    val baseExtraBold: FontFamily
        get() = FontFamily(Font(R.font.font_family_base_extrabold))
    val baseLight: FontFamily
        get() = FontFamily(Font(R.font.font_family_base_light))
    val baseMedium: FontFamily
        get() = FontFamily(Font(R.font.font_family_base_medium))
    val baseRegular: FontFamily
        get() = FontFamily(Font(R.font.font_family_base_regular))
    val highlightBold: FontFamily
        get() = FontFamily(Font(R.font.font_family_highlight_bold))
    val highlightExtraBold: FontFamily
        get() = FontFamily(Font(R.font.font_family_highlight_extrabold))
    val highlightLight: FontFamily
        get() = FontFamily(Font(R.font.font_family_highlight_light))
    val highlightMedium: FontFamily
        get() = FontFamily(Font(R.font.font_family_highlight_medium))
    val highlightRegular: FontFamily
        get() = FontFamily(Font(R.font.font_family_highlight_regular))
}
