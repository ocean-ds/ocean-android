package br.com.useblu.oceands.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier

@Immutable
data class OceanTextListIconItemModel(
    val title: String,
    val modifier: Modifier = Modifier,
    val description: String? = null,
    val caption: String? = null,
    val badge: String? = null,
    val leadingIconToken: String? = null,
    val trailingIconToken: String? = null,
    val onClick: (() -> Unit)? = null
)
