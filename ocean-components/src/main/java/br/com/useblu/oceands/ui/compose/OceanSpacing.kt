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
    /**
     * 4dp
     */
    val xxxs @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxxs)

    /**
     * 8dp
     */
    val xxs @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxs)

    /**
     * 16dp
     */
    val xs @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xs)

    /**
     * 24dp
     */
    val sm @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_sm)

    /**
     * 32dp
     */
    val md @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_md)

    /**
     * 40dp
     */
    val lg @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_lg)

    /**
     * 48dp
     */
    val xl @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xl)

    /**
     * 64dp
     */
    val xxl @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxl)

    /**
     * 80dp
     */
    val xxxl @Composable get() = dimensionResource(id = R.dimen.ocean_spacing_stack_xxxl)

    /**
     * 4dp
     */
    @Composable
    fun StackXXXS() {
        Spacer(modifier = Modifier.size(xxxs))
    }

    /**
     * 8dp
     */
    @Composable
    fun StackXXS() {
        Spacer(modifier = Modifier.size(xxs))
    }

    /**
     * 16dp
     */
    @Composable
    fun StackXS() {
        Spacer(modifier = Modifier.size(xs))
    }

    /**
     * 24dp
     */
    @Composable
    fun StackSM() {
        Spacer(modifier = Modifier.size(sm))
    }

    /**
     * 32dp
     */
    @Composable
    fun StackMD() {
        Spacer(modifier = Modifier.size(md))
    }

    /**
     * 40dp
     */
    @Composable
    fun StackLG() {
        Spacer(modifier = Modifier.size(lg))
    }

    /**
     * 48dp
     */
    @Composable
    fun StackXL() {
        Spacer(modifier = Modifier.size(xl))
    }

    /**
     * 64dp
     */
    @Composable
    fun StackXXL() {
        Spacer(modifier = Modifier.size(xxl))
    }

    /**
     * 80dp
     */
    @Composable
    fun StackXXXL() {
        Spacer(modifier = Modifier.size(xxxl))
    }
}