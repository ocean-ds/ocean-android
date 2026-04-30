package br.com.useblu.oceands.components.compose.pinpad.handlers.installments

import br.com.useblu.oceands.components.compose.pinpad.OceanPinPadPreSelectionInputMode
import br.com.useblu.oceands.components.compose.pinpad.handlers.installments.models.OceanInstallmentsPinPadResult
import br.com.useblu.oceands.components.compose.pinpad.handlers.installments.models.OceanInstallmentsPinPadTextSetup
import org.junit.Assert.assertEquals
import org.junit.Test

class OceanInstallmentsPinPadHandlerTest {
    private val textSetup =
        object : OceanInstallmentsPinPadTextSetup {
            override fun getPlaceholder() = "0"

            override fun getHint(maxInstallments: Int) = "Até ${maxInstallments}x"

            override fun getErrorEmpty() = "Selecione o número de parcelas"

            override fun getErrorMax(maxInstallments: Int) = "O número máximo de parcelas é $maxInstallments"
        }

    @Test
    fun `default ClearOnFirstInput discards selectedInstallment on first newDigit`() {
        val handler =
            OceanInstallmentsPinPadHandler(
                maxInstallments = 12,
                selectedInstallment = 6,
                textSetup = textSetup
            )

        handler.newDigit("3")

        val result = handler.getResult() as OceanInstallmentsPinPadResult.Success
        // First digit clears the pre-selected 6, then 3 -> 3.
        assertEquals(3, result.installments)
    }

    @Test
    fun `EditInPlace appends digits to selectedInstallment`() {
        val handler =
            OceanInstallmentsPinPadHandler(
                maxInstallments = 99,
                selectedInstallment = 6,
                textSetup = textSetup,
                preSelectionInputMode = OceanPinPadPreSelectionInputMode.EditInPlace
            )

        handler.newDigit("3")

        val result = handler.getResult() as OceanInstallmentsPinPadResult.Success
        // 6 -> 6 * 10 + 3 = 63.
        assertEquals(63, result.installments)
    }
}
