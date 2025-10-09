package br.com.useblu.oceands.components.compose.input

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.key.utf16CodePoint
import androidx.compose.ui.text.TextRange
import br.com.useblu.oceands.ui.compose.stringmask.OceanInputType

object OceanInputKeyHandler {
    fun onInputKey(
        keyEvent: KeyEvent,
        enabled: Boolean,
        oceanInputType: OceanInputType,
        value: String,
        textFieldSelection: TextRange,
        maxLength: Int?,
        singleLine: Boolean,
        setSelection: (TextRange) -> Unit,
        onTextChanged: (String) -> Unit
    ): Boolean {
        if (!enabled) return false
        if (keyEvent.type == KeyEventType.KeyDown) return false
        return when (keyEvent.key.keyCode) {
            Key.Backspace.keyCode -> handleBackspace(
                oceanInputType = oceanInputType,
                value = value,
                textFieldSelection = textFieldSelection,
                maxLength = maxLength,
                setSelection = setSelection,
                onTextChanged = onTextChanged
            )

            Key.Enter.keyCode -> handleEnter(
                singleLine = singleLine,
                oceanInputType = oceanInputType,
                value = value,
                textFieldSelection = textFieldSelection,
                maxLength = maxLength,
                setSelection = setSelection,
                onTextChanged = onTextChanged
            )

            else -> handleDefault(
                keyEvent = keyEvent,
                oceanInputType = oceanInputType,
                value = value,
                textFieldSelection = textFieldSelection,
                maxLength = maxLength,
                setSelection = setSelection,
                onTextChanged = onTextChanged
            )
        }
    }

    private fun handleBackspace(
        oceanInputType: OceanInputType,
        value: String,
        textFieldSelection: TextRange,
        maxLength: Int?,
        setSelection: (TextRange) -> Unit,
        onTextChanged: (String) -> Unit
    ): Boolean {
        val text = oceanInputType.transformForInput(value)
        val safeStart = textFieldSelection.start.coerceIn(0, text.length)
        if (safeStart > 0) {
            val newText = insertTextAtSelection(
                value = text,
                selection = TextRange(safeStart - 1, safeStart),
                insertValue = ""
            )
            updateSelectionAndText(newText, oceanInputType, maxLength, setSelection, onTextChanged)
        }
        return true
    }

    private fun handleEnter(
        singleLine: Boolean,
        oceanInputType: OceanInputType,
        value: String,
        textFieldSelection: TextRange,
        maxLength: Int?,
        setSelection: (TextRange) -> Unit,
        onTextChanged: (String) -> Unit
    ): Boolean {
        if (singleLine) return false
        val text = oceanInputType.transformForInput(value)
        val newText = insertTextAtSelection(
            value = text,
            selection = textFieldSelection,
            insertValue = "\n"
        )
        updateSelectionAndText(newText, oceanInputType, maxLength, setSelection, onTextChanged)
        return true
    }

    private fun handleDefault(
        keyEvent: KeyEvent,
        oceanInputType: OceanInputType,
        value: String,
        textFieldSelection: TextRange,
        maxLength: Int?,
        setSelection: (TextRange) -> Unit,
        onTextChanged: (String) -> Unit
    ): Boolean {
        val unicodeChar = keyEvent.utf16CodePoint.takeIf { it != 0 }?.toChar() ?: return false
        if (unicodeChar.isISOControl()) return false
        val text = oceanInputType.transformForInput(value)
        val newText = insertTextAtSelection(
            value = text,
            selection = textFieldSelection,
            insertValue = unicodeChar.toString()
        )
        updateSelectionAndText(newText, oceanInputType, maxLength, setSelection, onTextChanged)
        return true
    }

    private fun insertTextAtSelection(
        value: String,
        selection: TextRange,
        insertValue: String
    ): String {
        val safeStart = selection.start.coerceIn(0, value.length)
        val safeEnd = selection.end.coerceIn(0, value.length)
        return value.substring(0, safeStart) + insertValue + value.substring(safeEnd)
    }

    private fun updateSelectionAndText(
        newText: String,
        oceanInputType: OceanInputType,
        maxLength: Int?,
        setSelection: (TextRange) -> Unit,
        onTextChanged: (String) -> Unit
    ) {
        setSelection(TextRange(newText.length))
        val outputValue = oceanInputType.transformForOutput(newText)
        val finalValue = if (maxLength != null && outputValue.length > maxLength) {
            outputValue.take(maxLength)
        } else {
            outputValue
        }
        onTextChanged(finalValue)
    }
}
