package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.colorResource
import br.com.useblu.oceands.OceanDS
import br.com.useblu.oceands.tokens.OceanColorTokens
import br.com.useblu.oceands.utils.toOceanColor

@Immutable
object OceanColors {
    private val tokens = OceanDS.colorTokens ?: object : OceanColorTokens {}

    val interfaceLightPure @Composable get() = tokens.interfaceLightPure
    val interfaceLightUp @Composable get() = tokens.interfaceLightUp
    val interfaceLightDown @Composable get() = tokens.interfaceLightDown
    val interfaceLightDeep @Composable get() = tokens.interfaceLightDeep
    val interfaceDarkPure @Composable get() = tokens.interfaceDarkPure
    val interfaceDarkUp @Composable get() = tokens.interfaceDarkUp
    val interfaceDarkDown @Composable get() = tokens.interfaceDarkDown
    val interfaceDarkDeep @Composable get() = tokens.interfaceDarkDeep
    val brandPrimaryPure @Composable get() = tokens.brandPrimaryPure
    val brandPrimaryUp @Composable get() = tokens.brandPrimaryUp
    val brandPrimaryDown @Composable get() = tokens.brandPrimaryDown
    val brandPrimaryDeep @Composable get() = tokens.brandPrimaryDeep
    val highlightPure @Composable get() = tokens.highlightPure
    val highlightUp @Composable get() = tokens.highlightUp
    val highlightDown @Composable get() = tokens.highlightDown
    val highlightDeep @Composable get() = tokens.highlightDeep
    val complementaryPure @Composable get() = tokens.complementaryPure
    val complementaryUp @Composable get() = tokens.complementaryUp
    val complementaryDown @Composable get() = tokens.complementaryDown
    val complementaryDeep @Composable get() = tokens.complementaryDeep
    val statusPositivePure @Composable get() = tokens.statusPositivePure
    val statusPositiveUp @Composable get() = tokens.statusPositiveUp
    val statusPositiveDown @Composable get() = tokens.statusPositiveDown
    val statusPositiveDeep @Composable get() = tokens.statusPositiveDeep
    val statusNegativeUp @Composable get() = tokens.statusNegativeUp
    val statusNegativeDown @Composable get() = tokens.statusNegativeDown
    val statusNegativeDeep @Composable get() = tokens.statusNegativeDeep
    val statusNegativePure @Composable get() = tokens.statusNegativePure
    val statusWarningPure @Composable get() = tokens.statusWarningPure
    val statusWarningUp @Composable get() = tokens.statusWarningUp
    val statusWarningDown @Composable get() = tokens.statusWarningDown
    val statusWarningDeep @Composable get() = tokens.statusWarningDeep

    @Composable
    fun fromString(color: String) = colorResource(color.toOceanColor())
}
