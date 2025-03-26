package br.com.useblu.oceands.components.compose.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

@Preview(showBackground = true)
@Composable
private fun OceanOrderedListItemPreview() {
    val models = listOf(
        "Mais segurança: aprove, na hora, suas transações feitas pelo app",
        "<b>Mais segurança:</b> aprove, na hora, suas transações feitas pelo app"
    )

    OceanOrderedList(models)
}

@Composable
fun OceanOrderedList(
    items: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items) { index, item ->
            OceanOrderedListItem(item, index + 1)
        }
    }
}

@Composable
fun OceanOrderedListItem(
    title: String,
    number: Int
) {
    Row(
        modifier = Modifier.padding(
            vertical = OceanSpacing.xxxs
        ),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .iconContainerBackground(true)
        ) {
            OceanText(
                text = number.toString(),
                fontFamily = OceanFontFamily.BaseMedium,
                modifier = Modifier
                    .align(Alignment.Center),
                color = OceanColors.brandPrimaryDown
            )
        }

        OceanSpacing.StackXXS()

        OceanText(
            text = title,
            style = OceanTextStyle.description
        )
    }
}
