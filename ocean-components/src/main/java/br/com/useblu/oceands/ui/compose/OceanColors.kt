package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.colorResource
import br.com.useblu.oceands.R

@Immutable
object OceanColors {

    @Composable
    fun interfaceLightPure() = colorResource(id = R.color.ocean_color_interface_light_pure)

    @Composable
    fun interfaceLightUp() = colorResource(id = R.color.ocean_color_interface_light_up)

    @Composable
    fun interfaceLightDown() = colorResource(id = R.color.ocean_color_interface_light_down)

    @Composable
    fun interfaceDarkUp() = colorResource(id = R.color.ocean_color_interface_dark_up)

    @Composable
    fun interfaceDarkDown() = colorResource(id = R.color.ocean_color_interface_dark_down)

    @Composable
    fun brandPrimaryPure() = colorResource(id = R.color.ocean_color_brand_primary_pure)
}