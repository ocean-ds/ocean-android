package br.com.useblu.oceands.components.compose.pinpad.handlers.password

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPadHandler
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPadUIState
import br.com.useblu.oceands.components.compose.pinpad.handlers.password.models.OceanPasswordPinPadType
import br.com.useblu.oceands.extensions.createColoredHtmlTag
import br.com.useblu.oceands.model.compose.OceanViewStatus
import br.com.useblu.oceands.ui.compose.OceanColors

class OceanPasswordPinPadHandler(
    private val type: OceanPasswordPinPadType,
    private val inputColor: Color = Color.Black
) : OceanPinPadHandler<String> {
    override var uiState: OceanPinPadUIState by mutableStateOf(
        OceanPinPadUIState(
            placeholder = getPlaceholder()
        )
    )
    private var currentValue: String = ""
    private var currentError: String = ""

    override fun newDigit(digit: String) {
        if (canIncrement().not()) return
        currentValue += digit
        updateFormattedValue()
    }

    override fun deleteLast() {
        currentValue = currentValue.dropLast(1)
        updateFormattedValue()
    }

    override fun clear() {
        currentValue = ""
        updateFormattedValue()
    }

    override fun getResult(): String {
        return currentValue
    }

    @Composable
    override fun getInputColor(status: OceanViewStatus): Color {
        return OceanColors.interfaceLightDeep
    }

    @Composable
    override fun getErrorMessage(): String {
        return currentError
    }

    override fun updateErrorMessage(message: String) {
        currentError = message
        uiState = uiState.copy(
            toggleError = uiState.toggleError.not(),
            hint = message
        )
    }

    private fun canIncrement(): Boolean {
        return when (type) {
            is OceanPasswordPinPadType.FixedSize -> currentValue.length < type.size
            is OceanPasswordPinPadType.Limited -> currentValue.length < type.maxSize
            OceanPasswordPinPadType.Unlimited -> true
        }
    }

    private fun updateFormattedValue() {
        val maskedCurrentValue = "•".repeat(currentValue.length)
            .createColoredHtmlTag(
                color = inputColor
            )
        val maskedValue: String = when (type) {
            is OceanPasswordPinPadType.FixedSize -> "•".repeat(maxOf(type.size - currentValue.length, 0)) + maskedCurrentValue
            is OceanPasswordPinPadType.Limited, OceanPasswordPinPadType.Unlimited -> maskedCurrentValue
        }
        uiState = uiState.copy(inputValue = maskedValue)
    }

    private fun getPlaceholder(): String {
        return when (type) {
            is OceanPasswordPinPadType.FixedSize -> "•".repeat(type.size)
            is OceanPasswordPinPadType.Limited -> "•".repeat(type.maxSize)
            OceanPasswordPinPadType.Unlimited -> "••••"
        }
    }
}
