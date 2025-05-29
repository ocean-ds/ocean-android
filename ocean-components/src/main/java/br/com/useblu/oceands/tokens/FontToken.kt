package br.com.useblu.oceands.tokens

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit

interface FontToken {
    @get:Composable
    val xxxs: TextUnit

    @get:Composable
    val xxs: TextUnit

    @get:Composable
    val xs: TextUnit

    @get:Composable
    val sm: TextUnit

    @get:Composable
    val md: TextUnit

    @get:Composable
    val lg: TextUnit

    @get:Composable
    val xl: TextUnit

    @get:Composable
    val xxl: TextUnit

    @get:Composable
    val xxxl: TextUnit

    @get:Composable
    val display: TextUnit

    @get:Composable
    val giant: TextUnit
}
