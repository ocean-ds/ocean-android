package br.com.useblu.oceands.utils

import br.com.useblu.oceands.R

fun String.toOceanColor() = when (this.lowercase()) {
    "colorbrandprimarydeep" -> {
        R.color.ocean_color_brand_primary_deep
    }
    "colorbrandprimarydown" -> {
        R.color.ocean_color_brand_primary_down
    }
    "colorbrandprimarypure" -> {
        R.color.ocean_color_brand_primary_pure
    }
    "colorbrandprimaryup" -> {
        R.color.ocean_color_brand_primary_up
    }
    "colorcomplementarydeep" -> {
        R.color.ocean_color_complementary_deep
    }
    "colorcomplementarydown" -> {
        R.color.ocean_color_complementary_down
    }
    "colorcomplementarypure" -> {
        R.color.ocean_color_complementary_pure
    }
    "colorcomplementaryup" -> {
        R.color.ocean_color_complementary_up
    }
    "colorhighlightdeep" -> {
        R.color.ocean_color_highlight_deep
    }
    "colorhighlightdown" -> {
        R.color.ocean_color_highlight_down
    }
    "colorhighlightpure" -> {
        R.color.ocean_color_highlight_pure
    }
    "colorhighlightup" -> {
        R.color.ocean_color_highlight_up
    }
    "colorinterfacedarkdeep" -> {
        R.color.ocean_color_interface_dark_deep
    }
    "colorinterfacedarkdown" -> {
        R.color.ocean_color_interface_dark_down
    }
    "colorinterfacedarkpure" -> {
        R.color.ocean_color_interface_dark_pure
    }
    "colorinterfacedarkup" -> {
        R.color.ocean_color_interface_dark_up
    }
    "colorinterfacelightdeep" -> {
        R.color.ocean_color_interface_light_deep
    }
    "colorinterfacelightdown" -> {
        R.color.ocean_color_interface_light_down
    }
    "colorinterfacelightpure" -> {
        R.color.ocean_color_interface_light_pure
    }
    "colorinterfacelightup" -> {
        R.color.ocean_color_interface_light_up
    }
    "colorstatusnegativedeep" -> {
        R.color.ocean_color_status_negative_deep
    }
    "colorstatusnegativedown" -> {
        R.color.ocean_color_status_negative_down
    }
    "colorstatusnegativepure" -> {
        R.color.ocean_color_status_negative_pure
    }
    "colorstatusnegativeup" -> {
        R.color.ocean_color_status_negative_up
    }
    "colorstatusneutraldeep", "colorstatuswarningdeep" -> {
        R.color.ocean_color_status_warning_deep
    }
    "colorstatusneutraldown", "colorstatuswarningdown" -> {
        R.color.ocean_color_status_warning_down
    }
    "colorstatusneutralpure", "colorstatuswarningpure" -> {
        R.color.ocean_color_status_warning_pure
    }
    "colorstatusneutralup", "colorstatuswarningup" -> {
        R.color.ocean_color_status_warning_up
    }
    "colorstatuspositivedeep" -> {
        R.color.ocean_color_status_positive_deep
    }
    "colorstatuspositivedown" -> {
        R.color.ocean_color_status_positive_down
    }
    "colorstatuspositivepure" -> {
        R.color.ocean_color_status_positive_pure
    }
    "colorstatuspositiveup" -> {
        R.color.ocean_color_status_positive_up
    }
    else -> {
        R.color.ocean_color_interface_dark_down
    }
}
