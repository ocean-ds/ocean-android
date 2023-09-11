package br.com.useblu.oceands.components.compose

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors


@Preview
@Composable
fun OceanShimmeringPreview(){
    OceanShimmering { brush ->
        Column(
            modifier = Modifier
                .background(OceanColors.interfaceLightPure)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .width(96.dp)
                        .height(16.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(brush)
                )
            }
            Row {
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(4.dp))
                        .background(brush)
                )
            }
        }
    }
}

@Composable
fun OceanShimmering(
    content: @Composable (Brush) -> Unit
) {
    val gradient = listOf(
        OceanColors.interfaceLightDown.copy(alpha = 0.9f),
        OceanColors.interfaceLightUp.copy(alpha = 0.3f),
        OceanColors.interfaceLightDown.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition(label = "")

    val translateAnimation = transition
        .animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 800,
                    easing = FastOutLinearInEasing
                ),
            ),
            label = ""
        )
    val brush = Brush.linearGradient(
        colors = gradient,
        start = Offset.Zero,
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )
    content(brush)
}
