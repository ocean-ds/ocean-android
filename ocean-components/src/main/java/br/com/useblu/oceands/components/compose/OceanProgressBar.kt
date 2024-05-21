package br.com.useblu.oceands.components.compose

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing


@Preview
@Composable
private fun OceanProgressBarPrev() {
    OceanTheme {
        Scaffold {
            Column {
                OceanProgressBar(
                    modifier = Modifier.padding(it),
                    progress = .5f
                )
                OceanProgressBar(
                    modifier = Modifier.padding(it),
                    progress = 0f
                )
                OceanProgressBar(
                    modifier = Modifier.padding(it),
                    progress = 1f
                )
            }
        }
    }
}

@Composable
fun OceanProgressBar(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 1.0) progress: Float
) {
   BoxWithConstraints(
       modifier = modifier
           .background(
               color = OceanColors.brandPrimaryUp,
               shape = RoundedCornerShape(4.dp)
           )
           .fillMaxWidth()
           .height(OceanSpacing.xxs)
   ) {
       val size = this.maxWidth * progress
       Box(
           Modifier
               .background(
                   color = OceanColors.brandPrimaryPure,
                   shape = RoundedCornerShape(4.dp)
               )
               .align(Alignment.CenterStart)
               .fillMaxHeight()
               .width(size)
       )
   }
}