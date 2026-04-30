package br.com.useblu.oceands.components.compose.pinpad.handlers.currency

import br.com.useblu.oceands.components.compose.pinpad.handlers.currency.models.OceanCurrencyPinPadResult
import org.junit.Assert.assertEquals
import org.junit.Test

class OceanCurrencyPinPadHandlerTest {
    @Test
    fun `default ClearOnFirstInput discards initialValue on first newDigit`() {
        val handler =
            OceanCurrencyPinPadHandler(
                initialValue = 600.52,
                maxValue = 1000.00
            )

        handler.newDigit("5")
        handler.newDigit("0")

        val result = handler.getResult() as OceanCurrencyPinPadResult.Success
        // First digit clears 600.52 -> 0; then 5 -> R$ 0,05; then 0 -> R$ 0,50.
        assertEquals(0.50, result.value, 0.0001)
    }

    @Test
    fun `EditInPlace appends digits to initialValue`() {
        val handler =
            OceanCurrencyPinPadHandler(
                initialValue = 6.00,
                maxValue = 100_000.00,
                preSelectionInputMode = OceanPinPadPreSelectionInputMode.EditInPlace
            )

        handler.newDigit("5")

        val result = handler.getResult() as OceanCurrencyPinPadResult.Success
        // 600 cents -> 600 * 10 + 5 = 6005 cents = R$ 60,05.
        assertEquals(60.05, result.value, 0.0001)
    }

    @Test
    fun `ClearOnFirstInput only fires once — subsequent digits append normally`() {
        val handler =
            OceanCurrencyPinPadHandler(
                initialValue = 600.52,
                maxValue = 1000.00
            )

        handler.newDigit("1")
        handler.newDigit("2")
        handler.newDigit("3")

        val result = handler.getResult() as OceanCurrencyPinPadResult.Success
        // First clears, then 1 -> R$ 0,01; 2 -> R$ 0,12; 3 -> R$ 1,23.
        assertEquals(1.23, result.value, 0.0001)
    }
}
