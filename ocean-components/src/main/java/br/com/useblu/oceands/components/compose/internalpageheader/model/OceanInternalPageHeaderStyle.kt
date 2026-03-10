package br.com.useblu.oceands.components.compose.internalpageheader.model

import androidx.annotation.IntRange

sealed interface OceanInternalPageHeaderStyle {
    data object Default : OceanInternalPageHeaderStyle

    data class WithSteps(
        @IntRange(from = 1) val currentStep: Int,
        val stepCount: Int
    ) : OceanInternalPageHeaderStyle
}
