package br.com.useblu.oceands.client.ui.transactionlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.AmountType
import br.com.useblu.oceands.components.compose.ContentListSize
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.compose.OceanIconModel
import br.com.useblu.oceands.model.compose.OceanTagModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

internal object TransactionListSamples {
    fun content(size: ContentListSize? = null) = ContentListStyle.Inverted(
        title = "Title",
        description = "Description",
        caption = "Caption",
        size = size
    )

    fun amount(
        size: ContentListSize? = null,
        type: AmountType = AmountType.Default,
        amount: String = "R$ 0,00",
        strikethrough: String = ""
    ) = ContentListStyle.Amount(
        amount = amount,
        type = type,
        size = size,
        strikethroughAmount = strikethrough,
        tag = OceanTagModel(type = OceanTagType.Positive, text = "Label"),
        additionalData = "Additional data"
    )

    /** Long texts: content up to 2 lines + ellipsis, one-line Tag with ellipsis, full value. */
    fun longContent(size: ContentListSize? = null) = ContentListStyle.Inverted(
        title = "Bank transfer",
        description = "Seashell Corporation Wholesale and Distribution Ltda",
        caption = "Order #7182, invoice 4821, scheduled for Oct 15",
        size = size
    )

    fun longAmount(size: ContentListSize? = null) = ContentListStyle.Amount(
        amount = "R$ 1.314,28",
        size = size,
        tag = OceanTagModel(type = OceanTagType.Warning, text = "Payment scheduled for Oct 15 by bank transfer"),
        additionalData = "Transfer to Seashell Corporation, account 4821"
    )

    val icon = OceanIconModel(icon = OceanIcons.PLACEHOLDER_OUTLINE)
    val childIcon = OceanIconModel(icon = OceanIcons.PLACEHOLDER_SOLID)
}

@Composable
internal fun SamplesScreen(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OceanColors.interfaceLightPure)
            .verticalScroll(rememberScrollState()),
        content = content
    )
}

@Composable
internal fun SampleSection(title: String) {
    OceanText(
        modifier = Modifier.padding(
            start = OceanSpacing.xs,
            end = OceanSpacing.xs,
            top = OceanSpacing.md,
            bottom = OceanSpacing.xxs
        ),
        text = title,
        style = OceanTextStyle.heading4
    )
}
