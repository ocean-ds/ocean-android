package br.com.useblu.oceands.model

import android.content.Context
import br.com.useblu.oceands.adapter.OceanFilterChipMultipleOptionsAdapter
import br.com.useblu.oceands.adapter.OceanFilterChipSingleOptionsAdapter
import br.com.useblu.oceands.components.OceanOptionsBottomListSheet

sealed class OceanChipFilterOptions {
    abstract val title: String
    abstract val optionsItems: List<FilterOptionsItem>

    private fun List<FilterOptionsItem>.getSelectedIndexes(): List<Int> {
        return mapIndexed { index, filterOptionsItem ->
            if (filterOptionsItem.isSelected) {
                index
            } else -1
        }.filter { it != -1 }
    }

    internal fun showBottomSheet(context: Context) {
        val internalItems = optionsItems.map { it.copy() }

        val bottomSheet = OceanOptionsBottomListSheet(context)
            .withTitle(title)

        when (this) {
            is MultipleChoice -> {
                val adapter = OceanFilterChipMultipleOptionsAdapter(internalItems)
                bottomSheet.withCustomList(adapter)

                bottomSheet.withFooterButton(
                    primaryButtonLabel,
                    secondaryButtonLabel,
                    primaryAction = {
                        val selectedItems = internalItems.getSelectedIndexes()
                        onPrimaryButtonClick(selectedItems)
                    },
                    secondaryAction = {
                        val selectedItems = internalItems.getSelectedIndexes()
                        onSecondaryButtonClick(selectedItems)
                    }
                )
            }

            is SingleChoice -> {
                val adapter = OceanFilterChipSingleOptionsAdapter(this) {
                    onSelectItem(it)
                    bottomSheet.dismiss()
                }
                bottomSheet.withCustomList(adapter)
            }
        }

        bottomSheet.show()
    }

    data class SingleChoice(
        override val title: String,
        override val optionsItems: List<FilterOptionsItem>,
        val onSelectItem: (selectedIndex: Int) -> Unit
    ) : OceanChipFilterOptions()

    data class MultipleChoice(
        override val title: String,
        override val optionsItems: List<FilterOptionsItem>,
        val onPrimaryButtonClick: (selectedIndexes: List<Int>) -> Unit,
        val onSecondaryButtonClick: (selectedIndexes: List<Int>) -> Unit,
        val showSelectAllButton: Boolean = false,
        val primaryButtonLabel: String,
        val secondaryButtonLabel: String
    ) : OceanChipFilterOptions()
}

data class FilterOptionsItem(
    val title: String,
    var isSelected: Boolean = false,
    val label: String? = null
)
