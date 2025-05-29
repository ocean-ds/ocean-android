package br.com.useblu.oceands.tokens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.TextUnit
import br.com.useblu.oceands.R
import br.com.useblu.oceands.ui.compose.sp

interface OceanFontSizeTokens {
    @get:Composable
    val xxxs: TextUnit
        get() = dimensionResource(id = R.dimen.ocean_font_size_xxxs).sp

    @get:Composable
    val xxs: TextUnit
        get() = dimensionResource(id = R.dimen.ocean_font_size_xxs).sp

    @get:Composable
    val xs: TextUnit
        get() = dimensionResource(id = R.dimen.ocean_font_size_xs).sp

    @get:Composable
    val sm: TextUnit
        get() = dimensionResource(id = R.dimen.ocean_font_size_sm).sp

    @get:Composable
    val md: TextUnit
        get() = dimensionResource(id = R.dimen.ocean_font_size_md).sp

    @get:Composable
    val lg: TextUnit
        get() = dimensionResource(id = R.dimen.ocean_font_size_lg).sp

    @get:Composable
    val xl: TextUnit
        get() = dimensionResource(id = R.dimen.ocean_font_size_xl).sp

    @get:Composable
    val xxl: TextUnit
        get() = dimensionResource(id = R.dimen.ocean_font_size_xxl).sp

    @get:Composable
    val xxxl: TextUnit
        get() = dimensionResource(id = R.dimen.ocean_font_size_xxxl).sp

    @get:Composable
    val display: TextUnit
        get() = dimensionResource(id = R.dimen.ocean_font_size_display).sp

    @get:Composable
    val giant: TextUnit
        get() = dimensionResource(id = R.dimen.ocean_font_size_giant).sp
}
