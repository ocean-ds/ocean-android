package br.com.useblu.oceands.model

sealed class OceanChip {
    abstract val id: String
    abstract val label: String
    abstract var state: OceanChipItemState
    abstract val badge: Badge?
}

data class OceanBasicChip(
    override val id: String,
    override val label: String,
    override val badge: Badge? = null,
    val icon: String? = null,
    override var state: OceanChipItemState = OceanChipItemState.INACTIVE_HOVER,
    var onClick: (selected: Boolean) -> Unit,
    val hasFilterAll: Boolean = false,
): OceanChip()

data class OceanFilterChip(
    override val id: String,
    override val label: String,
    override val badge: Badge? = null,
    override var state: OceanChipItemState = OceanChipItemState.INACTIVE_HOVER,
    val filterOptions: OceanChipFilterOptions
): OceanChip()
