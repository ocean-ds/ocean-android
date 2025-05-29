package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.colorResource
import br.com.useblu.oceands.OceanDS
import br.com.useblu.oceands.tokens.oceandefaults.OceanColorToken
import br.com.useblu.oceands.utils.toOceanColor

@Immutable
object OceanColors {
    private val token = OceanDS.colorToken ?: OceanColorToken

    val interfaceLightPure @Composable get() = token.interfaceLightPure
    val interfaceLightUp @Composable get() = token.interfaceLightUp
    val interfaceLightDown @Composable get() = token.interfaceLightDown
    val interfaceLightDeep @Composable get() = token.interfaceLightDeep
    val interfaceDarkPure @Composable get() = token.interfaceDarkPure
    val interfaceDarkUp @Composable get() = token.interfaceDarkUp
    val interfaceDarkDown @Composable get() = token.interfaceDarkDown
    val interfaceDarkDeep @Composable get() = token.interfaceDarkDeep
    val brandPrimaryPure @Composable get() = token.brandPrimaryPure
    val brandPrimaryUp @Composable get() = token.brandPrimaryUp
    val brandPrimaryDown @Composable get() = token.brandPrimaryDown
    val brandPrimaryDeep @Composable get() = token.brandPrimaryDeep
    val highlightPure @Composable get() = token.highlightPure
    val highlightUp @Composable get() = token.highlightUp
    val highlightDown @Composable get() = token.highlightDown
    val highlightDeep @Composable get() = token.highlightDeep
    val complementaryPure @Composable get() = token.complementaryPure
    val complementaryUp @Composable get() = token.complementaryUp
    val complementaryDown @Composable get() = token.complementaryDown
    val complementaryDeep @Composable get() = token.complementaryDeep
    val statusPositivePure @Composable get() = token.statusPositivePure
    val statusPositiveUp @Composable get() = token.statusPositiveUp
    val statusPositiveDown @Composable get() = token.statusPositiveDown
    val statusPositiveDeep @Composable get() = token.statusPositiveDeep
    val statusNegativeUp @Composable get() = token.statusNegativeUp
    val statusNegativeDown @Composable get() = token.statusNegativeDown
    val statusNegativeDeep @Composable get() = token.statusNegativeDeep
    val statusNegativePure @Composable get() = token.statusNegativePure
    val statusWarningPure @Composable get() = token.statusWarningPure
    val statusWarningUp @Composable get() = token.statusWarningUp
    val statusWarningDown @Composable get() = token.statusWarningDown
    val statusWarningDeep @Composable get() = token.statusWarningDeep

    @Composable
    fun fromString(color: String) = colorResource(color.toOceanColor())
}
