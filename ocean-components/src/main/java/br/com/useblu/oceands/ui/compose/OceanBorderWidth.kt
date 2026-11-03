package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import br.com.useblu.oceands.R

sealed interface OceanBorderWidth {
    /** 0dp */
    data object None : OceanBorderWidth

    /** 1dp */
    data object Hairline : OceanBorderWidth

    /** 2dp */
    data object Thin : OceanBorderWidth

    /** 4dp */
    data object Thick : OceanBorderWidth

    /** 8dp */
    data object Heavy : OceanBorderWidth

    val value: Dp
        @Composable get() = dimensionResource(
            id = when (this) {
                None -> R.dimen.ocean_border_width_none
                Hairline -> R.dimen.ocean_border_width_hairline
                Thin -> R.dimen.ocean_border_width_thin
                Thick -> R.dimen.ocean_border_width_thick
                Heavy -> R.dimen.ocean_border_width_heavy
            }
        )
}
