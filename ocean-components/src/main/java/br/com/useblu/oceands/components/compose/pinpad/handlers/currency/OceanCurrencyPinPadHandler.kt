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
    testValue: Double? = null,
    private val errorSetup: OceanPinPadCurrencyErrorSetup = OceanPinPadCurrencyErrorSetup.Default
) : OceanPinPadHandler<OceanCurrencyPinPadResult> {
    private var currentValue: Long = initialValue?.let { valueToInput(it) } ?: 0L
    private val currentMinValue: Long? = minValue?.let { valueToInput(it) }
    private val currentMaxValue: Long? = maxValue?.let { valueToInput(it) }
    private val currentTestValue: Long? = testValue?.let { valueToInput(it) }

    override var uiState: OceanPinPadUIState by mutableStateOf(
        OceanPinPadUIState(
            inputValue = initialValue?.let { formatValue(it) } ?: "",
            placeholder = 0.0.oceanFormatWithCurrency(),
            hint = getHint()
        )
    )
        private set

    private var currentError: OceanCurrencyPinPadError? = null

    override fun newDigit(digit: String) {
        val newLastDigit = digit.toLong()
        try {
            val newValue = Math.addExact(
                Math.multiplyExact(currentValue, 10L),
                newLastDigit
            )
            currentValue = newValue
        } catch (e: Exception) { /* no-op */ }
        updateFormattedValue()
    }

    override fun deleteLast() {
        currentValue = Math.floorDiv(currentValue, 10L)
        updateFormattedValue()
    }

    override fun clear() {
        currentValue = 0L
        currentError = null
        uiState = uiState.copy(
            inputValue = ""
        )
    }

    override fun getResult(): OceanCurrencyPinPadResult {
        if (currentTestValue != null && currentValue == currentTestValue) return getSuccess()

        val errorType = when {
            currentValue == 0L ->
                OceanCurrencyPinPadResult.Error(type = OceanCurrencyPinPadError.Empty)
            currentMinValue != null && currentValue < currentMinValue ->
                OceanCurrencyPinPadResult.Error(type = OceanCurrencyPinPadError.Min)
            currentMaxValue != null && currentValue > currentMaxValue ->
                OceanCurrencyPinPadResult.Error(type = OceanCurrencyPinPadError.Max)
            else -> null
        }
        currentError = errorType?.type
        uiState = uiState.copy(
            toggleError = uiState.toggleError.not()
        )

        return errorType ?: getSuccess()
    }

    @Composable
    override fun getErrorMessage(): String {
        return when (currentError) {
            OceanCurrencyPinPadError.Empty -> errorSetup.emptyError
            OceanCurrencyPinPadError.Min -> errorSetup.getMinErrorMessage(minValue = currentMinValue ?: 0L)
            OceanCurrencyPinPadError.Max -> errorSetup.getMaxErrorMessage(maxValue = currentMaxValue ?: 0L)
            null -> ""
        }
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

    private fun getSuccess(): OceanCurrencyPinPadResult {
        return OceanCurrencyPinPadResult.Success(value = inputToValue(input = currentValue))
    }
}
