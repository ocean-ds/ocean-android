package br.com.useblu.oceands.components.compose.pinpad.handlers.installments.models

interface OceanInstallmentsPinPadTextSetup {
    fun getPlaceholder(): String
    fun getHint(maxInstallments: Int): String
    fun getErrorEmpty(): String
    fun getErrorMax(maxInstallments: Int): String
}
