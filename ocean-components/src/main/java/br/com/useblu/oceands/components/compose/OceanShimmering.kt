package br.com.useblu.oceands.components.compose

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.skeleton.OceanStatusListItemSkeleton
import br.com.useblu.oceands.ui.compose.OceanColors
import kotlinx.coroutines.delay


@Preview
@Composable
fun PreviewSkeleton() {
    var showSkeleton by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LaunchedEffect(key1 = true) {
            delay(2000)
            showSkeleton = false
        }

        if (showSkeleton) {
            ScreenSkeleton()
        } else {
            OceanStatusListItem(title = "Item 1")
            OceanStatusListItem(title = "Item 2")
            OceanStatusListItem(title = "Item 3")
            OceanStatusListItem(title = "Item 4")
            OceanStatusListItem(title = "Item 5")
            OceanStatusListItem(title = "Item 6")
        }

    }
}

@Composable
private fun ScreenSkeleton() {
    repeat(5) {
        OceanStatusListItemSkeleton()
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
