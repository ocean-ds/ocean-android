package br.com.useblu.oceands.tokens

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

interface SpacingToken {
    @get:Composable
    val xxxs: Dp

    @get:Composable
    val xxs: Dp

    @get:Composable
    val xxsExtra: Dp

    @get:Composable
    val xs: Dp

    @get:Composable
    val sm: Dp

    @get:Composable
    val md: Dp

    @get:Composable
    val lg: Dp

    @get:Composable
    val xl: Dp

    @get:Composable
    val xxl: Dp

    @get:Composable
    val xxxl: Dp
}
