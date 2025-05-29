package br.com.useblu.oceands.tokens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import br.com.useblu.oceands.R

interface OceanSpacingTokens {
    @get:Composable
    val xxxs: Dp
        get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxxs)

    @get:Composable
    val xxs: Dp
        get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxs)

    @get:Composable
    val xxsExtra: Dp
        get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxs_extra)

    @get:Composable
    val xs: Dp
        get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xs)

    @get:Composable
    val sm: Dp
        get() = dimensionResource(id = R.dimen.ocean_spacing_stack_sm)

    @get:Composable
    val md: Dp
        get() = dimensionResource(id = R.dimen.ocean_spacing_stack_md)

    @get:Composable
    val lg: Dp
        get() = dimensionResource(id = R.dimen.ocean_spacing_stack_lg)

    @get:Composable
    val xl: Dp
        get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xl)

    @get:Composable
    val xxl: Dp
        get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxl)

    @get:Composable
    val xxxl: Dp
        get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxxl)
}
