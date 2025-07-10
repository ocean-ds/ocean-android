package br.com.useblu.oceands.ui.compose.stringmask

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import br.com.useblu.oceands.utils.canarinho.formatador.FormatadorValor

class PercentageMaskVisualTransformation : NumberVisualTransformation() {
    override fun filter(text: AnnotatedString): TransformedText {
        if (text.length < 3) {
            return TransformedText(text, OffsetMapping.Identity)
        }

        val formattedText = FormatadorValor.VALOR.formata(text.text) + " %"
        return TransformedText(AnnotatedString(formattedText), offsetTranslator(text.text, formattedText))
    }
}
