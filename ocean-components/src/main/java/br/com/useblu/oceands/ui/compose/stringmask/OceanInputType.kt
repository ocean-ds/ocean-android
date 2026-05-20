package br.com.useblu.oceands.ui.compose.stringmask

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.OceanDS
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import java.math.BigDecimal
import java.math.RoundingMode

sealed interface OceanInputType {
    fun getMaxLength(): Int? = null

    fun getKeyboardType() = KeyboardType.Text

    fun transformForInput(text: String) = text
    fun transformForOutput(text: String) = text

    fun alwaysGoToEndOfInput(): Boolean = false

    fun getPrefixComposable(value: String): @Composable (() -> Unit)? = null

    fun getVisualTransformation(): VisualTransformation = VisualTransformation.None

    fun sanitizeWithDigits(text: String): String {
        val digitsText = text.filter { it.isDigit() }
        return getMaxLength()?.let { digitsText.take(it) } ?: digitsText
    }

    /**
     * Keep letters `[A-Z]` and digits `[0-9]` (upper-cases letters), drop everything else.
     * Used by inputs that must support alphanumeric content — notably CNPJ after
     * Receita Federal NT 49/2024.
     */
    fun sanitizeWithAlphanumeric(text: String): String {
        val alphanumeric = text.uppercase().filter { it.isDigit() || it in 'A'..'Z' }
        return getMaxLength()?.let { alphanumeric.take(it) } ?: alphanumeric
    }

    fun usePhysicalKeyboardOnly(): Boolean {
        return OceanDS.disabledKeyboards.contains(getKeyboardType())
    }

    data object DEFAULT : OceanInputType

    data object Number : OceanInputType {
        override fun getKeyboardType() = KeyboardType.Number
    }

    data class Currency(
        val showCurrencySymbol: Boolean = true,
        val showZeroValue: Boolean = false
    ) : OceanInputType {
        override fun getKeyboardType() = KeyboardType.Number

        override fun getPrefixComposable(value: String): @Composable (() -> Unit)? {
            if (!showCurrencySymbol || (value.isBlank() && !showZeroValue)) return null

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

        override fun alwaysGoToEndOfInput() = true

        private fun sanitizeText(text: String): String {
            val digitsString = text.filter { it.isDigit() }

            if (digitsString.isEmpty() || digitsString.all { it == '0' }) {
                return if (showZeroValue) {
                    "0.00"
                } else ""
            }

            val result = BigDecimal(digitsString)
                .divide(BigDecimal(100), 2, RoundingMode.HALF_DOWN)

            return result.toPlainString()
        }

        override fun transformForInput(text: String) = sanitizeText(text)
        override fun transformForOutput(text: String) = sanitizeText(text)

        override fun getVisualTransformation(): VisualTransformation {
            return CurrencyMaskVisualTransformation()
        }
    }

    data class Percentage(
        val showZeroValue: Boolean = false,
        val maxValue: Double = 100.0
    ) : OceanInputType {
        override fun getKeyboardType() = KeyboardType.Number

        override fun alwaysGoToEndOfInput() = true

        private fun sanitizeText(text: String): String {
            val digitsString = text.filter { it.isDigit() }

            if (digitsString.isEmpty() || digitsString.all { it == '0' }) {
                return if (showZeroValue) {
                    "0.00"
                } else ""
            }

            val result = BigDecimal(digitsString)
                .divide(BigDecimal(100), 2, RoundingMode.HALF_DOWN)
                .coerceAtMost(BigDecimal(maxValue).setScale(2, RoundingMode.HALF_DOWN))

            return result.toPlainString()
        }

        override fun transformForInput(text: String) = sanitizeText(text)
        override fun transformForOutput(text: String) = sanitizeText(text)

        override fun getVisualTransformation(): VisualTransformation {
            return PercentageMaskVisualTransformation()
        }
    }

    data object BankBillet : OceanInputType {
        private const val BOLETO_NORMAL = "#####.##### #####.###### #####.###### # ##############"
        private const val BOLETO_TRIBUTO = "############ ############ ############ ############"

        override fun getKeyboardType() = KeyboardType.Number

        override fun getMaxLength() = 48

        override fun transformForInput(text: String): String {
            return text.take(getMaxLength())
        }

        override fun transformForOutput(text: String): String {
            return text.take(getMaxLength())
        }

        private fun getMask(currentValue: String): String {
            return if (isTributo(currentValue))
                BOLETO_TRIBUTO
            else BOLETO_NORMAL
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(::getMask)
        }

        private fun isTributo(e: String): Boolean {
            return e.take(1) == "8"
        }
    }

    data object CEP : OceanInputType {
        private const val CEP_DIGITOS = "#####-###"

        override fun getMaxLength() = 8

        override fun getKeyboardType() = KeyboardType.Number

        override fun transformForInput(text: String) = sanitizeWithDigits(text)
        override fun transformForOutput(text: String) = sanitizeWithDigits(text)

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation {
                CEP_DIGITOS
            }
        }
    }

