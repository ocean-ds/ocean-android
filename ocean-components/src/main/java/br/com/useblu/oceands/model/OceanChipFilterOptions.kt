package br.com.useblu.oceands.model

sealed class OceanChipFilterOptions{
    abstract val items: List<FilterOptionsItem>
}


data class SingleChoice(
    override val items: List<FilterOptionsItem>
): OceanChipFilterOptions()


data class MultipleChoice(
    override val items: List<FilterOptionsItem>,
    val column: Int = 1,
    val title: String? = null,
    val showSelectAllButton: Boolean = false,
    val primaryButtonLabel: String,
    val secondaryButtonLabel: String
): OceanChipFilterOptions()

data class FilterOptionsItem(
    val title: String,
    var isChecked: Boolean = false
)
