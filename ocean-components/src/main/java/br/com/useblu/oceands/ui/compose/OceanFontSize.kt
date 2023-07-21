package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.dimensionResource
import br.com.useblu.oceands.R

@Immutable
object OceanFontSize {
    val xxxs @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xxxs).sp
    val xxs @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xxs).sp
    val xs @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xs).sp
    val sm @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_sm).sp
    val md @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_md).sp
    val lg @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_lg).sp
    val xl @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xl).sp
    val xxl @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xxl).sp
    val xxxl @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xxxl).sp
    val display @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_display).sp
    val giant @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_giant).sp
}