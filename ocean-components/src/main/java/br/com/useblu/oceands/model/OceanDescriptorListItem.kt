package br.com.useblu.oceands.model

data class OceanDescriptorListItem(
    val title: String? = null,
    val value: String? = null,
    val newValue: String? = null,
    val color: String? = null,
    val icon: String? = null,
    val isBold: Boolean? = false,
    val isDivider: Boolean? = false,
    val isStrike: Boolean? = !newValue.isNullOrBlank()
)