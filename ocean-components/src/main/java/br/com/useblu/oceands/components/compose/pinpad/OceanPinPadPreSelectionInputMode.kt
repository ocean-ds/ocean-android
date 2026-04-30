package br.com.useblu.oceands.components.compose.pinpad

sealed interface OceanPinPadPreSelectionInputMode {
    fun shouldClearPreSelection(): Boolean

    object EditInPlace : OceanPinPadPreSelectionInputMode {
        override fun shouldClearPreSelection(): Boolean = false
    }

    class ClearOnFirstInput : OceanPinPadPreSelectionInputMode {
        private var hasUserClearedPreSelection = false

        override fun shouldClearPreSelection(): Boolean {
            if (!hasUserClearedPreSelection) {
                hasUserClearedPreSelection = true
                return true
            }
            return false
        }
    }
}
