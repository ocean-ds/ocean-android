package br.com.useblu.oceands.extensions.compose

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.Dp
import br.com.useblu.oceands.components.compose.input.OceanInputKeyHandler
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.stringmask.OceanInputType

@Composable
fun Modifier.disabledOverlay(
    isDisabled: Boolean = true,
    disabledAlpha: Float = 0.7f,
    overlayColor: Color = OceanColors.interfaceDarkUp,
    borderRadius: OceanBorderRadius? = null
): Modifier {
    return if (isDisabled) {
        this
            .let {
                val shape = borderRadius?.shape() ?: return@let it
                it.clip(shape)
            }
            .drawWithContent {
                drawContent()
                drawRect(
                    color = overlayColor.copy(alpha = disabledAlpha),
                    size = size,
                    blendMode = BlendMode.SrcOver
                )
            }
            .pointerInput(Unit) { detectTapGestures { } }
    } else {
        this
    }
}

@Composable
fun Modifier.height(
    height: Dp?
): Modifier {
    return if (height != null) {
        this.height(height)
    } else {
        this
    }
}

@Composable
fun Modifier.oceanInputKeyHandler(
    enabled: Boolean,
    oceanInputType: OceanInputType,
    value: String,
    textFieldSelection: TextRange,
    maxLength: Int?,
    singleLine: Boolean,
    setSelection: (TextRange) -> Unit,
    onTextChanged: (String) -> Unit
): Modifier {
    return if (oceanInputType.usePhysicalKeyboardOnly()) {
        this.onPreviewKeyEvent { keyEvent ->
            OceanInputKeyHandler.onInputKey(
                keyEvent = keyEvent,
                enabled = enabled,
                oceanInputType = oceanInputType,
                value = value,
                textFieldSelection = textFieldSelection,
                maxLength = maxLength,
                singleLine = singleLine,
                setSelection = setSelection,
                onTextChanged = onTextChanged
            )
        }
    } else {
        this
    }
}
