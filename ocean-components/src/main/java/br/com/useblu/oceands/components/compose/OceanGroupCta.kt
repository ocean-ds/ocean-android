package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun OceanGroupCtaPreview() {
    OceanTheme {
        Column {
            OceanGroupCta(
                title = "Call to Action",
                onClick = {}
            )

            OceanGroupCta(
                title = "Call to Action",
                onClick = {},
                showProgress = true
            )
        }
    }
}

@Composable
fun OceanGroupCta(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showProgress: Boolean = false,
) {

    Row(
        modifier = modifier
            .background(color = OceanColors.interfaceLightPure)
            .clickable(enabled = !showProgress, onClick = onClick)
            .height(52.dp)
            .padding(horizontal = OceanSpacing.xs)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (showProgress) {
            CircularProgressIndicator(
                color = OceanColors.brandPrimaryPure,
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = title,
                fontFamily = OceanFontFamily.BaseBold,
                fontSize = OceanFontSize.xxs,
                color = OceanColors.brandPrimaryPure,
                modifier = Modifier.weight(1f)
            )

            OceanIcon(
                iconType = OceanIcons.CHEVRON_RIGHT_OUTLINE,
                modifier = Modifier
                    .size(20.dp),
                tint = OceanColors.brandPrimaryPure
            )
        }
    }
}