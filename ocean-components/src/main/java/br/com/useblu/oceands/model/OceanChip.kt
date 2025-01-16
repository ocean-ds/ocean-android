package br.com.useblu.oceands.model

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.components.OceanBottomSheetCompose
import br.com.useblu.oceands.components.daterangefilter.OceanDateRangeSelectFilterSheet
import br.com.useblu.oceands.extensions.getSupportFragmentManager
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


sealed interface OceanFilterChipBottomSheet {
    data class FilterOptions(val options: OceanChipFilterOptions): OceanFilterChipBottomSheet {
        override fun showBottomSheet(context: Context) {
            options.showBottomSheet(context = context)
        }
    }
    data class DateRange(
        val title: String,
        val onResult: (from: String, to: String) -> Unit,
        val currentFrom: String,
        val currentTo: String
    ): OceanFilterChipBottomSheet {
        override fun showBottomSheet(context: Context) {
            OceanDateRangeSelectFilterSheet(
                currentBeginDate = currentFrom,
                currentEndDate = currentTo,
                context = context
            ).showBottomSheet(onResult = onResult)
        }
    }
    data class Custom(val view: @Composable () -> Unit): OceanFilterChipBottomSheet {
        override fun showBottomSheet(context: Context) {
            OceanBottomSheetCompose()
                .withComposeContent { view() }
                .show(context.getSupportFragmentManager(),null)
        }
    }

    fun showBottomSheet(context: Context)
}