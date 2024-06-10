package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.dimensionResource
import br.com.useblu.oceands.R

@Immutable
object OceanFontSize {
    /**
    * 12 sp
    */
    val xxxs @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xxxs).sp

    /**
     * 14 sp
     */
    val xxs @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xxs).sp

    /**
     * 16 sp
     */
    val xs @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xs).sp

    /**
     * 20 sp
     */
    val sm @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_sm).sp

    /**
     * 24 sp
     */
    val md @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_md).sp

    /**
     * 32 sp
     */
    val lg @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_lg).sp

    /**
     * 40 sp
     */
    val xl @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xl).sp

    /**
     * 48 sp
     */
    val xxl @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xxl).sp

    /**
     * 64 sp
     */
    val xxxl @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_xxxl).sp

    /**
     * 80 sp
     */
    val display @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_display).sp

    /**
     * 96 sp
     */
    val giant @Composable get() = dimensionResource(id = R.dimen.ocean_font_size_giant).sp
}