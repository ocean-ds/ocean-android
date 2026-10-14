package br.com.useblu.oceands.model

import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.utils.OceanIconType

sealed class OceanTextListStyle {
    data object Default : OceanTextListStyle()
    data object RadioButton : OceanTextListStyle()
    data object Checkbox : OceanTextListStyle()
    data class Icon(val icon: OceanIconType? = null, val tint: Color = Color.Unspecified) : OceanTextListStyle()
    data class Image(val imageUrl: String) : OceanTextListStyle()
}
