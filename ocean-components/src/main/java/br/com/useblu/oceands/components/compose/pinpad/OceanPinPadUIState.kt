package br.com.useblu.oceands.components.compose.pinpad

data class OceanPinPadUIState(
    val inputValue: String = "",
    val placeholder: String = "",
    val toggleError: Boolean = false,
    val hint: String = ""
)
