package br.com.useblu.oceands.tokens.oceandefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.tokens.SpacingToken

internal object OceanSpacingToken : SpacingToken {
    override val xxxs: Dp
        @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxxs)
    override val xxs: Dp
        @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxs)
    override val xxsExtra: Dp
        @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxs_extra)
    override val xs: Dp
        @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xs)
    override val sm: Dp
        @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_sm)
    override val md: Dp
        @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_md)
    override val lg: Dp
        @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_lg)
    override val xl: Dp
        @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xl)
    override val xxl: Dp
        @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxl)
    override val xxxl: Dp
        @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxxl)
}
