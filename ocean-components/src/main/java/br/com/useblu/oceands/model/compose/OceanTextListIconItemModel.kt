package br.com.useblu.oceands.model.compose

import androidx.compose.ui.Modifier
import br.com.useblu.oceands.utils.OceanIconType

data class OceanTextListIconItemModel(
    val title: String,
    val modifier: Modifier = Modifier,
    val description: String = "",
    val caption: String = "",
    val badge: String = "",
    val leadingIconToken: OceanIconType? = null,
    val trailingIconToken: OceanIconType? = null,
    val onClick: (() -> Unit)? = null
)
