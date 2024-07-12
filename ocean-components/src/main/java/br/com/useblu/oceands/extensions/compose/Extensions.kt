package br.com.useblu.oceands.extensions.compose

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.text.HtmlCompat
import br.com.useblu.oceands.ui.compose.OceanColors

@Composable
fun Modifier.iconContainerBackground(
    showBackground: Boolean,
    color: Color = OceanColors.interfaceLightUp
): Modifier {
    return if (showBackground) {
        this.background(
            color = color,
            shape = CircleShape
        )
    } else {
        this
    }
}


fun String.htmlToAnnotatedString(): AnnotatedString {
    val spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
    return spanned.toAnnotatedString()
}

fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    append(this@toAnnotatedString.toString())

    getSpans(0, length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)

        val spanStyle = when (span) {
            is StyleSpan -> {
                when (span.style) {
                    Typeface.BOLD -> SpanStyle(fontWeight = FontWeight.Bold)
                    Typeface.ITALIC -> SpanStyle(fontStyle = FontStyle.Italic)
                    Typeface.BOLD_ITALIC -> SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )

                    else -> SpanStyle()
                }
            }

            is UnderlineSpan -> SpanStyle(textDecoration = TextDecoration.Underline)
            is ForegroundColorSpan -> SpanStyle(color = Color(span.foregroundColor))
            is URLSpan -> {
                addStringAnnotation(tag = "URL", annotation = span.url, start, end)
                SpanStyle(
                    color = Color(0xFF0025E0),
                    textDecoration = TextDecoration.Underline
                )
            }

            else -> SpanStyle()
        }
        addStyle(spanStyle, start, end)
    }
}
