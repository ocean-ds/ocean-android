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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
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
        mutableStateOf(2)
    }
    val models = listOf(
        OceanBottomNavigationModel(
            label = "Home",
            activeIcon = "homesolid",
            inactiveIcon = "homeoutline",
            onClickListener = {
                selectedIndex.value = 0
            }
        ),
        OceanBottomNavigationModel(
            label = "Pagar",
            activeIcon = "pagblusolid",
            inactiveIcon = "pagbluoutline",
            onClickListener = {
                selectedIndex.value = 1
            }
        ),
        OceanBottomNavigationModel(
            label = "Cobrar",
            activeIcon = "chargesolid",
            inactiveIcon = "chargeoutline",
            onClickListener = {
                selectedIndex.value = 2
            }
        ),
        OceanBottomNavigationModel(
            label = "EasterEggs",
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
    val childrenWidths = remember(models) {
        mutableStateListOf<Int>()
    }
    val density = LocalDensity.current

    fun getBackgroundOffset(): Float {
        return childrenWidths
            .subList(0, selectedIndex.coerceAtMost(childrenWidths.size))
            .ifEmpty { listOf(0) }
            .reduce { acc, dp -> acc + dp }
            .toFloat()
    }

    val xOffsetAnimated = remember(models) {
        val initialOffset = getBackgroundOffset()
        Animatable(initialOffset)
    }

    LaunchedEffect(key1 = selectedIndex, key2 = models) {
        val xOffset = getBackgroundOffset()

        xOffsetAnimated.animateTo(
            targetValue = xOffset,
            animationSpec = tween()
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = OceanColors.brandPrimaryPure)
            .padding(4.dp)
            .height(64.dp)
    ) {
        val backgroundWidth = density.run { (childrenWidths.getOrNull(selectedIndex) ?: 0).toDp() }

        Box(
            modifier = Modifier
                .offset {
                    IntOffset(x = xOffsetAnimated.value.toInt(), y = 0)
                }
                .width(backgroundWidth)
                .fillMaxHeight()
                .background(
                    color = Color(0xA35872F5),
                    shape = RoundedCornerShape(8.dp)
                )
        )

        ItemsRow(models, selectedIndex, childrenWidths)
    }
}

@Composable
private fun ItemsRow(
    models: List<OceanBottomNavigationModel>,
    selectedIndex: Int,
    childrenWidths: SnapshotStateList<Int>
) {
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
                    .onSizeChanged {
                        childrenWidths.add(it.width)
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
            text = model.label,
            color = color,
            fontFamily = OceanFontFamily.HighlightExtraBold,
            fontSize = 10.sp
        )
    }
}