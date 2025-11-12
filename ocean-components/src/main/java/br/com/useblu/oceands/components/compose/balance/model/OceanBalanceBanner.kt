package br.com.useblu.oceands.components.compose.balance.model

import androidx.compose.runtime.Composable

data class OceanBalanceBanner(
    val position: OceanBalanceBannerPosition,
    val content: (@Composable () -> Unit)
)
