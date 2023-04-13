package br.com.useblu.oceands.model

sealed class OceanChip {
    abstract val id: String
    abstract val label: String
    abstract var state: OceanChipItemState
}

data class OceanBasicChip(
    override val label: String,
    override val id: String,
    val badge: Badge? = null,
    val icon: String? = null,
    override var state: OceanChipItemState = OceanChipItemState.DEFAULT,
    var onClick: () -> Unit = {}
): OceanChip()

data class OceanFilterChip(
    override val label: String,
    override val id: String,
    override var state: OceanChipItemState = OceanChipItemState.DEFAULT,
    val filterOptions: OceanChipFilterOptions,
    var isOpen: Boolean = false
): OceanChip()