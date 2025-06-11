package br.com.useblu.oceands.components.compose.pinpad.handlers.password.models

sealed interface OceanPasswordPinPadType {
    data class FixedSize(val size: Int) : OceanPasswordPinPadType
    data class Limited(val maxSize: Int) : OceanPasswordPinPadType
    data object Unlimited : OceanPasswordPinPadType
}
