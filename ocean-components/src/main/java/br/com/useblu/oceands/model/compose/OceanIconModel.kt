package br.com.useblu.oceands.model.compose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import br.com.useblu.oceands.utils.OceanIcons

data class OceanIconModel(
    val icon: OceanIcons,
    val tint: Color = Color.Unspecified,
    val size: Dp? = null
)
