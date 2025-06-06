package br.com.useblu.oceands.model.compose.checkbox

data class OceanCheckboxLabelItem<ID>(
    val id: ID,
    val label: String,
    val style: OceanCheckBoxTextStyle = OceanCheckBoxTextStyle.Regular(isBold = false)
)
