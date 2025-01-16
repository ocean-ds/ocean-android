package br.com.useblu.oceands.model

import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.utils.OceanIcons

@Immutable
sealed class OceanChip {
    abstract val id: String
    abstract val label: String
    abstract var state: OceanChipItemState
    abstract val badge: Badge?
}

@Immutable
data class OceanBasicChip(
    override val id: String,
    override val label: String,
    override val badge: Badge? = null,
    val icon: OceanIcons? = null,
    override var state: OceanChipItemState = OceanChipItemState.HOVER_INACTIVE,
    val onClick: (selected: Boolean) -> Unit,
    val hasFilterAll: Boolean = false,
): OceanChip()

@Immutable
data class OceanFilterChip(
    override val id: String,
    override val label: String,
    override val badge: Badge? = null,
    override var state: OceanChipItemState = OceanChipItemState.HOVER_INACTIVE,
    val bottomSheet: OceanFilterChipBottomSheet
): OceanChip() {
    constructor(
        id: String,
        label: String,
        badge: Badge? = null,
        state: OceanChipItemState = OceanChipItemState.HOVER_INACTIVE,
        filterOptions: OceanChipFilterOptions
    ) : this(
        id = id,
        label = label,
        badge = badge,
        state = state,
        bottomSheet = OceanFilterChipBottomSheet.FilterOptions(options = filterOptions)
    )
}