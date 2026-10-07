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
    fun content(size: ContentListSize = ContentListSize.Md) = ContentListStyle.Inverted(
        title = "Title",
        description = "Description",
        caption = "Caption",
        size = size
    )

    fun amount(
        size: ContentListSize = ContentListSize.Md,
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
