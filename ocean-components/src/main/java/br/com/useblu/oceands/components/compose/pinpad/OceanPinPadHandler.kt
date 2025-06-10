package br.com.useblu.oceands.components.compose.pinpad

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.model.compose.OceanViewStatus
import br.com.useblu.oceands.ui.compose.OceanColors

interface OceanPinPadHandler {
    val uiState: OceanPinPadUIState

    fun newDigit(digit: String)
    fun deleteLast()
    fun clear()

    @Composable
    fun getInputColor(status: OceanViewStatus): Color {
        return when (status) {
            OceanViewStatus.Enabled -> OceanColors.interfaceLightDeep
            OceanViewStatus.Disabled -> OceanColors.interfaceLightDeep
            OceanViewStatus.Activated -> OceanColors.interfaceDarkDeep
        }
    }
}
