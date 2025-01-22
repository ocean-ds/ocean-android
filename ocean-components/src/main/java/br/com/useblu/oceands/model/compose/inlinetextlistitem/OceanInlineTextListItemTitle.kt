package br.com.useblu.oceands.model.compose.inlinetextlistitem

import androidx.compose.runtime.Composable
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.utils.OceanIcons

sealed interface OceanInlineTextListItemTitle {
    data class Default(val title: String): OceanInlineTextListItemTitle
    data class WithTag(val title: String, val tagStyle: OceanTagStyle): OceanInlineTextListItemTitle
    data class Custom(val title: String, val content: @Composable () -> Unit): OceanInlineTextListItemTitle
}