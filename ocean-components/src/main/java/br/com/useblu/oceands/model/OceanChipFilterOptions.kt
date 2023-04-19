package br.com.useblu.oceands.model

sealed class OceanChipFilterOptions{
    abstract val title: String
    abstract val optionsItems: List<FilterOptionsItem>

    data class SingleChoice(
        override val title: String,
        override val optionsItems: List<FilterOptionsItem>,
        val onSelectItem: (selectedIndex: Int) -> Unit
    ): OceanChipFilterOptions()

    data class MultipleChoice(
        override val title: String,
        override val optionsItems: List<FilterOptionsItem>,
        val onPrimaryButtonClick: (selectedIndexes: List<Int>) -> Unit,
        val onSecondaryButtonClick: (selectedIndexes: List<Int>) -> Unit,
        val showSelectAllButton: Boolean = false,
        val primaryButtonLabel: String,
        val secondaryButtonLabel: String
    ): OceanChipFilterOptions()
}

data class FilterOptionsItem(
    val title: String,
    var isSelected: Boolean = false,
    val label: String? = null
)
