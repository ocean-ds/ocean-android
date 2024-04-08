package br.com.useblu.oceands.ui.compose.stringmask

import androidx.annotation.VisibleForTesting
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue

class CurrencyMaskVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        var outputText = ""

        if (text.length < 3) {
            return TransformedText(text, OffsetMapping.Identity)
        }

        var charCount = -1

        text.reversed().forEach { char ->
            if (char == '.') {
                outputText += ','
                charCount = -1
            } else {
                if (charCount % 3 == 2) {
                    outputText += '.'
                }

                outputText += char
                charCount++
            }
        }

        return TransformedText(AnnotatedString(outputText.reversed()), offsetTranslator(text.text, outputText))
    }

    @VisibleForTesting
    fun offsetTranslator(originalText: String, transformedText: String) = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0

            val totalDots = (originalText.length - 4) / 3
            val remainingChars = originalText.length - offsetValue
            val remainingDots = ((remainingChars - 3) / 3).coerceAtLeast(0)

            return offsetValue + (totalDots - remainingDots)
        }

        override fun transformedToOriginal(offset: Int): Int {
            return transformedText.take(offset.absoluteValue).count { it.isDigit() || it == ',' }
        }
    }
}