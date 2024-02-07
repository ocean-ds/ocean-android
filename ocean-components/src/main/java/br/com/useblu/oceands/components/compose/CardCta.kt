package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCta(
    showProgress: Boolean,
    actionTitle: String,
    actionIcon: OceanIcons = OceanIcons.CHEVRON_RIGHT_SOLID,
    actionClick: (() -> Unit)? = null
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = OceanColors.interfaceLightPure,
            disabledContainerColor = OceanColors.interfaceLightPure
        ),
        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = OceanColors.interfaceLightDown
        ),
        onClick = { actionClick?.invoke() },
        enabled = !showProgress && actionClick != null,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
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
                        .padding(horizontal = 16.dp)
                )

                OceanIcon(
                    iconType = actionIcon,
                    tint = OceanColors.brandPrimaryPure,
                    modifier = Modifier
                        .padding(end = 8.dp)
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