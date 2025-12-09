package br.com.useblu.blupos.presentation.features.sale.installments.pinpad

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPadHandler
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPadUIState
import br.com.useblu.oceands.components.compose.pinpad.handlers.installments.models.OceanInstallmentsPinPadError
import br.com.useblu.oceands.components.compose.pinpad.handlers.installments.models.OceanInstallmentsPinPadResult
import br.com.useblu.oceands.components.compose.pinpad.handlers.installments.models.OceanInstallmentsPinPadTextSetup
import kotlin.math.pow

class OceanInstallmentsPinPadHandler(
    private var maxInstallments: Int = 1,
    private var selectedInstallment: Int? = null,
    private val textSetup: OceanInstallmentsPinPadTextSetup
) : OceanPinPadHandler<OceanInstallmentsPinPadResult> {
    private val maxDigitsValue = 10.toDouble().pow(maxInstallments.toString().length).toInt()
    private var currentError: OceanInstallmentsPinPadError? = null
        set(value) {
            uiState =
                uiState.copy(
                    toggleError = uiState.toggleError.not()
                )
            field = value
        }

    override var uiState: OceanPinPadUIState by mutableStateOf(
        OceanPinPadUIState(
            inputValue = selectedInstallment?.let { "${it}x" } ?: "",
            placeholder = textSetup.getPlaceholder(),
            hint = textSetup.getHint(maxInstallments)
        )
    )

    override fun newDigit(digit: String) {
        val newInputDigit = digit.toInt()
        val newValue = (selectedInstallment ?: 0) * 10 + newInputDigit
        selectedInstallment = if (newValue < maxDigitsValue) newValue else selectedInstallment
        updateFormattedValue()
    }

    override fun deleteLast() {
        selectedInstallment = selectedInstallment?.div(10)
        updateFormattedValue()
    }

    override fun clear() {
        selectedInstallment = null
        currentError = null
        updateFormattedValue()
    }

    @Composable
    override fun getErrorMessage(): String = when (currentError) {
        OceanInstallmentsPinPadError.Empty -> textSetup.getErrorEmpty()
        OceanInstallmentsPinPadError.Max -> textSetup.getErrorMax(maxInstallments)
        null -> ""
    }

    override fun getResult(): OceanInstallmentsPinPadResult {
        currentError = getError()
        return when (currentError) {
            null ->
                OceanInstallmentsPinPadResult.Success(
                    installments = selectedInstallment ?: 0
                )

            else ->
                OceanInstallmentsPinPadResult.Error(
                    type = currentError ?: OceanInstallmentsPinPadError.Empty
                )
        }
    }

    fun updateMaxInstallments(maxInstallments: Int) {
        this.maxInstallments = maxInstallments
        uiState =
            uiState.copy(
                hint = textSetup.getHint(maxInstallments)
            )
    }

    @VisibleForTesting
    fun updateFormattedValue() {
        val maskedValue =
            selectedInstallment?.let {
                "${it}x"
            } ?: ""
        uiState =
            uiState.copy(
                inputValue = maskedValue
            )
    }

    @VisibleForTesting
    fun getError(): OceanInstallmentsPinPadError? = when {
        (selectedInstallment ?: 0) == 0 -> OceanInstallmentsPinPadError.Empty
        (selectedInstallment ?: 0) > maxInstallments -> OceanInstallmentsPinPadError.Max
        else -> null
    }
}
