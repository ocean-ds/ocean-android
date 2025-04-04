package br.com.useblu.oceands.ui.compose.stringmask

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue

class StaticMaskVisualTransformation(
    private val getMask: (String) -> String
) : VisualTransformation {
    private fun specialSymbolsIndices(text: String): List<Int> {
        val mask = getMask(text)
        return mask.indices.filter { mask[it] != '#' }
    }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0

        val mask = getMask(text.text)
        text.forEach { char ->
            while (specialSymbolsIndices(text.text).contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator(text.text))
    }

    private fun offsetTranslator(text: String) = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = getMask(text).takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return getMask(text).take(offset.absoluteValue).count { it == '#' }
        }
    }
}
