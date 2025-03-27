package br.com.useblu.oceands.components.compose.cardlistitem.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemType
import br.com.useblu.oceands.components.compose.input.OceanSelectableBox
import br.com.useblu.oceands.components.compose.input.OceanSelectableRadio
import br.com.useblu.oceands.ui.compose.OceanSpacing

@Composable
internal fun LeadingSelectableCardListItem(
    type: OceanCardListItemType.Selectable,
    isSelected: Boolean,
    disabled: Boolean
) {
    Column(
        modifier = Modifier
            .padding(end = OceanSpacing.xs),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (type.selectionType) {
            OceanCardListItemType.Selectable.SelectionType.Checkbox -> {
                OceanSelectableBox(
                    selected = isSelected,
                    enabled = !disabled,
                    onSelectedBox = type.didUpdate
                )
            }
            OceanCardListItemType.Selectable.SelectionType.Radiobutton -> {
                OceanSelectableRadio(
                    isSelected = isSelected,
                    enabled = !disabled,
                    onSelectedBox = { type.didUpdate(!isSelected) }
                )
            }
        }
    }
}
