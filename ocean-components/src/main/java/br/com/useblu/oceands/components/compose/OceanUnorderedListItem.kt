package br.com.useblu.oceands.components.compose

import android.widget.TextView
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.model.compose.OceanUnorderedListItemModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons


@Preview(showBackground = true)
@Composable
fun OceanUnorderedListItemPreview() {
    val models = listOf(
        OceanUnorderedListItemModel(
            title = "Mais segurança: aprove, na hora, suas transações feitas pelo app",
            iconType = OceanIcons.SHIELD_CHECK_OUTLINE,
            showIconBackground = false
        ),
        OceanUnorderedListItemModel(
            title = "Mais segurança: aprove, na hora, suas transações feitas pelo app",
            iconType = OceanIcons.SHIELD_CHECK_OUTLINE,
            showIconBackground = false
        ),
        OceanUnorderedListItemModel(
            title = "Mais segurança: aprove, na hora, suas transações feitas pelo app",
            style = OceanTextStyle.heading5,
            iconType = OceanIcons.SHIELD_CHECK_OUTLINE,
            showIconBackground = false
        ),
        OceanUnorderedListItemModel(
            title = "<b>Mais segurança:</b> aprove, na hora, suas transações feitas pelo app",
            iconType = OceanIcons.SHIELD_CHECK_OUTLINE,
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
        iconType = model.iconType,
        style = model.style ?: OceanTextStyle.description,
        showIconBackground = model.showIconBackground
    )
}

@Composable
fun OceanUnorderedListItem(
    title: String,
    iconType: OceanIcons,
    style: TextStyle = OceanTextStyle.description,
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
                iconType = iconType,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(iconSize),
                tint = OceanColors.brandPrimaryDown
            )
        }

        if(!isHtmlString(title)){
            Text(
                text = title,
                style = style,
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(start = 8.dp)
            )
        } else {
            HtmlText(
                html = title,
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun isHtmlString(input: String): Boolean {
    // Check if the string contains HTML tags
    return input.contains(Regex("<[^>]*>"))
}

@Composable
fun HtmlText(
    modifier: Modifier = Modifier,
    html: String,
) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}
