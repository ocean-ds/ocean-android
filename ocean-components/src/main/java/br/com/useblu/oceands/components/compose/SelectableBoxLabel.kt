package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.extensions.compose.htmlToAnnotatedString
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily

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
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable {
                onSelected.invoke()
            },
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = TextStyle(
            color = if (enabled) OceanColors.interfaceDarkDown else OceanColors.interfaceDarkUp,
            fontSize = 16.sp,
            fontFamily = OceanFontFamily.BaseRegular
        ),
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}

@Preview
@Composable
private fun SelectableText() {
    SelectableBoxLabel(
        label = stringResource(id = R.string.link),
        enabled = true,
        onSelected = {

        }
    )
}
