package br.com.useblu.oceands.components.compose.pinpad.handlers.currency.models

sealed interface OceanCurrencyPinPadResult {
    data class Success(val value: Double) : OceanCurrencyPinPadResult
    data class Error(val type: OceanCurrencyPinPadError) : OceanCurrencyPinPadResult
}