    data object Email : OceanInputType {
        override fun getKeyboardType() = KeyboardType.Email
    }

    data object Phone : OceanInputType {
        private const val PHONE_EIGHT_DIGITS = "(##) ####-####"
        private const val PHONE_NINE_DIGITS = "(##) #####-####"

        override fun getMaxLength() = 11

        override fun getKeyboardType() = KeyboardType.Number

        private fun isNineDigits(text: String): Boolean {
            return text.filter { it.isDigit() }.length > 10
        }

        override fun transformForInput(text: String) = sanitizeWithDigits(text)
        override fun transformForOutput(text: String) = sanitizeWithDigits(text)

        private fun getMask(currentValue: String): String {
            return if (isNineDigits(currentValue)) PHONE_NINE_DIGITS else PHONE_EIGHT_DIGITS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(::getMask)
        }
    }

    data object CPF : OceanInputType {
        private const val CPF_DIGITS = "###.###.###-##"

        override fun getMaxLength() = CPF_DIGITS.count { it == '#' }

        override fun getKeyboardType() = KeyboardType.Number

        override fun transformForInput(text: String) = sanitizeWithDigits(text)
        override fun transformForOutput(text: String) = sanitizeWithDigits(text)

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation {
                CPF_DIGITS
            }
        }
    }

    data object CNPJ : OceanInputType {
        private const val CNPJ_DIGITS = "##.###.###/####-##"

        override fun getMaxLength() = CNPJ_DIGITS.count { it == '#' }

        // Ascii keyboard so the user can type letters [A-Z] required by the alphanumeric
        // CNPJ format from Receita Federal NT 49/2024 (positions 1..12 are [A-Z0-9]).
        // On POS hosts that disable the numeric virtual keyboard, the ASCII keyboard still
        // opens normally because only KeyboardType.Number is in `disabledKeyboards`.
        override fun getKeyboardType() = KeyboardType.Ascii

        override fun transformForInput(text: String) = sanitizeWithAlphanumeric(text)
        override fun transformForOutput(text: String) = sanitizeWithAlphanumeric(text)

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation {
                CNPJ_DIGITS
            }
        }
    }

    data object CpfCnpj : OceanInputType {
        private const val CPF_DIGITS = "###.###.###-##"
        private const val CNPJ_DIGITS = "##.###.###/####-##"
        private const val CPF_LENGTH = 11

        override fun getMaxLength() = CNPJ_DIGITS.count { it == '#' }

        // Ascii keyboard because once the input grows past 11 chars (or the user types a letter)
        // the value is interpreted as an alphanumeric CNPJ.
        override fun getKeyboardType() = KeyboardType.Ascii

        private fun isCnpj(currentValue: String): Boolean =
            currentValue.length > CPF_LENGTH || currentValue.any { it in 'A'..'Z' }

        private fun getMask(currentValue: String): String {
            return if (isCnpj(currentValue)) CNPJ_DIGITS else CPF_DIGITS
        }

        // Strategy: while the value fits CPF length and has no letters, sanitise as digits-only.
        // As soon as a letter is typed or length exceeds 11, sanitise as alphanumeric.
        private fun sanitize(text: String): String {
            val hasLetters = text.any { it.uppercaseChar() in 'A'..'Z' }
            val digitsOnly = text.filter { it.isDigit() }
            return if (hasLetters || digitsOnly.length > CPF_LENGTH) {
                sanitizeWithAlphanumeric(text)
            } else {
                getMaxLength()?.let { digitsOnly.take(it) } ?: digitsOnly
            }
        }

        override fun transformForInput(text: String) = sanitize(text)
        override fun transformForOutput(text: String) = sanitize(text)

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(::getMask)
        }
    }

    data object Date : OceanInputType {
        private const val DATE_MASK = "##/##/####"

        override fun getKeyboardType() = KeyboardType.Number

        override fun getMaxLength() = DATE_MASK.count { it == '#' }

        override fun transformForInput(text: String) = sanitizeWithDigits(text)

        override fun transformForOutput(text: String): String {
            val digitsText = text.filter { it.isDigit() }.take(8)

            if (digitsText.length <= 2) return text

            val day = digitsText.take(2)
            val month = digitsText.drop(2).take(2)

            if (digitsText.length <= 4) return "$day/$month"

            return "$day/$month/${digitsText.drop(4)}"
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation {
                DATE_MASK
            }
        }
    }
}
