package br.com.useblu.oceands.extensions.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import br.com.useblu.oceands.ui.compose.OceanColors

fun Modifier.iconContainerBackground(showBackground: Boolean) = composed {
    if (showBackground) {
        this.background(
            color = OceanColors.interfaceLightUp,
            shape = CircleShape
        )
    } else {
        this
    }
}