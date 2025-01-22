package br.com.useblu.oceands.model.compose.inlinetextlistitem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.utils.OceanIcons

sealed interface OceanInlineTextListItemDescription {
    data class Default(
        override val icon: OceanIcons? = null,
        override val text: String
    ): OceanInlineTextListItemDescriptionGenericType{
        @Composable
        override fun getTintColor(): Color = OceanColors.interfaceDarkDeep
    }

    data class Innactive(
        override val icon: OceanIcons? = null,
        override val text: String
    ): OceanInlineTextListItemDescriptionGenericType{
        @Composable
        override fun getTintColor(): Color = OceanColors.interfaceDarkUp
    }

    data class Positive(
        override val icon: OceanIcons? = null,
        override val text: String
    ): OceanInlineTextListItemDescriptionGenericType{
        @Composable
        override fun getTintColor(): Color = OceanColors.statusPositiveDeep
    }

    data class Warning(
        override val icon: OceanIcons? = null,
        override val text: String
    ): OceanInlineTextListItemDescriptionGenericType{
        @Composable
        override fun getTintColor(): Color = OceanColors.statusWarningDeep
    }

    data class Highlight(
        override val icon: OceanIcons? = null,
        override val text: String
    ): OceanInlineTextListItemDescriptionGenericType {
        @Composable
        override fun getTintColor(): Color = OceanColors.interfaceDarkDeep
        override val fontWeight: FontWeight = FontWeight.Bold
    }

    data class Strikethrough(
        val oldText: String,
        val currentText: String
    ): OceanInlineTextListItemDescription {
        @Composable
        fun getTintColor(): Color = OceanColors.statusPositiveDeep
    }

    data class Action(
        val title: String,
        val onClick: () -> Unit,
        val buttonStyle: OceanButtonStyle
    ): OceanInlineTextListItemDescription
}

internal interface OceanInlineTextListItemDescriptionGenericType: OceanInlineTextListItemDescription {
    val icon: OceanIcons?
    val text: String
    val fontWeight: FontWeight? get() = null

    @Composable
    fun getTintColor(): Color
}