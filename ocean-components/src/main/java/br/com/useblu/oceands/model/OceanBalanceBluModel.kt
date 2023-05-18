package br.com.useblu.oceands.model

data class OceanBalanceBluModel(
    val firstLabel: String,
    val firstValue: String,
    val secondLabel: String,
    val secondValue: String,
    val thirdLabel: String,
    val thirdValue: String,
    val isContentHidden: Boolean,
    val onClickHideIcon: () -> Unit,
    val buttonDescription: String,
    val buttonCta: String,
    val onClickButton: () -> Unit
)
