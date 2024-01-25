package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.utils.OceanIcons

@Preview(showBackground = true)
@Composable
fun OceanIconPreview() {
    OceanIcon(
        iconType = OceanIcons.SHIELD_CHECK_OUTLINE,
        tint = OceanColors.brandPrimaryPure,
        fallbackIcon = OceanIcons.EXCLAMATION_CIRCLE_OUTLINE
    )
}

@Composable
fun OceanIcon(
    iconType: OceanIcons,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    fallbackIcon: OceanIcons? = null
) {
    if (iconType != OceanIcons.UNDEFINED) {
        Icon(
            painter = painterResource(id = iconType.icon),
            contentDescription = null,
            modifier = modifier,
            tint = tint
        )

        return
    }

    if (fallbackIcon != null) {
        Icon(
            painter = painterResource(id = fallbackIcon.icon),
            contentDescription = null,
            modifier = modifier,
            tint = tint
        )

        return
    }

    Spacer(modifier = modifier)
}
