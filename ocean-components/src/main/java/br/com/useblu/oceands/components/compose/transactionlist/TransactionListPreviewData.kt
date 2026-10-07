package br.com.useblu.oceands.components.compose.transactionlist

import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.compose.OceanIconModel
import br.com.useblu.oceands.model.compose.OceanTagModel
import br.com.useblu.oceands.utils.OceanIcons

internal object TransactionListPreviewData {
    val content = ContentListStyle.Inverted(
        title = "Title",
        description = "Description",
        caption = "Caption"
    )

    val contentSm = content.copy(size = br.com.useblu.oceands.components.compose.ContentListSize.Sm)

    val amount = ContentListStyle.Amount(
        amount = "R$ 0,00",
        tag = OceanTagModel(type = OceanTagType.Positive, text = "Label"),
        additionalData = "Additional data"
    )

    val amountSm = amount.copy(size = br.com.useblu.oceands.components.compose.ContentListSize.Sm)

    val icon = OceanIconModel(icon = OceanIcons.PLACEHOLDER_OUTLINE)

    val childIcon = OceanIconModel(icon = OceanIcons.PLACEHOLDER_SOLID)
}
