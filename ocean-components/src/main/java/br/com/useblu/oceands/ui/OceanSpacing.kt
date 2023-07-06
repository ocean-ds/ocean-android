package br.com.useblu.oceands.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import br.com.useblu.oceands.R

object OceanSpacing {
    @Composable
    fun StackXXXS() {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.ocean_spacing_stack_xxxs)))
    }

    @Composable
    fun StackXXS() {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.ocean_spacing_stack_xxs)))
    }

    @Composable
    fun StackXS() {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.ocean_spacing_stack_xs)))
    }

    @Composable
    fun StackSM() {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.ocean_spacing_stack_sm)))
    }

    @Composable
    fun StackMD() {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.ocean_spacing_stack_md)))
    }

    @Composable
    fun StackLG() {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.ocean_spacing_stack_lg)))
    }

    @Composable
    fun StackXL() {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.ocean_spacing_stack_xl)))
    }

    @Composable
    fun StackXXL() {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.ocean_spacing_stack_xxl)))
    }

    @Composable
    fun StackXXXL() {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.ocean_spacing_stack_xxxl)))
    }
}