package br.com.useblu.oceands.ui.stringmask

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        val showCurrencySymbol: Boolean = true,
        val showZeroValue: Boolean = false
    ): OceanInputType {
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

        override fun modifyBeforeOnChange(text: String): String {
            val cleanText = super.modifyBeforeOnChange(text)
                .filter { it.isDigit() }

            if (cleanText.isEmpty() && showZeroValue) {
                return FormatadorValor.VALOR.formata("000")
            }

            val decimalResult = BigDecimal(cleanText)
                .divide(BigDecimal(100))
                .setScale(2, RoundingMode.HALF_DOWN)

            return FormatadorValor.VALOR.formata(decimalResult.toPlainString())
        }

        override fun getVisualTransformation(): VisualTransformation {
            return VisualTransformation { text ->
                TransformedText(text, OffsetMapping.Identity)
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
            return if (isTributo(currentValue))
                BOLETO_TRIBUTO
            else BOLETO_NORMAL
        }

         override fun getVisualTransformation(): VisualTransformation {
             return StaticMaskVisualTransformation(this)
         }

        private fun isTributo(e: String): Boolean {
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

    object Email: OceanInputType {
        override fun getKeyboardType() = KeyboardType.Email
        override fun getVisualTransformation() = VisualTransformation.None
    }

    object Phone: OceanInputType, StaticStringMask {
        private const val PHONE_EIGHT_DIGITS = "(##) ####-####"
        private const val PHONE_NINE_DIGITS = "(##) #####-####"

        override fun getMaxLength() = 11

        override fun getKeyboardType() = KeyboardType.Number

        private fun isNineDigits(e: String): Boolean {
            return Formatador.Padroes.PADRAO_SOMENTE_NUMEROS.matcher(e).replaceAll("").length > 10
        }

        override fun getMask(currentValue: String): String {
            return if (isNineDigits(currentValue)) PHONE_NINE_DIGITS else PHONE_EIGHT_DIGITS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }

    object CPF: OceanInputType, StaticStringMask {
        private const val CPF_DIGITS = "###.###.###-##"

        override fun getMaxLength() = CPF_DIGITS.count { it == '#' }

        override fun getKeyboardType() = KeyboardType.Number

        override fun getMask(currentValue: String): String {
            return CPF_DIGITS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }

    object CNPJ: OceanInputType, StaticStringMask {
        private const val CNPJ_DIGITS = "##.###.###/####-##"

        override fun getMaxLength() = CNPJ_DIGITS.count { it == '#' }

        override fun getKeyboardType() = KeyboardType.Number

        override fun getMask(currentValue: String): String {
            return CNPJ_DIGITS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }

    object CpfCnpj: OceanInputType, StaticStringMask {
        private const val CPF_DIGITS = "###.###.###-##"
        private const val CNPJ_DIGITS = "##.###.###/####-##"

        override fun getMaxLength() = CNPJ_DIGITS.count { it == '#' }

        override fun getKeyboardType() = KeyboardType.Number

        override fun getMask(currentValue: String): String {
            return if (currentValue.length > 11) CNPJ_DIGITS else CPF_DIGITS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }
}

