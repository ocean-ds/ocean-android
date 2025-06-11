package br.com.useblu.oceands.components.compose.pinpad.handlers.currency

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPadHandler
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPadUIState
import br.com.useblu.oceands.components.compose.pinpad.handlers.currency.models.OceanCurrencyPinPadError
import br.com.useblu.oceands.components.compose.pinpad.handlers.currency.models.OceanCurrencyPinPadResult
import br.com.useblu.oceands.components.compose.pinpad.handlers.currency.models.OceanPinPadCurrencyErrorSetup
import br.com.useblu.oceands.extensions.oceanFormatWithCurrency
import java.math.RoundingMode

class OceanCurrencyPinPadHandler(
    initialValue: Double? = null,
    minValue: Double? = null,
    maxValue: Double? = null,
    private val errorSetup: OceanPinPadCurrencyErrorSetup = OceanPinPadCurrencyErrorSetup.Default
) : OceanPinPadHandler<OceanCurrencyPinPadResult> {
    private var currentValue: Long = initialValue?.let { valueToInput(it) } ?: 0L
    private val currentMinValue: Long? = minValue?.let { valueToInput(it) }
    private val currentMaxValue: Long? = maxValue?.let { valueToInput(it) }
    override var uiState: OceanPinPadUIState by mutableStateOf(
        OceanPinPadUIState(
            inputValue = initialValue?.let { formatValue(it) } ?: "",
            placeholder = 0.0.oceanFormatWithCurrency(),
            hint = getHint()
        )
    )
        private set

    override fun newDigit(digit: String) {
        val newLastDigit = digit.toLong()
        currentValue = Math.addExact(
            Math.multiplyExact(currentValue, 10L),
            newLastDigit
        )
        updateFormattedValue()
    }

    override fun deleteLast() {
        currentValue = Math.floorDiv(currentValue, 10L)
        updateFormattedValue()
    }

    override fun clear() {
        currentValue = 0L
        uiState = uiState.copy(inputValue = "")
    }

    @Composable
    override fun getResult(): OceanCurrencyPinPadResult {
        val (error, errorType) = when {
            currentValue == 0L ->
                errorSetup.emptyError to OceanCurrencyPinPadResult.Error(type = OceanCurrencyPinPadError.Empty)
            currentMinValue != null && currentValue < currentMinValue ->
                errorSetup.getMinErrorMessage(minValue = currentMinValue) to OceanCurrencyPinPadResult.Error(type = OceanCurrencyPinPadError.Min)
            currentMaxValue != null && currentValue > currentMaxValue ->
                errorSetup.getMaxErrorMessage(maxValue = currentMaxValue) to OceanCurrencyPinPadResult.Error(type = OceanCurrencyPinPadError.Max)
            else -> "" to null
        }
        uiState.copy(
            error = error
        )
        return errorType ?: OceanCurrencyPinPadResult.Success(value = inputToValue(input = currentValue))
    }

    private fun getHint(): String {
        return errorSetup.getHint(
            minValue = currentMinValue,
            maxValue = currentMaxValue,
            input = currentValue
        )
    }

    private fun formatValue(value: Double): String {
        return value.oceanFormatWithCurrency()
    }

    private fun formatValue(long: Long): String {
        return long.oceanFormatWithCurrency()
    }

    private fun updateFormattedValue() {
        uiState = uiState.copy(
            inputValue = formatValue(currentValue),
            hint = getHint()
        )
    }

    private fun inputToValue(input: Long): Double {
        return input.toBigDecimal()
            .divide(
                100.toBigDecimal(),
                2,
                RoundingMode.HALF_EVEN
            )
            .toDouble()
    }

    private fun valueToInput(value: Double): Long {
        return value.toBigDecimal().multiply(100.toBigDecimal()).toLong()
    }
}
