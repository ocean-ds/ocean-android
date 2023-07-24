package br.com.useblu.oceands.ui.stringmask

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.concrete.canarinho.formatador.Formatador
import br.com.concrete.canarinho.formatador.FormatadorValor
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.absoluteValue


sealed interface OceanInputType {
    interface StaticStringMask {
        fun getMask(currentValue: String): String
    }

    fun getMaxLength(): Int? = null

    fun getKeyboardType(): KeyboardType {
        return KeyboardType.Text
    }

    fun modifyBeforeOnChange(text: String): String {
        return getMaxLength()?.let { text.take(it) } ?: text
    }

    fun getPrefixComposable(): @Composable (() -> Unit)? = null

    fun getVisualTransformation(): VisualTransformation

    object DEFAULT: OceanInputType {
        override fun getVisualTransformation() = VisualTransformation.None
    }

    data class Currency(
        val showCurrencySymbol: Boolean = true
    ): OceanInputType {
        // TODO: fix bug when input already has value
        override fun getKeyboardType() = KeyboardType.Number

        override fun getPrefixComposable(): @Composable (() -> Unit)? {
            if (!showCurrencySymbol) return null

            return {
                Text(
                    text = "R$",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = OceanColors.interfaceDarkDeep,
                        fontFamily = OceanFontFamily.BaseRegular
                    ),
                    modifier = Modifier.padding(end = 2.dp)
                )
            }
        }
        override fun getVisualTransformation(): VisualTransformation {
            return VisualTransformation { text ->
                val out = FormatadorValor.VALOR.formata(text.text.ifEmpty { "000" })

                TransformedText(
                    AnnotatedString(out),
                    offsetTranslator(text.text, out)
                )
            }
        }

        override fun modifyBeforeOnChange(text: String): String {
            val cleanText = super.modifyBeforeOnChange(text)
                .filter { it.isDigit() }

            return BigDecimal(cleanText)
                .divide(BigDecimal(100))
                .setScale(2, RoundingMode.HALF_DOWN)
                .toPlainString()
        }

        private fun offsetTranslator(originalString: String, formattedString: String) = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val offsetValue = offset.absoluteValue
                if (offsetValue == 0) return 0

                var numberOfNonDigits = 0
                var originalStringIndex = 0

                while (originalStringIndex < offsetValue) {
                    if (!formattedString[originalStringIndex + numberOfNonDigits].isDigit()) {
                        numberOfNonDigits++
                    }
                    originalStringIndex++
                }

                return originalStringIndex + numberOfNonDigits
            }

            override fun transformedToOriginal(offset: Int): Int {
                return formattedString.take(offset.absoluteValue).count { it.isDigit() }.coerceAtMost(originalString.length)
            }
        }
    }

     object BankBillet: OceanInputType, StaticStringMask {
        private const val BOLETO_NORMAL = "#####.##### #####.###### #####.###### # ##############"
        private const val BOLETO_TRIBUTO = "############ ############ ############ ############"

         override fun getKeyboardType() = KeyboardType.Number

         override fun getMaxLength(): Int {
             return 48
         }

        override fun getMask(currentValue: String): String {
            return if (ehTributo(currentValue))
                BOLETO_TRIBUTO
            else BOLETO_NORMAL
        }

         override fun getVisualTransformation(): VisualTransformation {
             return StaticMaskVisualTransformation(this)
         }

        private fun ehTributo(e: String): Boolean {
            return e[0] == '8'
        }
    }

    object CEP: OceanInputType, StaticStringMask {
        private const val CEP_DIGITOS = "#####-###"

        override fun getMaxLength() = 8

        override fun getKeyboardType() = KeyboardType.Number

        override fun getMask(currentValue: String): String {
            return CEP_DIGITOS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }

    object Phone: OceanInputType, StaticStringMask {
        private const val TELEFONE_OITO_DIGITOS = "(##) ####-####"
        private const val TELEFONE_NOVE_DIGITOS = "(##) #####-####"

        override fun getMaxLength() = 11

        override fun getKeyboardType() = KeyboardType.Number

        private fun ehNoveDigitos(e: String): Boolean {
            return Formatador.Padroes.PADRAO_SOMENTE_NUMEROS.matcher(e).replaceAll("").length > 10
        }

        override fun getMask(currentValue: String): String {
            return if (ehNoveDigitos(currentValue)) TELEFONE_NOVE_DIGITOS else TELEFONE_OITO_DIGITOS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }

    object CPF: OceanInputType, StaticStringMask {
        private const val CPF_DIGITOS = "###.###.###-##"

        override fun getMaxLength() = CPF_DIGITOS.count { it == '#' }

        override fun getKeyboardType() = KeyboardType.Number

        override fun getMask(currentValue: String): String {
            return CPF_DIGITOS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }

    object CNPJ: OceanInputType, StaticStringMask {
        private const val CNPJ_DIGITOS = "##.###.###/####-##"

        override fun getMaxLength() = CNPJ_DIGITOS.count { it == '#' }

        override fun getKeyboardType() = KeyboardType.Number

        override fun getMask(currentValue: String): String {
            return CNPJ_DIGITOS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }

    object CpfCnpj: OceanInputType, StaticStringMask {
        private const val CPF_DIGITOS = "###.###.###-##"
        private const val CNPJ_DIGITOS = "##.###.###/####-##"

        override fun getMaxLength() = CNPJ_DIGITOS.count { it == '#' }

        override fun getKeyboardType() = KeyboardType.Number

        override fun getMask(currentValue: String): String {
            return if (currentValue.length > 11) CNPJ_DIGITOS else CPF_DIGITOS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }
}

