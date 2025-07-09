package br.com.useblu.oceands.components.compose.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
fun OceanListSubHeaderPreview() {
    OceanTheme {
        Column {
            OceanListSubHeader(
                title = "Seg 16 Out 2023",
                highlighted = "R$ 42.180,25",
                icon = OceanIcons.INFO_OUTLINE,
                subtitle = "Saldo do dia:"
            )
            OceanListSubHeader(
                title = "Ter 17 Out 2023",
                icon = OceanIcons.INFO_OUTLINE,
                subtitle = "Opa tudo bem?"
            )
            OceanListSubHeader(
                title = "Qua 18 Out 2023",
                icon = OceanIcons.INFO_OUTLINE,
                highlighted = "Highlighted value"
            )
        }
    }
}

@Composable
fun OceanListSubHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String = "",
    highlighted: String = "",
    isSmall: Boolean = false,
    icon: OceanIcons? = null
) {
    val rowHeight = if (isSmall) {
        32.dp
    } else 40.dp

    Row(
        modifier = modifier
            .borderBackground(
                color = OceanColors.interfaceLightUp,
                borderRadius = OceanBorderRadius.SM.topCorners
            )
            .fillMaxWidth()
            .padding(horizontal = OceanSpacing.xs)
            .height(rowHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            OceanIcon(
                iconType = icon,
                modifier = Modifier.size(16.dp),
                tint = OceanColors.interfaceDarkUp
            )

            OceanSpacing.StackXXS()
        }

        Text(
            text = title,
            fontFamily = OceanFontFamily.BaseRegular,
            color = OceanColors.interfaceDarkDown,
            fontSize = OceanFontSize.xxxs,
            modifier = Modifier.weight(1f)
        )

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(start = OceanSpacing.xxs)
        ) {
            if (subtitle.isNotBlank()) {
                OceanText(
                    text = subtitle,
                    fontFamily = OceanFontFamily.BaseRegular,
                    color = OceanColors.interfaceDarkDown,
                    fontSize = OceanFontSize.xxxs
                )
            }

            if (highlighted.isNotBlank()) {
                OceanText(
                    text = highlighted,
                    fontFamily = OceanFontFamily.BaseExtraBold,
                    color = OceanColors.interfaceDarkDown,
                    fontSize = OceanFontSize.xxxs
                )
            }
        }
    }
}
