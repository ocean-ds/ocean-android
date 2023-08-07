package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.model.compose.OceanUnorderedListItemModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanTextStyle


@Preview(showBackground = true)
@Composable
fun OceanUnorderedListItemPreview() {
    val models = listOf(
        OceanUnorderedListItemModel(
            title = "Mais segurança: aprove, na hora, suas transações feitas pelo app",
            iconToken = "shieldcheckoutline",
            showIconBackground = false
        ),
        OceanUnorderedListItemModel(
            title = "Mais segurança: aprove, na hora, suas transações feitas pelo app",
            iconToken = "shieldcheckoutline",
            showIconBackground = false
        ),
        OceanUnorderedListItemModel(
            title = "Mais segurança: aprove, na hora, suas transações feitas pelo app",
            iconToken = "shieldcheckoutline",
            showIconBackground = false
        )
    )

    OceanUnorderedList(models)
}

@Composable
fun OceanUnorderedList(
    models: List<OceanUnorderedListItemModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(models) {item ->
            OceanUnorderedListItem(item)
        }
    }
}

@Composable
fun OceanUnorderedListItem(
    model: OceanUnorderedListItemModel
) {
    OceanUnorderedListItem(
        title = model.title,
        iconToken = model.iconToken,
        showIconBackground = model.showIconBackground
    )
}

@Composable
fun OceanUnorderedListItem(
    title: String,
    iconToken: String,
    showIconBackground: Boolean = false
) {
    Row(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 4.dp
        )
    ) {
        val iconSize = if (showIconBackground) 16.dp else 24.dp

        Box(
            modifier = Modifier
                .size(24.dp)
                .iconContainerBackground(showIconBackground)
        ) {
            OceanIcon(
                token = iconToken,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(iconSize),
                tint = OceanColors.brandPrimaryDown
            )
        }

        Text(
            text = title,
            style = OceanTextStyle.description,
            modifier = Modifier
                .align(Alignment.Top)
                .padding(start = 8.dp)
        )
    }
}
