package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import br.com.useblu.oceands.utils.toOceanIcon

@Composable
fun OceanIcon(
    token: String,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    fallbackIcon: String? = null
) {
    val icon = token.toOceanIcon()
    val fallback = fallbackIcon?.toOceanIcon()

    if (icon != null) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = modifier,
            tint = tint
        )

        return
    }

    if (fallback != null) {
        Icon(
            painter = painterResource(id = fallback),
            contentDescription = null,
            modifier = modifier,
            tint = tint
        )

        return
    }

    Spacer(modifier = modifier)
}