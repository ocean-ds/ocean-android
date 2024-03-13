package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.colorResource
import br.com.useblu.oceands.R
import br.com.useblu.oceands.utils.toOceanColor

@Immutable
object OceanColors {

    val interfaceLightPure @Composable get() = colorResource(id = R.color.ocean_color_interface_light_pure)
    val interfaceLightUp @Composable get() = colorResource(id = R.color.ocean_color_interface_light_up)
    val interfaceLightDown @Composable get() = colorResource(id = R.color.ocean_color_interface_light_down)
    val interfaceLightDeep @Composable get() = colorResource(id = R.color.ocean_color_interface_light_deep)
    val interfaceDarkPure @Composable get() = colorResource(id = R.color.ocean_color_interface_dark_pure)
    val interfaceDarkUp @Composable get() = colorResource(id = R.color.ocean_color_interface_dark_up)
    val interfaceDarkDown @Composable get() = colorResource(id = R.color.ocean_color_interface_dark_down)
    val interfaceDarkDeep @Composable get() = colorResource(id = R.color.ocean_color_interface_dark_deep)
    val brandPrimaryPure @Composable get() = colorResource(id = R.color.ocean_color_brand_primary_pure)
    val brandPrimaryUp @Composable get() = colorResource(id = R.color.ocean_color_brand_primary_up)
    val brandPrimaryDown @Composable get() = colorResource(id = R.color.ocean_color_brand_primary_down)
    val brandPrimaryDeep @Composable get() = colorResource(id = R.color.ocean_color_brand_primary_deep)
    val highlightPure @Composable get() = colorResource(id = R.color.ocean_color_highlight_pure)
    val highlightUp @Composable get() = colorResource(id = R.color.ocean_color_highlight_up)
    val highlightDown @Composable get() = colorResource(id = R.color.ocean_color_highlight_down)
    val highlightDeep @Composable get() = colorResource(id = R.color.ocean_color_highlight_deep)
    val complementaryPure @Composable get() = colorResource(id = R.color.ocean_color_complementary_pure)
    val complementaryUp @Composable get() = colorResource(id = R.color.ocean_color_complementary_up)
    val complementaryDown @Composable get() = colorResource(id = R.color.ocean_color_complementary_down)
    val complementaryDeep @Composable get() = colorResource(id = R.color.ocean_color_complementary_deep)
    val statusPositivePure @Composable get() = colorResource(id = R.color.ocean_color_status_positive_pure)
    val statusPositiveUp @Composable get() = colorResource(id = R.color.ocean_color_status_positive_up)
    val statusPositiveDown @Composable get() = colorResource(id = R.color.ocean_color_status_positive_down)
    val statusPositiveDeep @Composable get() = colorResource(id = R.color.ocean_color_status_positive_deep)
    val statusNegativeUp @Composable get() = colorResource(id = R.color.ocean_color_status_negative_up)
    val statusNegativeDown @Composable get() = colorResource(id = R.color.ocean_color_status_negative_down)
    val statusNegativeDeep @Composable get() = colorResource(id = R.color.ocean_color_status_negative_deep)
    val statusNegativePure @Composable get() = colorResource(id = R.color.ocean_color_status_negative_pure)
    val statusWarningPure @Composable get() = colorResource(id = R.color.ocean_color_status_warning_pure)
    val statusWarningUp @Composable get() = colorResource(id = R.color.ocean_color_status_warning_up)
    val statusWarningDown @Composable get() = colorResource(id = R.color.ocean_color_status_warning_down)
    val statusWarningDeep @Composable get() = colorResource(id = R.color.ocean_color_status_warning_deep)

    @Composable
    fun fromString(color: String) = colorResource(color.toOceanColor())
}