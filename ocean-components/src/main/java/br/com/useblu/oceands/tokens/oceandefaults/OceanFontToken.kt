package br.com.useblu.oceands.tokens.oceandefaults

import android.content.Context
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.tokens.FontToken

internal class OceanFontToken(
    private val context: Context
) : FontToken {

    private val resources
        get() = context.resources

    override val xxxs: TextUnit
        get() = resources.getDimension(R.dimen.ocean_font_size_xxxs).toSp()
    override val xxs: TextUnit
        get() = resources.getDimension(R.dimen.ocean_font_size_xxs).toSp()
    override val xs: TextUnit
        get() = resources.getDimension(R.dimen.ocean_font_size_xs).toSp()
    override val sm: TextUnit
        get() = resources.getDimension(R.dimen.ocean_font_size_sm).toSp()
    override val md: TextUnit
        get() = resources.getDimension(R.dimen.ocean_font_size_md).toSp()
    override val lg: TextUnit
        get() = resources.getDimension(R.dimen.ocean_font_size_lg).toSp()
    override val xl: TextUnit
        get() = resources.getDimension(R.dimen.ocean_font_size_xl).toSp()
    override val xxl: TextUnit
        get() = resources.getDimension(R.dimen.ocean_font_size_xxl).toSp()
    override val xxxl: TextUnit
        get() = resources.getDimension(R.dimen.ocean_font_size_xxxl).toSp()
    override val display: TextUnit
        get() = resources.getDimension(R.dimen.ocean_font_size_display).toSp()
    override val giant: TextUnit
        get() = resources.getDimension(R.dimen.ocean_font_size_giant).toSp()

    fun Float.toSp(): TextUnit {
        return (this / resources.displayMetrics.scaledDensity).sp
    }
}
