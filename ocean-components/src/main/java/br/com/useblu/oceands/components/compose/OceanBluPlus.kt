package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Composable
fun OceanBluPlus(
    bluPlusValue: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = Color(0xFF2244E8),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick()
            }
            .height(40.dp)
            .padding(start = 8.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_blu_plus), contentDescription = null,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            bluPlusValue.toString(),
            style = OceanTextStyle.caption,
            color = OceanColors.interfaceLightPure
        )

        Spacer(modifier = Modifier.size(4.dp))

        OceanIcon(
            iconType = OceanIcons.CHEVRON_RIGHT_SOLID,
            tint = OceanColors.brandPrimaryUp,
            modifier = Modifier.size(16.dp)
        )
    }
}