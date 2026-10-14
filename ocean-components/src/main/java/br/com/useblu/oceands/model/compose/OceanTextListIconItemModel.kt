package br.com.useblu.oceands.model.compose

import androidx.compose.ui.Modifier
import br.com.useblu.oceands.utils.OceanIcons

data class OceanTextListIconItemModel(
    val title: String,
    val modifier: Modifier = Modifier,
    val description: String = "",
    val caption: String = "",
    val badge: String = "",
    val leadingIconToken: OceanIcons? = null,
    val trailingIconToken: OceanIcons? = null,
    val onClick: (() -> Unit)? = null
)
