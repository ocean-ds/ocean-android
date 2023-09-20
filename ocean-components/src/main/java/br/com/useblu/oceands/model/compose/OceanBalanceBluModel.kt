package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable

@Immutable
data class OceanBalanceBluModel(
    val firstLabel: String = "",
    val firstValue: String = "",
    val secondLabel: String = "",
    val secondValue: String = "",
    val thirdLabel: String = "",
    val thirdValue: String = "",
    val buttonDescription: String = "",
    val buttonCta: String = "",
    val onClickButton: () -> Unit = {},
    val onClickExpandScroll: () -> Unit = {}
)
