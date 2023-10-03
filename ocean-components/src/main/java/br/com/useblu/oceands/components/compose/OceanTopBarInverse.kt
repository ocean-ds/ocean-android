package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun PreviewOceanTopBarInverse() {
    Column(
        modifier = Modifier.background(OceanColors.interfaceLightPure)
    ) {
        OceanTopBarInverse(
            title = "Portabilidade",
            icon = OceanIcons.X_OUTLINE,
            onClickIcon = {},
            onClickToolbar = {},
            visibleShadow = true
        )

        OceanSpacing.StackSM()

        OceanTopBarInverse(
            title = "Portabilidade",
            onClickIcon = {},
            onClickToolbar = {},
            visibleShadow = true,
            iconInvisible = true
        )
    }
}

@Composable
fun OceanTopBarInverse(
    title: String,
    icon: OceanIcons? = null,
    onClickIcon: () -> Unit,
    onClickToolbar: () -> Unit = {},
    visibleShadow: Boolean = false,
    iconInvisible: Boolean = false
) {
    Column(
        modifier = Modifier.background(OceanColors.interfaceLightPure)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable {
                    onClickToolbar()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            val topBarIcon = icon ?: OceanIcons.ARROW_LEFT_OUTLINE

            if (!iconInvisible) {
                IconButton(
                    modifier = Modifier.size(56.dp),
                    onClick = { onClickIcon() }
                ) {
                    Icon(
                        painter = painterResource(id = topBarIcon.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = OceanColors.brandPrimaryPure
                    )
                }
            }

            OceanSpacing.StackXS()

            Text(
                text = title,
                fontSize = OceanFontSize.sm,
                fontFamily = OceanFontFamily.HighlightExtraBold,
                color = OceanColors.brandPrimaryPure,
                modifier = Modifier.weight(1f),
                textAlign = if (iconInvisible) TextAlign.Center else TextAlign.Start
            )

            OceanSpacing.StackXS()
        }

        if (visibleShadow) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0x0C0D1414),
                                Color.White
                            )
                        )
                    )
            )
        }
    }
}