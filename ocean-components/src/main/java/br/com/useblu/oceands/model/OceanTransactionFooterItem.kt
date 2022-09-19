package br.com.useblu.oceands.model

data class OceanTransactionFooterItem(
    val label: String? = null,
    val tooltip: String? = null,
    val value: String? = null,
    val newValue: String? = null,
    val color: String? = null,
    val icon: String? = null,
    val isBold: Boolean? = false,
    val isStrike: Boolean? = !newValue.isNullOrBlank()
)