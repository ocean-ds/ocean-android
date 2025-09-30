package br.com.useblu.oceands.components.compose.cardlistexpandable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Composable
fun OceanCardListExpandable(
    modifier: Modifier = Modifier,
    title: String = "",
    description: String = "",
    startExpanded: Boolean = false,
    expandedContent: @Composable () -> Unit
) {
    var isExpanded by remember { mutableStateOf(startExpanded) }
    val animatedRotation = animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "OceanCardListExpandableInteraction"
    )

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = OceanColors.interfaceLightPure
        ),
        shape = OceanBorderRadius.SM.allCorners.shape(),
        border = BorderStroke(1.dp, OceanColors.interfaceLightDown),
        onClick = {
            isExpanded = !isExpanded
        }
    ) {
        Column(
            modifier = Modifier
                .padding(OceanSpacing.xs),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxsExtra)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    OceanText(
                        text = title,
                        style = OceanTextStyle.paragraph,
                        color = OceanColors.interfaceDarkDeep
                    )
                    OceanText(
                        text = description,
                        style = OceanTextStyle.description
                    )
                }

                OceanIcon(
                    modifier = Modifier
                        .rotate(degrees = animatedRotation.value),
                    iconType = OceanIcons.CHEVRON_DOWN_SOLID,
                    tint = OceanColors.interfaceDarkUp
                )
            }
        }

        AnimatedVisibility(
            visible = isExpanded
        ) {
            expandedContent()
        }
    }
}

@Preview
@Composable
fun OceanCardListExpandablePreview() {
    OceanTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(OceanColors.interfaceLightPure)
        ) {
            OceanCardListExpandable(
                modifier = Modifier
                    .padding(OceanSpacing.xs),
                title = "Expandable Card",
                description = "This is an expandable card. Click to expand or collapse.",
                startExpanded = false
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(OceanColors.brandPrimaryUp)
                        .padding(OceanSpacing.sm),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OceanText("Here is the expanded content!")
                }
            }
        }
    }
}
