package br.com.useblu.oceands.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.R

@Immutable
object OceanFontSize {
    @Composable
    fun xxxs() = dimensionResource(id = R.dimen.ocean_font_size_xxxs).sp

    @Composable
    fun xxs() = dimensionResource(id = R.dimen.ocean_font_size_xxs).sp

    @Composable
    fun xs() = dimensionResource(id = R.dimen.ocean_font_size_xs).sp

    @Composable
    fun sm() = dimensionResource(id = R.dimen.ocean_font_size_sm).sp

    @Composable
    fun md() = dimensionResource(id = R.dimen.ocean_font_size_md).sp

    @Composable
    fun lg() = dimensionResource(id = R.dimen.ocean_font_size_lg).sp

    @Composable
    fun xl() = dimensionResource(id = R.dimen.ocean_font_size_xl).sp

    @Composable
    fun xxl() = dimensionResource(id = R.dimen.ocean_font_size_xxl).sp

    @Composable
    fun xxxl() = dimensionResource(id = R.dimen.ocean_font_size_xxxl).sp

    @Composable
    fun display() = dimensionResource(id = R.dimen.ocean_font_size_display).sp

    @Composable
    fun giant() = dimensionResource(id = R.dimen.ocean_font_size_giant).sp
}

inline val Dp.sp get() = this.value.sp