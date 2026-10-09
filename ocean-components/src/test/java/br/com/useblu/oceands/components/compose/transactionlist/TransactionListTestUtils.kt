package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.compose.OceanIconModel
import br.com.useblu.oceands.model.compose.OceanTagModel
import br.com.useblu.oceands.utils.OceanIcons

internal fun SemanticsNodeInteraction.textStyle(): TextStyle {
    val results = mutableListOf<TextLayoutResult>()
    fetchSemanticsNode().config.getOrNull(SemanticsActions.GetTextLayoutResult)?.action?.invoke(results)
    return results.first().layoutInput.style
}

internal object Samples {
    val content = ContentListStyle.Inverted(
        title = "Title",
        description = "Description",
        caption = "Caption"
    )

    val amount = ContentListStyle.Amount(
        amount = "R$ 0,00",
        tag = OceanTagModel(type = OceanTagType.Positive, text = "Label"),
        additionalData = "Additional data"
    )

    val icon = OceanIconModel(icon = OceanIcons.PLACEHOLDER_OUTLINE)
}
