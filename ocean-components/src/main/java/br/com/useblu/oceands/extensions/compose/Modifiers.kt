package br.com.useblu.oceands.extensions.compose

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import br.com.useblu.oceands.ui.compose.OceanColors

@Composable
fun Modifier.disabledOverlay(
    isDisabled: Boolean = true,
    disabledAlpha: Float = 0.7f,
    overlayColor: Color = OceanColors.interfaceDarkUp
): Modifier {
    return if (isDisabled) {
        this
            .drawWithContent {
                drawContent()
                drawRect(
                    color = overlayColor.copy(alpha = disabledAlpha),
                    size = size
                )
            }
            .pointerInput(Unit) { detectTapGestures { } }
    } else {
        this
    }
}
