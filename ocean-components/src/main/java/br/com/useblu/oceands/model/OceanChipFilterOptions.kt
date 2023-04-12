package br.com.useblu.oceands.model

sealed class OceanChipFilterOptions(
    val items: List<FilterOptionsItem>
) {
    data class SingleChoice(
        private val optionsItems: List<FilterOptionsItem>,
        val onSelectItem: (selectedIndex: Int) -> Unit
    ): OceanChipFilterOptions(optionsItems)

    data class MultipleChoice(
        private val optionsItems: List<FilterOptionsItem>,
        val onPrimaryButtonClick: (selectedIndexes: List<Int>) -> Unit,
        val column: Int = 1,
        val title: String? = null,
        val showSelectAllButton: Boolean = false,
        val primaryButtonLabel: String,
        val secondaryButtonLabel: String
    ): OceanChipFilterOptions(optionsItems)
}

data class FilterOptionsItem(
    val title: String,
    var isSelected: Boolean = false,
    val label: String? = null
)
