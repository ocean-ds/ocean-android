package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.utils.OceanIcons

@Composable
fun CardCta(
    showProgress: Boolean,
    actionTitle: String,
    actionIcon: OceanIcons = OceanIcons.CHEVRON_RIGHT_SOLID,
    actionClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = OceanColors.interfaceLightPure,
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
            .border(
                width = 1.dp,
                color = OceanColors.interfaceLightDown,
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
            .clip(
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
            .run {
                if (actionClick != null) {
                    clickable { actionClick() }
                } else this
            }
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