package br.com.useblu.oceands.model.compose.carouselwithcomponents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
data class OceanCarouselComponentItem(
    val component: @Composable (() -> Unit),
    val action: () -> Unit = {}
) {}