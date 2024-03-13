package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextLayoutResult
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
    onSelected: (Boolean) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    var layoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
    var selected by remember { mutableStateOf(false) }

    val annotatedString = label.htmlToAnnotatedString()

    Text(
        text = annotatedString,
        modifier = Modifier
            .padding(horizontal = 8.dp)
//            .pointerInput(Unit) {
//                detectTapGestures { offsetPosition ->
////                    val result = annotatedString
////                        .getStringAnnotations(0, label.length)
////                        .firstOrNull() ?: return@detectTapGestures
////
////                    if (result.tag == "URL") {
////                        uriHandler.openUri(result.item)
////                    } else {
////                        selected = !selected
////                        onSelected(selected)
////                    }
//
//                    layoutResult?.let {
//                        val position = it.getOffsetForPosition(offsetPosition)
//                        val result = annotatedString
//                            .getStringAnnotations(position, position)
//                            .firstOrNull()
//                            ?: return@detectTapGestures
//
//                        if (result.tag == "URL") {
//                            uriHandler.openUri(result.item)
//                        }
//                    }
//                }
//            },
                ,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontSize = OceanFontSize.xxs,
        color = if (enabled) OceanColors.interfaceDarkDown
        else OceanColors.interfaceDarkUp,
        fontFamily = OceanFontFamily.BaseRegular,
//        onTextLayout = { layoutResult = it }
    )
}
