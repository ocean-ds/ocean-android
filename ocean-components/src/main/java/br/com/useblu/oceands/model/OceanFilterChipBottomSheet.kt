package br.com.useblu.oceands.model

import android.content.Context
import br.com.useblu.oceands.components.OceanBottomSheetCompose
import br.com.useblu.oceands.components.daterangefilter.OceanDateRangeSelectFilterSheet
import br.com.useblu.oceands.extensions.getSupportFragmentManager
import java.util.Calendar

sealed interface OceanFilterChipBottomSheet {
    data class FilterOptions(val options: OceanChipFilterOptions) : OceanFilterChipBottomSheet {
        override fun showBottomSheet(context: Context) {
            options.showBottomSheet(context = context)
        }
    }

    data class DateRange(
        val title: String,
        val onResult: (from: String, to: String) -> Unit,
        val currentBeginDate: String,
        val currentEndDate: String,
        val maxDate: Calendar? = null
    ) : OceanFilterChipBottomSheet {
        override fun showBottomSheet(context: Context) {
            OceanDateRangeSelectFilterSheet(
                currentBeginDate = currentBeginDate,
                currentEndDate = currentEndDate,
                maxDate = maxDate,
                context = context
            ).showBottomSheet(onResult = onResult)
        }
    }

    data class Custom(val bottomSheetCompose: OceanBottomSheetCompose) : OceanFilterChipBottomSheet {
        override fun showBottomSheet(context: Context) {
            bottomSheetCompose.show(context.getSupportFragmentManager(), null)
        }
    }

    fun showBottomSheet(context: Context)
}
