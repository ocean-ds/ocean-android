package br.com.useblu.oceands.components.compose.cardlistitem.model

import br.com.useblu.oceands.utils.OceanIconType

sealed interface OceanCardListItemType {
    data class Default(
        val leadingIconToken: OceanIconType? = null,
        val trailingIconToken: OceanIconType? = null,
        val showIconBackground: Boolean = true
    ) : OceanCardListItemType

    data class Selectable(
        val selectionType: SelectionType,
        val didUpdate: ((Boolean) -> Unit)
    ) : OceanCardListItemType {
        enum class SelectionType {
            Checkbox,
            Radiobutton
        }
    }
}
