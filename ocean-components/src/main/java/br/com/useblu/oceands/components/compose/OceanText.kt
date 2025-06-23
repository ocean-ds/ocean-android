package br.com.useblu.oceands.components.compose

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.extensions.compose.htmlToAnnotatedString
import br.com.useblu.oceands.ui.compose.OceanFontSize

@Composable
fun OceanText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        text = text.htmlToAnnotatedString(),
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = style
    )
}

@Composable
fun OceanTextResizable(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    lineHeight: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    style: TextStyle = LocalTextStyle.current,
    minFontSize: TextUnit = OceanFontSize.xxxs
) {
    var currentFontSize by remember { mutableStateOf(style.fontSize) }
    var lastTextLenght by remember { mutableIntStateOf(text.length) }
    var canDraw by remember { mutableStateOf(true) }

    OceanText(
        modifier = modifier.drawWithContent { if (canDraw) drawContent() },
        text = text,
        style = style.copy(fontSize = currentFontSize),
        color = color,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = TextOverflow.Visible,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = { textLayoutResult ->
            when {
                currentFontSize > style.fontSize -> {
                    currentFontSize = style.fontSize
                    lastTextLenght = text.length
                    canDraw = true
                }

                currentFontSize < minFontSize -> {
                    currentFontSize = minFontSize
                    lastTextLenght = text.length
                    canDraw = true
                }

                textLayoutResult.didOverflowWidth -> {
                    currentFontSize = (currentFontSize.value * 0.9f).sp
                    canDraw = false
                }

                else -> {
                    if (text.length < lastTextLenght) {
                        currentFontSize = (currentFontSize.value * 1.15f).sp
                        canDraw = false
                    } else {
                        canDraw = true
                    }
                    lastTextLenght = text.length
                }
            }
        }
    )
}
