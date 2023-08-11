package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.model.OceanIconType

@Immutable
data class OceanTextListIconItemModel(
    val title: String,
    val modifier: Modifier = Modifier,
    val description: String? = null,
    val caption: String? = null,
    val badge: String? = null,
    val leadingIconToken: OceanIconType? = null,
    val trailingIconToken: OceanIconType? = null,
    val onClick: (() -> Unit)? = null
)
