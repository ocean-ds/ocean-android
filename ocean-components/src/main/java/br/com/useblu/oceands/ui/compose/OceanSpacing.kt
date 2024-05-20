package br.com.useblu.oceands.ui.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import br.com.useblu.oceands.R

@Immutable
object OceanSpacing {
    val xxxs @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxxs)
    val xxs @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxs)
    val xs @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xs)
    val sm @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_sm)
    val md @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_md)
    val lg @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_lg)
    val xl @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xl)
    val xxl @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxl)
    val xxxl @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxxl)

    @Composable
    fun StackXXXS() {
        Spacer(modifier = Modifier.size(xxxs))
    }

    @Composable
    fun StackXXS() {
        Spacer(modifier = Modifier.size(xxs))
    }

    @Composable
    fun StackXS() {
        Spacer(modifier = Modifier.size(xs))
    }

    @Composable
    fun StackSM() {
        Spacer(modifier = Modifier.size(sm))
    }

    @Composable
    fun StackMD() {
        Spacer(modifier = Modifier.size(md))
    }

    @Composable
    fun StackLG() {
        Spacer(modifier = Modifier.size(lg))
    }

    @Composable
    fun StackXL() {
        Spacer(modifier = Modifier.size(xl))
    }

    @Composable
    fun StackXXL() {
        Spacer(modifier = Modifier.size(xxl))
    }

    @Composable
    fun StackXXXL() {
        Spacer(modifier = Modifier.size(xxxl))
    }
}