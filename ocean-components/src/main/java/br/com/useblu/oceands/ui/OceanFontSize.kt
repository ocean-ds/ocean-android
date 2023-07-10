package br.com.useblu.oceands.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.R

object OceanFontSize {
    @Composable
    fun XXXS() = dimensionResource(id = R.dimen.ocean_font_size_xxxs).sp

    @Composable
    fun XXS() = dimensionResource(id = R.dimen.ocean_font_size_xxs).sp

    @Composable
    fun XS() = dimensionResource(id = R.dimen.ocean_font_size_xs).sp

    @Composable
    fun SM() = dimensionResource(id = R.dimen.ocean_font_size_sm).sp

    @Composable
    fun MD() = dimensionResource(id = R.dimen.ocean_font_size_md).sp

    @Composable
    fun LG() = dimensionResource(id = R.dimen.ocean_font_size_lg).sp

    @Composable
    fun XL() = dimensionResource(id = R.dimen.ocean_font_size_xl).sp

    @Composable
    fun XXL() = dimensionResource(id = R.dimen.ocean_font_size_xxl).sp

    @Composable
    fun XXXL() = dimensionResource(id = R.dimen.ocean_font_size_xxxl).sp

    @Composable
    fun Display() = dimensionResource(id = R.dimen.ocean_font_size_display).sp

    @Composable
    fun Giant() = dimensionResource(id = R.dimen.ocean_font_size_giant).sp
}

inline val Dp.sp get() = this.value.sp