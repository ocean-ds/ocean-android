package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import br.com.useblu.oceands.R

sealed interface OceanOpacity {
    /** 0.8f */
    data object Semiopaque : OceanOpacity

    /** 0.64f */
    data object Intense : OceanOpacity

    /** 0.4f */
    data object Medium : OceanOpacity

    /** 0.24f */
    data object Soft : OceanOpacity

    /** 0.16f */
    data object Light : OceanOpacity

    /** 0.08f */
    data object Semitransparent : OceanOpacity

    val value: Float
        @Composable get() = ResourcesCompat.getFloat(
            LocalContext.current.resources,
            when (this) {
                Semiopaque -> R.dimen.ocean_opacity_level_semiopaque
                Intense -> R.dimen.ocean_opacity_level_intense
                Medium -> R.dimen.ocean_opacity_level_medium
                Soft -> R.dimen.ocean_opacity_level_soft
                Light -> R.dimen.ocean_opacity_level_light
                Semitransparent -> R.dimen.ocean_opacity_level_semitransparent
            }
        )
}
