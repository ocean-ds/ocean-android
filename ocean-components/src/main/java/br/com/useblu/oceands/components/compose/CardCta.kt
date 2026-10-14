package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
private fun CardCtaPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CardCta(
            showProgress = false,
            actionTitle = "Call to Action",
            badgeText = "9",
            actionIcon = OceanIcons.CHEVRON_RIGHT_SOLID,
            actionClick = {}
        )

        CardCta(
            showProgress = true,
            actionTitle = "Call to Action",
            actionIcon = OceanIcons.CHEVRON_RIGHT_SOLID,
            actionClick = {}
        )
    }
}

@Composable
fun CardCta(
    showProgress: Boolean,
    actionTitle: String,
    badgeText: String = "",
    badgeType: OceanBadgeType = OceanBadgeType.WARNING,
    actionIcon: OceanIcons = OceanIcons.CHEVRON_RIGHT_SOLID,
    backgroundColor: Color = OceanColors.interfaceLightPure,
    actionClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor
            )
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = OceanColors.interfaceLightPure,
                disabledContainerColor = OceanColors.interfaceLightPure
            ),
            shape = OceanBorderRadius.SM.bottomCorners.shape(),
            border = BorderStroke(
                width = 1.dp,
                color = OceanColors.interfaceLightDown
            ),
            onClick = { actionClick?.invoke() },
            enabled = !showProgress && actionClick != null
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                if (!showProgress) {
                    Text(
                        text = actionTitle,
                        color = OceanColors.brandPrimaryPure,
                        fontFamily = OceanFontFamily.BaseBold,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = OceanSpacing.xs)
                    )

                    if (badgeText.isNotBlank()) {
                        OceanSpacing.StackXXXS()

                        OceanBadge(
                            text = badgeText,
                            type = badgeType,
                            size = OceanBadgeSize.Medium
                        )

                        OceanSpacing.StackXXXS()
                    }

                    OceanIcon(
                        iconType = actionIcon,
                        tint = OceanColors.brandPrimaryPure,
                        modifier = Modifier
                            .padding(end = OceanSpacing.xxs)
                            .size(20.dp)
                    )
                } else {
                    CircularProgressIndicator(
                        color = OceanColors.brandPrimaryPure,
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                }
            }
        }
    }
}
