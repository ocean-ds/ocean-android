package br.com.useblu.oceands.extensions

import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.extensions.compose.toHexString
import java.text.SimpleDateFormat
import java.util.Locale

fun String.isValidDate(pattern: String): Boolean {
    val format = SimpleDateFormat(
        pattern,
        Locale.getDefault()
    ).apply {
        isLenient = false
    }

    return runCatching { format.parse(this) }.isSuccess
}

fun String.toDate(pattern: String = "dd/MM/yyyy") = runCatching {
    SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
}.getOrNull()

fun String.createColoredHtmlTag(
    color: Color,
    tag: String = "span"
): String {
    val hexColor = color.toHexString()
    return "<$tag style=\"color: $hexColor\">$this</$tag>"
}
