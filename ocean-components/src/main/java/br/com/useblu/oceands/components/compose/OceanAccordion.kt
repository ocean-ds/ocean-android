package br.com.useblu.oceands.components.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing

@Preview
@Composable
fun PreviewOceanAccordion() {
    Column {
        OceanAccordion(
            title = "Teste",
            description = "Uiuiui ui uiasduhsaudha",
            expanded = true
        )

        OceanSpacing.StackXS()

        OceanAccordion(
            title = "Teste",
            description = "Uiuiui ui uiasduhsaudha",
            expanded = false
        )

        OceanSpacing.StackSM()
    }
}

@Composable
fun OceanAccordion(
    title: String,
    description: String,
    expanded: Boolean
) {
    var expandedContent by remember { mutableStateOf(expanded) }

    Column(
        modifier = Modifier.background(OceanColors.interfaceLightPure)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expandedContent = !expandedContent
                }
                .padding(top = if (expandedContent) OceanSpacing.xxsExtra else OceanSpacing.xs)
                .padding(bottom = if (expandedContent) OceanSpacing.xxs else OceanSpacing.xs)
        ) {
            Text(
                text = title,
                color = if (expandedContent) OceanColors.brandPrimaryDown else OceanColors.interfaceDarkDown,
                fontSize = OceanFontSize.xxs,
                modifier = Modifier.weight(1f)
            )

            OceanSpacing.StackXS()

            Icon(
                painter = painterResource(id = R.drawable.ocean_icon_chevron_right_solid),
                contentDescription = null,
                tint = if (expandedContent) OceanColors.brandPrimaryDown else OceanColors.interfaceDarkDown,
                modifier = Modifier
                    .size(16.dp)
                    .rotate(if (expandedContent) 270f else 90f)
            )
        }

        AnimatedVisibility(visible = expandedContent) {
            Column {
                Text(
                    text = description,
                    color = OceanColors.interfaceDarkDown,
                    fontSize = OceanFontSize.xxs,
                    modifier = Modifier.fillMaxWidth()
                )
                OceanSpacing.StackXS()
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = OceanColors.interfaceLightDown
        )
    }
}
