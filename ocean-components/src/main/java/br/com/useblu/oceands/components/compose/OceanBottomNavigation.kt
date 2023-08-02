package br.com.useblu.oceands.components.compose

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing


@Preview
@Composable
fun OceanBottomNavigationPreview() {
    val selectedIndex = remember {
        mutableStateOf(0)
    }
    OceanBottomNavigation(
        selectedIndex = selectedIndex.value,
        onClick = {
            selectedIndex.value = (selectedIndex.value + 1) % 3
        }
    )
}
@Composable
fun OceanBottomNavigation(
    selectedIndex: Int = 0,
    onClick: () -> Unit = {}
) {
    val childrenWidths = remember {
        mutableStateListOf<Dp>()
    }

    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = OceanColors.brandPrimaryPure)
            .padding(4.dp)
            .height(64.dp)
            .clickable { onClick() }
    ) {
        val xOffsetAnimated = remember { Animatable(0f) }

        LaunchedEffect(key1 = selectedIndex) {
            val xOffset = childrenWidths
                .subList(0, selectedIndex)
                .ifEmpty { listOf(0.dp) }
                .map { density.run { it.toPx() } }
                .reduce { acc, dp -> acc + dp }

            xOffsetAnimated.animateTo(xOffset)
        }

        Box(
            modifier = Modifier
                .offset {
                    IntOffset(x = xOffsetAnimated.value.toInt(), y = 0)
                }
                .width(childrenWidths.getOrNull(selectedIndex) ?: 0.dp)
                .fillMaxHeight()
                .background(
                    color = Color(0xA35872F5),
                    shape = RoundedCornerShape(8.dp)
                )
        )

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OceanBottomNavigationMenuItem(
                modifier = Modifier
                    .weight(1f)
                    .onGloballyPositioned {
                        childrenWidths.add(density.run { it.size.width.toDp() })
                    }
            )

            OceanBottomNavigationMenuItem(
                modifier = Modifier
                    .weight(1f)
                    .onGloballyPositioned {
                        childrenWidths.add(density.run { it.size.width.toDp() })
                    }
            )

            OceanBottomNavigationMenuItem(
                modifier = Modifier
                    .weight(1f)
                    .onGloballyPositioned {
                        childrenWidths.add(density.run { it.size.width.toDp() })
                    }
            )
        }
    }
}

@Composable
private fun OceanBottomNavigationMenuItem(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OceanIcon(
            token = "terminalsolid",
            modifier = Modifier.size(22.dp),
            tint = OceanColors.interfaceLightPure
        )

        OceanSpacing.StackXXS()

        Text(
            text = "Easter Egg",
            color = OceanColors.interfaceLightPure,
            fontFamily = OceanFontFamily.HighlightExtraBold,
            fontSize = 10.sp
        )
    }
}