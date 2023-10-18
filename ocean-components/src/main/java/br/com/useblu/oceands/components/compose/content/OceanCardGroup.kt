package br.com.useblu.oceands.components.compose.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.components.compose.CardCta
import br.com.useblu.oceands.components.compose.OceanBadge
import br.com.useblu.oceands.components.compose.OceanBadgeSize
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing


@Preview
@Composable
fun OceanCardGroupPreview() {
    OceanTheme {
        OceanCardGroup(
            title = "Header",
            subtitle = "Subtitle",
            badgeText = "110",
            actionClick = {},
            actionTitle = "Call to Action",
            label = "Recomendado",
            showProgress = false,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun OceanCardGroup(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    badgeText: String? = null,
    badgeType: OceanBadgeType = OceanBadgeType.WARNING,
    actionTitle: String,
    actionClick: () -> Unit,
    showProgress: Boolean = false,
    label: String? = null
) {
    Box(modifier = modifier) {
        val paddingTop = if (label != null) 9.dp else 0.dp
        Column(
            modifier = Modifier
                .padding(top = paddingTop)
                .border(
                    width = 1.dp,
                    color = OceanColors.interfaceLightDown,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = OceanColors.interfaceLightPure,
                        shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)
                    )
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        fontFamily = OceanFontFamily.BaseExtraBold,
                        fontSize = 16.sp,
                        color = OceanColors.interfaceDarkDeep
                    )

                    if (subtitle != null) {
                        OceanSpacing.StackXXS()

                        Text(
                            text = subtitle,
                            fontFamily = OceanFontFamily.BaseRegular,
                            fontSize = 14.sp,
                            color = OceanColors.interfaceDarkDown
                        )
                    }
                }

                if (badgeText != null) {
                    OceanBadge(
                        text = badgeText,
                        type = badgeType,
                        size = OceanBadgeSize.Medium
                    )
                }
            }

            Divider(color = OceanColors.interfaceLightDown)

            CardCta(
                showProgress = showProgress,
                actionTitle = actionTitle,
                actionClick = actionClick
            )
        }

        if (label != null) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .height(18.dp)
                    .background(
                        color = OceanColors.brandPrimaryDown,
                        shape = RoundedCornerShape(40.dp)
                    )
                    .padding(horizontal = 6.dp)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = label,
                    fontSize = 11.sp,
                    color = OceanColors.interfaceLightPure,
                    fontFamily = OceanFontFamily.BaseBold,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}