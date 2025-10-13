package br.com.useblu.oceands.components.compose.pinpad

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type

object PinPadInputKeyHandler {
    fun <Result> onInputKey(
        keyEvent: KeyEvent,
        enabled: Boolean,
        handler: OceanPinPadHandler<Result>
    ): Boolean {
        if (!enabled) return false
        if (keyEvent.type == KeyEventType.KeyDown) return false
        return when (keyEvent.key.keyCode) {
            Key.Backspace.keyCode -> {
                handler.deleteLast()
                true
            }
            Key.NumPad0.keyCode, Key.Zero.keyCode -> {
                handler.newDigit("0")
                true
            }
            Key.NumPad1.keyCode, Key.One.keyCode -> {
                handler.newDigit("1")
                true
            }
            Key.NumPad2.keyCode, Key.Two.keyCode -> {
                handler.newDigit("2")
                true
            }
            Key.NumPad3.keyCode, Key.Three.keyCode -> {
                handler.newDigit("3")
                true
            }
            Key.NumPad4.keyCode, Key.Four.keyCode -> {
                handler.newDigit("4")
                true
            }
            Key.NumPad5.keyCode, Key.Five.keyCode -> {
                handler.newDigit("5")
                true
            }
            Key.NumPad6.keyCode, Key.Six.keyCode -> {
                handler.newDigit("6")
                true
            }
            Key.NumPad7.keyCode, Key.Seven.keyCode -> {
                handler.newDigit("7")
                true
            }
            Key.NumPad8.keyCode, Key.Eight.keyCode -> {
                handler.newDigit("8")
                true
            }
            Key.NumPad9.keyCode, Key.Nine.keyCode -> {
                handler.newDigit("9")
                true
            }
            else -> false
        }
    }
}
