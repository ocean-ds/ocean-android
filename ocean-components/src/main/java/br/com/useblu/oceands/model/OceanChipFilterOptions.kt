package br.com.useblu.oceands.model

sealed class OceanChipFilterOptions(
    val title: String,
    val items: List<FilterOptionsItem>
) {
    data class SingleChoice(
        private val _title: String,
        private val optionsItems: List<FilterOptionsItem>,
        val onSelectItem: (selectedIndex: Int) -> Unit
    ): OceanChipFilterOptions(_title, optionsItems)

    data class MultipleChoice(
        private val optionsItems: List<FilterOptionsItem>,
        val onPrimaryButtonClick: (selectedIndexes: List<Int>) -> Unit,
        val column: Int = 1,
        private val _title: String,
        val showSelectAllButton: Boolean = false,
        val primaryButtonLabel: String,
        val secondaryButtonLabel: String
    ): OceanChipFilterOptions(_title, optionsItems)
}

data class FilterOptionsItem(
    val title: String,
    var isSelected: Boolean = false,
    val label: String? = null
)
