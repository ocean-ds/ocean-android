package br.com.useblu.oceands.tokens.oceandefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.TextUnit
import br.com.useblu.oceands.R
import br.com.useblu.oceands.tokens.FontToken
import br.com.useblu.oceands.ui.compose.sp

internal object OceanFontToken : FontToken {

    override val xxxs: TextUnit
        @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xxxs).sp
    override val xxs: TextUnit
        @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xxs).sp
    override val xs: TextUnit
        @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xs).sp
    override val sm: TextUnit
        @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_sm).sp
    override val md: TextUnit
        @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_md).sp
    override val lg: TextUnit
        @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_lg).sp
    override val xl: TextUnit
        @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xl).sp
    override val xxl: TextUnit
        @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xxl).sp
    override val xxxl: TextUnit
        @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xxxl).sp
    override val display: TextUnit
        @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_display).sp
    override val giant: TextUnit
        @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_giant).sp
}
