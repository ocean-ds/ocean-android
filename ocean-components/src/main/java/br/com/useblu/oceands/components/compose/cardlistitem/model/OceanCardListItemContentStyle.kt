package br.com.useblu.oceands.components.compose.cardlistitem.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import br.com.useblu.oceands.ui.compose.OceanTextStyle

sealed interface OceanCardListItemContentStyle {
    data object Default : OceanCardListItemContentStyle
    data object Inverted : OceanCardListItemContentStyle
    data class Custom(
        val titleStyle: TextStyle,
        val descriptionStyle: TextStyle
    ) : OceanCardListItemContentStyle

    @Composable
    fun OceanCardListItemContentStyle.getTitleStyle(): TextStyle {
        return when (this) {
            is Default ->
                OceanTextStyle.paragraph
            is Inverted ->
                OceanTextStyle.description
            is Custom ->
                titleStyle
        }
    }

    @Composable
    fun OceanCardListItemContentStyle.getDescriptionStyle(): TextStyle {
        return when (this) {
            is Default ->
                OceanTextStyle.description
            is Inverted ->
                OceanTextStyle.paragraph
            is Custom ->
                descriptionStyle
        }
    }
}
