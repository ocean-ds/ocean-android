package br.com.useblu.oceands.components.compose.header.model

sealed interface OceanHeaderStyle {
    data object Minimal : OceanHeaderStyle
    data object Small : OceanHeaderStyle
}
