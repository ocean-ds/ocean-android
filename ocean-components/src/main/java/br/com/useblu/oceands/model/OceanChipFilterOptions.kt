package br.com.useblu.oceands.model

sealed class OceanChipFilterOptions {
    abstract val items: List<FilterOptionsItem>
    abstract val onCloseOptions: (selectedOptions: List<Int>) -> Unit
}


data class SingleChoice(
    override val items: List<FilterOptionsItem>,
    override val onCloseOptions: (selectedOptions: List<Int>) -> Unit
): OceanChipFilterOptions()


data class MultipleChoice(
    override val items: List<FilterOptionsItem>,
    override val onCloseOptions: (selectedOptions: List<Int>) -> Unit,
    val column: Int = 1,
    val title: String? = null,
    val showSelectAllButton: Boolean = false,
    val primaryButtonLabel: String,
    val secondaryButtonLabel: String
): OceanChipFilterOptions()

data class FilterOptionsItem(
    val title: String,
    var isSelected: Boolean = false,
    val label: String? = null
)
