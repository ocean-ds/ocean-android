package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.extensions.compose.htmlToAnnotatedString
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize

@Composable
fun SelectableBoxLabel(
    label: String,
    enabled: Boolean,
    onSelected: () -> Unit
) {
    val uriHandler = LocalUriHandler.current
    val annotatedString = label.htmlToAnnotatedString()

    ClickableText(
        text = annotatedString,
        modifier = Modifier.padding(horizontal = 8.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = TextStyle(
            color = if (enabled) OceanColors.interfaceDarkDown
                    else OceanColors.interfaceDarkUp,
            fontSize = OceanFontSize.xxs,
            fontFamily = OceanFontFamily.BaseRegular
        ),
        onClick = {
            val result = annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()

            if(result != null) {
                uriHandler.openUri(result.item)
            } else{
                onSelected()
            }
        }
    )
}
