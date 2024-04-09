package br.com.useblu.oceands.ui.compose.stringmask

import androidx.annotation.VisibleForTesting
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue

class CurrencyMaskVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        if (text.length < 3) {
            return TransformedText(text, OffsetMapping.Identity)
        }

        val chunks = text.reversed().chunked(3)
        val decimalChunk = chunks.first().replace('.', ',')
        val remaingChunks = chunks.drop(1).joinToString(".")
        val formattedText = (decimalChunk + remaingChunks).reversed()

        return TransformedText(AnnotatedString(formattedText), offsetTranslator(text.text, formattedText))
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