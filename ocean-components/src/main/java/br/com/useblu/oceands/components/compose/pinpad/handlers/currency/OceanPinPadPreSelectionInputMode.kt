package br.com.useblu.oceands.components.compose.pinpad.handlers.currency

/**
 * Defines how the pinpad handler should react to the **first** user interaction when an
 * `initialValue` was provided.
 *
 * - [EditInPlace]: the pre-selected value behaves as if the user had just typed it. New
 *   digits append (`current * 10 + digit`) and `deleteLast` removes the last digit.
 * - [ClearOnFirstInput]: the first `newDigit`/`deleteLast` discards the pre-selected
 *   value, so the user effectively overwrites it. Subsequent interactions behave normally.
 */
sealed interface OceanPinPadPreSelectionInputMode {
    fun filter(currentValue: Long): Long

    object EditInPlace : OceanPinPadPreSelectionInputMode {
        override fun filter(currentValue: Long): Long = currentValue
    }

    class ClearOnFirstInput : OceanPinPadPreSelectionInputMode {
        private var hasUserClearedPreSelection = false

        override fun filter(currentValue: Long): Long {
            if (!hasUserClearedPreSelection) {
                hasUserClearedPreSelection = true
                return 0L
            }
            return currentValue
        }
    }
}
