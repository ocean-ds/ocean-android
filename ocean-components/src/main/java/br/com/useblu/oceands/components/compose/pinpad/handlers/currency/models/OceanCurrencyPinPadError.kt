package br.com.useblu.oceands.components.compose.pinpad.handlers.currency.models

sealed interface OceanCurrencyPinPadError {
    data object Empty : OceanCurrencyPinPadError
    data object Min : OceanCurrencyPinPadError
    data object Max : OceanCurrencyPinPadError
}
