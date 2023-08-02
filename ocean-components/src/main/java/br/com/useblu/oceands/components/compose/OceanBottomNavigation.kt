package br.com.useblu.oceands.components.compose

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import br.com.useblu.oceands.model.compose.OceanBottomNavigationModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing


@Preview
@Composable
private fun OceanBottomNavigationPreview() {
    val selectedIndex = remember {
        mutableStateOf(0)
    }

    val models = listOf(
        OceanBottomNavigationModel(
            title = "Home",
            activeIcon = "homesolid",
            inactiveIcon = "homeoutline",
            onClickListener = {
                selectedIndex.value = 0
            }
        ),
        OceanBottomNavigationModel(
            title = "Pagar",
            activeIcon = "pagblusolid",
            inactiveIcon = "pagbluoutline",
            onClickListener = {
                selectedIndex.value = 1
            }
        ),
        OceanBottomNavigationModel(
            title = "Cobrar",
            activeIcon = "chargesolid",
            inactiveIcon = "chargeoutline",
            onClickListener = {
                selectedIndex.value = 2
            }
        ),
        OceanBottomNavigationModel(
            title = "EasterEggs",
            activeIcon = "terminalsolid",
            inactiveIcon = "terminaloutline",
            onClickListener = {
                selectedIndex.value = 3
            }
        )
    )

    OceanBottomNavigation(
        selectedIndex = selectedIndex.value,
        models = models
    )
}
@Composable
fun OceanBottomNavigation(
    selectedIndex: Int = 0,
    models: List<OceanBottomNavigationModel>
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
    ) {
        val xOffsetAnimated = remember { Animatable(0f) }

        LaunchedEffect(key1 = selectedIndex) {
            val xOffset = childrenWidths
                .subList(0, selectedIndex)
                .ifEmpty { listOf(0.dp) }
                .map { density.run { it.toPx() } }
                .reduce { acc, dp -> acc + dp }

            xOffsetAnimated.animateTo(
                targetValue = xOffset,
                animationSpec = tween()
            )
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
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            models.forEachIndexed { index, model ->
                OceanBottomNavigationMenuItem(
                    model = model,
                    isSelected = index == selectedIndex,
                    modifier = Modifier
                        .weight(1f)
                        .onGloballyPositioned {
                            childrenWidths.add(density.run { it.size.width.toDp() })
                        }
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = model.onClickListener
                        )
                )
            }
        }
    }
}

@Composable
private fun OceanBottomNavigationMenuItem(
    model: OceanBottomNavigationModel,
    modifier: Modifier = Modifier,
    isSelected: Boolean
) {
    val color = if (isSelected) OceanColors.interfaceLightPure else OceanColors.brandPrimaryUp
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OceanIcon(
            token = if (isSelected) model.activeIcon else model.inactiveIcon,
            modifier = Modifier.size(22.dp),
            tint = color
        )

        OceanSpacing.StackXXS()

        Text(
            text = model.title,
            color = color,
            fontFamily = OceanFontFamily.HighlightExtraBold,
            fontSize = 10.sp
        )
    }
}