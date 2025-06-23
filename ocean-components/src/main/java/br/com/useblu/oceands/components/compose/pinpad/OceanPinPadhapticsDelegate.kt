package br.com.useblu.oceands.components.compose.pinpad

interface OceanPinPadhapticsDelegate {
    fun didTapKey(key: String)
    fun didTapDelete()
    fun didTapClear()
}
