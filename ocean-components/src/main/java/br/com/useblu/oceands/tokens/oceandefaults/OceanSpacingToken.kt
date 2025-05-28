package br.com.useblu.oceands.tokens.oceandefaults

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.tokens.SpacingToken

internal class OceanSpacingToken(
    private val context: Context
) : SpacingToken {

    private val resources
        get() = context.resources

    override val xxxs: Dp
        get() = resources.getDimension(R.dimen.ocean_spacing_stack_xxxs).toDp()
    override val xxs: Dp
        get() = resources.getDimension(R.dimen.ocean_spacing_stack_xxs).toDp()
    override val xxsExtra: Dp
        get() = resources.getDimension(R.dimen.ocean_spacing_stack_xxs_extra).toDp()
    override val xs: Dp
        get() = resources.getDimension(R.dimen.ocean_spacing_stack_xs).toDp()
    override val sm: Dp
        get() = resources.getDimension(R.dimen.ocean_spacing_stack_sm).toDp()
    override val md: Dp
        get() = resources.getDimension(R.dimen.ocean_spacing_stack_md).toDp()
    override val lg: Dp
        get() = resources.getDimension(R.dimen.ocean_spacing_stack_lg).toDp()
    override val xl: Dp
        get() = resources.getDimension(R.dimen.ocean_spacing_stack_xl).toDp()
    override val xxl: Dp
        get() = resources.getDimension(R.dimen.ocean_spacing_stack_xxl).toDp()
    override val xxxl: Dp
        get() = resources.getDimension(R.dimen.ocean_spacing_stack_xxxl).toDp()

    fun Float.toDp(): Dp {
        return (this / resources.displayMetrics.density).dp
    }
}
