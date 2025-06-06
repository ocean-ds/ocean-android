package br.com.useblu.oceands.model.compose.checkbox

sealed interface OceanCheckBoxTextStyle {
    val isBold: Boolean
    val isSelectable: Boolean
        get() = false

    data class Regular(
        override val isBold: Boolean = false
    ) : OceanCheckBoxTextStyle

    data class Selectable(
        override val isBold: Boolean = false
    ) : OceanCheckBoxTextStyle {
        override val isSelectable: Boolean = true
    }
}
