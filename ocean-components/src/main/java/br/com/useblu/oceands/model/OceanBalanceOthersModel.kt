package br.com.useblu.oceands.model

data class OceanBalanceOthersModel(
    val firstLabel: String = "",
    val firstValue: String? = null,
    val onClickHideIcon: () -> Unit = {},
    val buttonDescription: String = "",
    val buttonCta: String = "",
    val onClickButton: () -> Unit = {}
)
