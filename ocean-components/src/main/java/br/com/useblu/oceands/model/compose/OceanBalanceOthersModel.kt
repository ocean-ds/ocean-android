package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable

@Immutable
data class OceanBalanceOthersModel(
    val title: String = "",
    val description: String = "",
    val buttonCta: String = "",
    val buttonCtaCollapsed: String = "",
    val onClickButton: () -> Unit = {}
)
