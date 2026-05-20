package br.com.useblu.oceands.ui.compose.stringmask

import androidx.compose.ui.text.input.KeyboardType
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Pure unit tests for [OceanInputType] sanitisation and keyboard configuration.
 *
 * Focuses on CNPJ alphanumeric support introduced for Receita Federal NT 49/2024:
 * [A-Z] is preserved (and upper-cased) in positions 1..12, separators dropped,
 * keyboard switches from `Number` to `Ascii` so the user can actually type letters.
 */
class OceanInputTypeTest {
    // region sanitizeWithAlphanumeric (shared helper) ---------------------------------------

    @Test
    fun `sanitizeWithAlphanumeric upper-cases letters`() {
        assertEquals("AB345678XY0174", OceanInputType.DEFAULT.sanitizeWithAlphanumeric("ab345678xy0174"))
    }

    @Test
    fun `sanitizeWithAlphanumeric drops separators`() {
        assertEquals("AB345678XY0174", OceanInputType.DEFAULT.sanitizeWithAlphanumeric("AB.345.678/XY01-74"))
    }

    @Test
    fun `sanitizeWithAlphanumeric drops symbols and whitespace`() {
        assertEquals("AB345678XY0174", OceanInputType.DEFAULT.sanitizeWithAlphanumeric(" AB-345_678 XY@01;74 "))
    }

    // endregion

    // region OceanInputType.CNPJ ------------------------------------------------------------

    @Test
    fun `CNPJ uses Ascii keyboard so letters can be typed`() {
        assertEquals(KeyboardType.Ascii, OceanInputType.CNPJ.getKeyboardType())
    }

    @Test
    fun `CNPJ has max length 14`() {
        assertEquals(14, OceanInputType.CNPJ.getMaxLength())
    }

    @Test
    fun `CNPJ transform preserves numeric input — backward compatibility`() {
        assertEquals("12345678000195", OceanInputType.CNPJ.transformForInput("12345678000195"))
        assertEquals("12345678000195", OceanInputType.CNPJ.transformForInput("12.345.678/0001-95"))
    }

    @Test
    fun `CNPJ transform preserves alphanumeric input upper-cased`() {
        assertEquals("AB345678XY0174", OceanInputType.CNPJ.transformForInput("ab.345.678/xy01-74"))
    }

    @Test
    fun `CNPJ transform truncates to 14 chars`() {
        assertEquals("AB345678XY0174", OceanInputType.CNPJ.transformForInput("AB345678XY0174EXTRA"))
    }

    // endregion

    // region OceanInputType.CpfCnpj ---------------------------------------------------------

    @Test
    fun `CpfCnpj uses Ascii keyboard`() {
        assertEquals(KeyboardType.Ascii, OceanInputType.CpfCnpj.getKeyboardType())
    }

    @Test
    fun `CpfCnpj treats short numeric input as CPF (digits only)`() {
        assertEquals("123456789", OceanInputType.CpfCnpj.transformForInput("123456789"))
    }

    @Test
    fun `CpfCnpj treats long numeric input as CNPJ (digits only)`() {
        assertEquals("12345678000195", OceanInputType.CpfCnpj.transformForInput("12.345.678/0001-95"))
    }

    @Test
    fun `CpfCnpj treats input with letters as alphanumeric CNPJ`() {
        assertEquals("AB345678XY0174", OceanInputType.CpfCnpj.transformForInput("ab.345.678/xy01-74"))
    }

    @Test
    fun `CpfCnpj preserves uppercase letters and drops separators`() {
        assertEquals("AB345678XY0174", OceanInputType.CpfCnpj.transformForInput("AB.345.678/XY01-74"))
    }

    // endregion

    // region OceanInputType.CPF — numeric only (no change) ----------------------------------

    @Test
    fun `CPF stays Number keyboard`() {
        assertEquals(KeyboardType.Number, OceanInputType.CPF.getKeyboardType())
    }

    @Test
    fun `CPF transform drops letters (numeric only)`() {
        assertEquals("12345678909", OceanInputType.CPF.transformForInput("123A456B789C09"))
    }

    // endregion
}
