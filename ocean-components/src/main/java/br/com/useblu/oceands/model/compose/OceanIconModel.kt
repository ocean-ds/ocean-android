package br.com.useblu.oceands.model.compose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import br.com.useblu.oceands.utils.OceanIconType

data class OceanIconModel(
    val icon: OceanIconType,
    val tint: Color = Color.Unspecified,
    val size: Dp? = null
)
