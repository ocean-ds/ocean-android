package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import br.com.useblu.oceands.utils.OceanIconType

@Immutable
data class OceanUnorderedListItemModel(
    val title: String,
    val description: String = "",
    val iconType: OceanIconType,
    val style: TextStyle? = null,
    val showIconBackground: Boolean = false,
    val iconColor: Color? = null,
    val roundBackgroundColor: Color? = null
)
