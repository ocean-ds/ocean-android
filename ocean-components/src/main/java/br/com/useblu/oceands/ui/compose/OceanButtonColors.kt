package br.com.useblu.oceands.ui.compose

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.colorResource
import br.com.useblu.oceands.R


@Immutable
object OceanButtonColors {
    val primaryDefaultButtonColors @Composable get() = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.ocean_color_brand_primary_pure),
        contentColor = colorResource(id = R.color.ocean_color_interface_light_pure),
        disabledContainerColor = colorResource(id = R.color.ocean_color_interface_light_down),
        disabledContentColor = colorResource(id = R.color.ocean_color_interface_dark_up)
    )

    val primaryInverseButtonColors @Composable get() = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.ocean_color_complementary_pure),
        contentColor = colorResource(id = R.color.ocean_color_interface_light_pure),
        disabledContainerColor = colorResource(id = R.color.ocean_color_interface_light_down),
        disabledContentColor = colorResource(id = R.color.ocean_color_interface_dark_up)
    )

    val primaryCriticalButtonColors @Composable get() = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.ocean_color_status_negative_pure),
        contentColor = colorResource(id = R.color.ocean_color_interface_light_pure),
        disabledContainerColor = colorResource(id = R.color.ocean_color_interface_light_down),
        disabledContentColor = colorResource(id = R.color.ocean_color_interface_dark_up)
    )
    
    val secondaryDefaultButtonColors @Composable get() = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.ocean_color_interface_light_up),
        contentColor = colorResource(id = R.color.ocean_color_brand_primary_pure),
        disabledContainerColor = colorResource(id = R.color.ocean_color_interface_light_down),
        disabledContentColor = colorResource(id = R.color.ocean_color_interface_dark_up)
    )

    val secondaryCriticalButtonColors @Composable get() = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.ocean_color_status_negative_up),
        contentColor = colorResource(id = R.color.ocean_color_status_negative_pure),
        disabledContainerColor = colorResource(id = R.color.ocean_color_interface_light_down),
        disabledContentColor = colorResource(id = R.color.ocean_color_interface_dark_up)
    )
}


