package br.com.useblu.oceands.model

data class OceanBalanceOthersModel(
    val title: String = "",
    val description: String = "",
    val buttonCta: String = "",
    val buttonCtaCollapsed: String = "",
    val onClickButton: () -> Unit = {}
)