package br.com.useblu.oceands.components.compose.pinpad.handlers.installments.models

sealed interface OceanInstallmentsPinPadResult {
    data class Success(val installments: Int) : OceanInstallmentsPinPadResult

    data class Error(val type: OceanInstallmentsPinPadError) : OceanInstallmentsPinPadResult
}
