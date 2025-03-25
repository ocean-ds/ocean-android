package br.com.useblu.oceands.model.compose.inlinetextlistitem

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import br.com.useblu.oceands.ui.compose.OceanTextStyle

enum class OceanInlineTextListItemSize {
    DEFAULT,
    SMALL;

    @Composable
    fun getTitleStyle(): TextStyle {
        return when (this) {
            DEFAULT -> OceanTextStyle.description
            SMALL -> OceanTextStyle.captionBold
        }
    }

    @Composable
    fun getDescriptionStyle(): TextStyle {
        return when (this) {
            DEFAULT -> OceanTextStyle.paragraph
            SMALL -> OceanTextStyle.description
        }
    }
}
