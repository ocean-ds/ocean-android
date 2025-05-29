package br.com.useblu.oceands.tokens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import br.com.useblu.oceands.R
import br.com.useblu.oceands.utils.toOceanColor

interface OceanColorTokens {
    @get:Composable
    val interfaceLightPure: Color
        get() = colorResource(id = R.color.ocean_color_interface_light_pure)

    @get:Composable
    val interfaceLightUp: Color
        get() = colorResource(id = R.color.ocean_color_interface_light_up)

    @get:Composable
    val interfaceLightDown: Color
        get() = colorResource(id = R.color.ocean_color_interface_light_down)

    @get:Composable
    val interfaceLightDeep: Color
        get() = colorResource(id = R.color.ocean_color_interface_light_deep)

    @get:Composable
    val interfaceDarkPure: Color
        get() = colorResource(id = R.color.ocean_color_interface_dark_pure)

    @get:Composable
    val interfaceDarkUp: Color
        get() = colorResource(id = R.color.ocean_color_interface_dark_up)

    @get:Composable
    val interfaceDarkDown: Color
        get() = colorResource(id = R.color.ocean_color_interface_dark_down)

    @get:Composable
    val interfaceDarkDeep: Color
        get() = colorResource(id = R.color.ocean_color_interface_dark_deep)

    @get:Composable
    val brandPrimaryPure: Color
        get() = colorResource(id = R.color.ocean_color_brand_primary_pure)

    @get:Composable
    val brandPrimaryUp: Color
        get() = colorResource(id = R.color.ocean_color_brand_primary_up)

    @get:Composable
    val brandPrimaryDown: Color
        get() = colorResource(id = R.color.ocean_color_brand_primary_down)

    @get:Composable
    val brandPrimaryDeep: Color
        get() = colorResource(id = R.color.ocean_color_brand_primary_deep)

    @get:Composable
    val highlightPure: Color
        get() = colorResource(id = R.color.ocean_color_highlight_pure)

    @get:Composable
    val highlightUp: Color
        get() = colorResource(id = R.color.ocean_color_highlight_up)

    @get:Composable
    val highlightDown: Color
        get() = colorResource(id = R.color.ocean_color_highlight_down)

    @get:Composable
    val highlightDeep: Color
        get() = colorResource(id = R.color.ocean_color_highlight_deep)

    @get:Composable
    val complementaryPure: Color
        get() = colorResource(id = R.color.ocean_color_complementary_pure)

    @get:Composable
    val complementaryUp: Color
        get() = colorResource(id = R.color.ocean_color_complementary_up)

    @get:Composable
    val complementaryDown: Color
        get() = colorResource(id = R.color.ocean_color_complementary_down)

    @get:Composable
    val complementaryDeep: Color
        get() = colorResource(id = R.color.ocean_color_complementary_deep)

    @get:Composable
    val statusPositivePure: Color
        get() = colorResource(id = R.color.ocean_color_status_positive_pure)

    @get:Composable
    val statusPositiveUp: Color
        get() = colorResource(id = R.color.ocean_color_status_positive_up)

    @get:Composable
    val statusPositiveDown: Color
        get() = colorResource(id = R.color.ocean_color_status_positive_down)

    @get:Composable
    val statusPositiveDeep: Color
        get() = colorResource(id = R.color.ocean_color_status_positive_deep)

    @get:Composable
    val statusNegativeUp: Color
        get() = colorResource(id = R.color.ocean_color_status_negative_up)

    @get:Composable
    val statusNegativeDown: Color
        get() = colorResource(id = R.color.ocean_color_status_negative_down)

    @get:Composable
    val statusNegativeDeep: Color
        get() = colorResource(id = R.color.ocean_color_status_negative_deep)

    @get:Composable
    val statusNegativePure: Color
        get() = colorResource(id = R.color.ocean_color_status_negative_pure)

    @get:Composable
    val statusWarningPure: Color
        get() = colorResource(id = R.color.ocean_color_status_warning_pure)

    @get:Composable
    val statusWarningUp: Color
        get() = colorResource(id = R.color.ocean_color_status_warning_up)

    @get:Composable
    val statusWarningDown: Color
        get() = colorResource(id = R.color.ocean_color_status_warning_down)

    @get:Composable
    val statusWarningDeep: Color
        get() = colorResource(id = R.color.ocean_color_status_warning_deep)

    @Composable
    fun fromString(color: String): Color = colorResource(color.toOceanColor())
}
