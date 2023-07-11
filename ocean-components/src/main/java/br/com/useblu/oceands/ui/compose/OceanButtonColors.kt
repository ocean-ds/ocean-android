package br.com.useblu.oceands.ui.compose

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.colorResource
import br.com.useblu.oceands.R


@Immutable
object OceanButtonColors {
    @Composable
    fun primaryDefaultButtonColors() = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.ocean_color_brand_primary_pure),
        contentColor = colorResource(id = R.color.ocean_color_interface_light_pure),
        disabledContainerColor = colorResource(id = R.color.ocean_color_interface_light_down),
        disabledContentColor = colorResource(id = R.color.ocean_color_interface_dark_up)
    )

    @Composable
    fun primaryInverseButtonColors() = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.ocean_color_complementary_pure),
        contentColor = colorResource(id = R.color.ocean_color_interface_light_pure),
        disabledContainerColor = colorResource(id = R.color.ocean_color_interface_light_down),
        disabledContentColor = colorResource(id = R.color.ocean_color_interface_dark_up)
    )

    @Composable
    fun primaryCriticalButtonColors() = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.ocean_color_status_negative_pure),
        contentColor = colorResource(id = R.color.ocean_color_interface_light_pure),
        disabledContainerColor = colorResource(id = R.color.ocean_color_interface_light_down),
        disabledContentColor = colorResource(id = R.color.ocean_color_interface_dark_up)
    )
    
    @Composable
    fun secondaryDefaultButtonColors() = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.ocean_color_interface_light_up),
        contentColor = colorResource(id = R.color.ocean_color_brand_primary_pure),
        disabledContainerColor = colorResource(id = R.color.ocean_color_interface_light_down),
        disabledContentColor = colorResource(id = R.color.ocean_color_interface_dark_up)
    )

    @Composable
    fun secondaryCriticalButtonColors() = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.ocean_color_status_negative_up),
        contentColor = colorResource(id = R.color.ocean_color_status_negative_pure),
        disabledContainerColor = colorResource(id = R.color.ocean_color_interface_light_down),
        disabledContentColor = colorResource(id = R.color.ocean_color_interface_dark_up)
    )
}


