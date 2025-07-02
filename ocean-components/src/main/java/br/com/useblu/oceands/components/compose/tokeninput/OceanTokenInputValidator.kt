package br.com.useblu.oceands.components.compose.tokeninput

import androidx.compose.ui.text.input.KeyboardType

interface OceanTokenInputValidator {
    fun isValidCharacter(char: Char): Boolean

    fun processInput(input: String): String {
        return input.filter { isValidCharacter(it) }
    }

    val keyboardType: KeyboardType
        get() = KeyboardType.Text
}

object OceanTokenDefaultValidator : OceanTokenInputValidator {
    override fun isValidCharacter(char: Char): Boolean = char.isWhitespace().not()

    override val keyboardType: KeyboardType = KeyboardType.Text
}

object OceanTokenAlphanumericValidator : OceanTokenInputValidator {
    override fun isValidCharacter(char: Char): Boolean = char.isLetterOrDigit()
}

object OceanTokenNumericValidator : OceanTokenInputValidator {
    override fun isValidCharacter(char: Char): Boolean = char.isDigit()

    override val keyboardType: KeyboardType = KeyboardType.Number
}

object OceanTokenAlphaValidator : OceanTokenInputValidator {
    override fun isValidCharacter(char: Char): Boolean = char.isLetter()
}
