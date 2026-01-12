package br.com.useblu.oceands.components.compose.balance.cardbalance

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import br.com.useblu.oceands.components.compose.balance.BadgeStyle
import br.com.useblu.oceands.components.compose.balance.BadgesContent
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing

@Composable
fun BadgesInteraction(
    badges: List<String>,
    wrapSize: Int,
    badgeSize: Dp = OceanSpacing.md
) {
    BadgesContent(
        wrapSize = wrapSize,
        badges = badges,
        style = BadgeStyle(
            size = badgeSize,
            shape = OceanBorderRadius.Circle.allCorners.shape(),
            fallbackBackgroundColor = OceanColors.brandPrimaryPure,
            fallbackTextColor = OceanColors.interfaceLightPure,
            useClip = true
        )
    )
}
