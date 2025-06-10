package br.com.useblu.oceands.components.compose.pinpad

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.model.compose.OceanViewStatus
import br.com.useblu.oceands.ui.compose.OceanColors

interface OceanPinPadHandler<Result> {
    val uiState: OceanPinPadUIState

    fun newDigit(digit: String)
    fun deleteLast()
    fun clear()
    fun updateErrorMessage(message: String) { /* no-op */ }

    @Composable
    fun getResult(): Result

    @Composable
    fun getInputColor(status: OceanViewStatus): Color {
        return when (status) {
            OceanViewStatus.Enabled, OceanViewStatus.Disabled -> OceanColors.interfaceLightDeep
            OceanViewStatus.Activated -> OceanColors.interfaceDarkDeep
        }
    }
}
