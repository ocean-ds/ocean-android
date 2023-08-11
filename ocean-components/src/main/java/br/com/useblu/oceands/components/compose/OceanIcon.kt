package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.model.OceanIconType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.utils.toOceanIcon

@Preview(showBackground = true)
@Composable
fun OceanIconPreview() {
    OceanIcon(
        iconType = OceanIconType.SHIELD_CHECK_OUTLINE,
        tint = OceanColors.brandPrimaryPure,
        fallbackIcon = OceanIconType.EXCLAMATION_CIRCLE_OUTLINE
    )
}

@Composable
fun OceanIcon(
    iconType: OceanIconType,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    fallbackIcon: OceanIconType? = null
) {
    val icon = iconType.toOceanIcon()
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
