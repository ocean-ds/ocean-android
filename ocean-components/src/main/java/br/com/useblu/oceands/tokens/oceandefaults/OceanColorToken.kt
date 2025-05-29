package br.com.useblu.oceands.tokens.oceandefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import br.com.useblu.oceands.R
import br.com.useblu.oceands.tokens.ColorToken
import br.com.useblu.oceands.utils.toOceanColor

internal object OceanColorToken : ColorToken {
    override val interfaceLightPure @Composable get() = colorResource(id = R.color.ocean_color_interface_light_pure)
    override val interfaceLightUp @Composable get() = colorResource(id = R.color.ocean_color_interface_light_up)
    override val interfaceLightDown @Composable get() = colorResource(id = R.color.ocean_color_interface_light_down)
    override val interfaceLightDeep @Composable get() = colorResource(id = R.color.ocean_color_interface_light_deep)
    override val interfaceDarkPure @Composable get() = colorResource(id = R.color.ocean_color_interface_dark_pure)
    override val interfaceDarkUp @Composable get() = colorResource(id = R.color.ocean_color_interface_dark_up)
    override val interfaceDarkDown @Composable get() = colorResource(id = R.color.ocean_color_interface_dark_down)
    override val interfaceDarkDeep @Composable get() = colorResource(id = R.color.ocean_color_interface_dark_deep)
    override val brandPrimaryPure @Composable get() = colorResource(id = R.color.ocean_color_brand_primary_pure)
    override val brandPrimaryUp @Composable get() = colorResource(id = R.color.ocean_color_brand_primary_up)
    override val brandPrimaryDown @Composable get() = colorResource(id = R.color.ocean_color_brand_primary_down)
    override val brandPrimaryDeep @Composable get() = colorResource(id = R.color.ocean_color_brand_primary_deep)
    override val highlightPure @Composable get() = colorResource(id = R.color.ocean_color_highlight_pure)
    override val highlightUp @Composable get() = colorResource(id = R.color.ocean_color_highlight_up)
    override val highlightDown @Composable get() = colorResource(id = R.color.ocean_color_highlight_down)
    override val highlightDeep @Composable get() = colorResource(id = R.color.ocean_color_highlight_deep)
    override val complementaryPure @Composable get() = colorResource(id = R.color.ocean_color_complementary_pure)
    override val complementaryUp @Composable get() = colorResource(id = R.color.ocean_color_complementary_up)
    override val complementaryDown @Composable get() = colorResource(id = R.color.ocean_color_complementary_down)
    override val complementaryDeep @Composable get() = colorResource(id = R.color.ocean_color_complementary_deep)
    override val statusPositivePure @Composable get() = colorResource(id = R.color.ocean_color_status_positive_pure)
    override val statusPositiveUp @Composable get() = colorResource(id = R.color.ocean_color_status_positive_up)
    override val statusPositiveDown @Composable get() = colorResource(id = R.color.ocean_color_status_positive_down)
    override val statusPositiveDeep @Composable get() = colorResource(id = R.color.ocean_color_status_positive_deep)
    override val statusNegativeUp @Composable get() = colorResource(id = R.color.ocean_color_status_negative_up)
    override val statusNegativeDown @Composable get() = colorResource(id = R.color.ocean_color_status_negative_down)
    override val statusNegativeDeep @Composable get() = colorResource(id = R.color.ocean_color_status_negative_deep)
    override val statusNegativePure @Composable get() = colorResource(id = R.color.ocean_color_status_negative_pure)
    override val statusWarningPure @Composable get() = colorResource(id = R.color.ocean_color_status_warning_pure)
    override val statusWarningUp @Composable get() = colorResource(id = R.color.ocean_color_status_warning_up)
    override val statusWarningDown @Composable get() = colorResource(id = R.color.ocean_color_status_warning_down)
    override val statusWarningDeep @Composable get() = colorResource(id = R.color.ocean_color_status_warning_deep)

    @Composable
    override fun fromString(color: String): Color = colorResource(color.toOceanColor())
}
