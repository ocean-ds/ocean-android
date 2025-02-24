package br.com.useblu.oceands.components.compose

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.borderBackground

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
            .borderBackground(
                color = OceanColors.brandPrimaryUp,
                borderRadius = OceanBorderRadius.Tiny.allCorners
            )
            .fillMaxWidth()
            .height(OceanSpacing.xxs)
    ) {
        val size = this.maxWidth * progress
        Box(
            Modifier
                .borderBackground(
                    color = OceanColors.brandPrimaryPure,
                    borderRadius = OceanBorderRadius.Tiny.allCorners
                )
                .align(Alignment.CenterStart)
                .fillMaxHeight()
                .width(size)
        )
    }
}
