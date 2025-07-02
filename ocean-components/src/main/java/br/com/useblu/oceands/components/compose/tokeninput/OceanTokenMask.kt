package br.com.useblu.oceands.components.compose.tokeninput

/**
 * Interface for transforming the visual representation of token characters
 */
interface OceanTokenMask {
    fun mask(character: Char): String
}

/**
 * Default implementation that shows characters as-is
 */
object OceanTokenDefaultMask : OceanTokenMask {
    override fun mask(character: Char): String = character.toString()
}

/**
 * Masked implementation that shows bullets instead of actual characters
 */
object OceanTokenSecurityMask : OceanTokenMask {
    override fun mask(character: Char): String = "•"
}

object OceanTokenUppercaseMask : OceanTokenMask {
    override fun mask(character: Char): String = character.uppercase()
}
