package br.com.useblu.oceands.components.compose.pinpad.password.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPadHandler
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPadUIState
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

    @Composable
    override fun getResult(): String {
        return currentValue
    }

    @Composable
    override fun getInputColor(status: OceanViewStatus): Color {
        return OceanColors.interfaceLightDeep
    }

    override fun updateErrorMessage(message: String) {
        uiState = uiState.copy(
            error = message
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
        uiState.copy(inputValue = maskedValue)
    }

    private fun getPlaceholder(): String {
        return when (type) {
            is OceanPasswordPinPadType.FixedSize -> "•".repeat(type.size)
            is OceanPasswordPinPadType.Limited -> "•".repeat(type.maxSize)
            OceanPasswordPinPadType.Unlimited -> "••••"
        }
    }
}

sealed interface OceanPasswordPinPadType {
    data class FixedSize(val size: Int) : OceanPasswordPinPadType
    data class Limited(val maxSize: Int) : OceanPasswordPinPadType
    data object Unlimited : OceanPasswordPinPadType
}
