package br.com.useblu.oceands.tokens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

interface ColorToken {
    @get:Composable
    val interfaceLightPure: Color

    @get:Composable
    val interfaceLightUp: Color

    @get:Composable
    val interfaceLightDown: Color

    @get:Composable
    val interfaceLightDeep: Color

    @get:Composable
    val interfaceDarkPure: Color

    @get:Composable
    val interfaceDarkUp: Color

    @get:Composable
    val interfaceDarkDown: Color

    @get:Composable
    val interfaceDarkDeep: Color

    @get:Composable
    val brandPrimaryPure: Color

    @get:Composable
    val brandPrimaryUp: Color

    @get:Composable
    val brandPrimaryDown: Color

    @get:Composable
    val brandPrimaryDeep: Color

    @get:Composable
    val highlightPure: Color

    @get:Composable
    val highlightUp: Color

    @get:Composable
    val highlightDown: Color

    @get:Composable
    val highlightDeep: Color

    @get:Composable
    val complementaryPure: Color

    @get:Composable
    val complementaryUp: Color

    @get:Composable
    val complementaryDown: Color

    @get:Composable
    val complementaryDeep: Color

    @get:Composable
    val statusPositivePure: Color

    @get:Composable
    val statusPositiveUp: Color

    @get:Composable
    val statusPositiveDown: Color

    @get:Composable
    val statusPositiveDeep: Color

    @get:Composable
    val statusNegativeUp: Color

    @get:Composable
    val statusNegativeDown: Color

    @get:Composable
    val statusNegativeDeep: Color

    @get:Composable
    val statusNegativePure: Color

    @get:Composable
    val statusWarningPure: Color

    @get:Composable
    val statusWarningUp: Color

    @get:Composable
    val statusWarningDown: Color

    @get:Composable
    val statusWarningDeep: Color

    @Composable
    fun fromString(color: String): Color
}
