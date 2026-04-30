package br.com.useblu.oceands.components.compose.pinpad

/**
 * Defines how a pinpad handler should react to the **first** user interaction when an
 * `initialValue` was provided.
 *
 * - [EditInPlace]: the pre-selected value behaves as if the user had just typed it. New
 *   digits append (`current * 10 + digit`) and `deleteLast` removes the last digit.
 * - [ClearOnFirstInput]: the first `newDigit`/`deleteLast` discards the pre-selected
 *   value, so the user effectively overwrites it. Subsequent interactions behave normally.
 *
 * Type-agnostic — handlers query [shouldClearPreSelection] before applying the digit op
 * and decide what "cleared" means for their value type (0L for currency cents, null/0 for
 * installments, etc).
 */
sealed interface OceanPinPadPreSelectionInputMode {
    /**
     * Returns `true` once when the next user interaction should discard the pre-selected
     * value. Stateful — flips back to `false` after the first call so subsequent
     * interactions behave normally.
     */
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
