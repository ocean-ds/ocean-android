package br.com.useblu.oceands.ui.compose

import androidx.compose.ui.unit.Dp

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
        get() = when (this) {
            None -> Dp(0f)
            Hairline -> Dp(1f)
            Thin -> Dp(2f)
            Thick -> Dp(4f)
            Heavy -> Dp(8f)
        }
}
