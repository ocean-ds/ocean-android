package br.com.useblu.oceands.ui

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import br.com.useblu.oceands.R

@Composable
fun primaryDefaultButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorResource(id = R.color.ocean_color_brand_primary_pure),
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