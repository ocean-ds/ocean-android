package br.com.useblu.oceands.components.compose

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.model.compose.OceanBottomNavigationModel
import br.com.useblu.oceands.model.compose.bottomnavigation.OceanBottomNavigationColorStyle
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

private val selectedIndex = mutableStateOf(2)

private val initialModelList = mutableStateOf(
    listOf(
        OceanBottomNavigationModel(
            label = "Home",
            activeIcon = OceanIcons.HOME_SOLID,
            inactiveIcon = OceanIcons.HOME_OUTLINE,
            onClickListener = {
                selectedIndex.value = 0
            }
        ),
        OceanBottomNavigationModel(
            label = "Pagar",
            activeIcon = OceanIcons.PAG_BLU_SOLID,
            inactiveIcon = OceanIcons.PAGBLU_OUTLINE,
            onClickListener = {
                selectedIndex.value = 1
            }
        ),
        OceanBottomNavigationModel(
            label = "Cobrar",
            activeIcon = OceanIcons.CHARGE_SOLID,
            inactiveIcon = OceanIcons.CHARGE_OUTLINE,
            onClickListener = {
                selectedIndex.value = 2
            }
        )
    )
)

@Preview
@Composable
private fun OceanBottomNavigationPreview() {
    remember {
        OceanBottomNavigationModel(
            label = "EasterEggs",
            activeIcon = OceanIcons.TERMINAL_SOLID,
            inactiveIcon = OceanIcons.TERMINAL_OUTLINE
        ) {
            println("click add easter egg")
            selectedIndex.value = 3
            initialModelList.value = initialModelList.value.toMutableList().apply {
                add(
                    OceanBottomNavigationModel(
                        label = "EasterEggs",
                        activeIcon = OceanIcons.TERMINAL_SOLID,
                        inactiveIcon = OceanIcons.TERMINAL_OUTLINE,
                        onClickListener = {
                            selectedIndex.value = initialModelList.value.size - 1
                        }
                    )
                )
            }
        }.let {
            initialModelList.value = initialModelList.value.toMutableList().apply {
                add(it)
            }
            initialModelList.value
        }
    }

    Scaffold(
        bottomBar = {
            OceanBottomNavigation(
                selectedIndex = selectedIndex.value,
                models = initialModelList.value
            )
        }
    ) {
        it
    }
}

@Composable
fun OceanBottomNavigation(
    selectedIndex: Int,
    models: List<OceanBottomNavigationModel>,
    colorStyle: OceanBottomNavigationColorStyle = OceanBottomNavigationColorStyle.Default
) {
    val density = LocalDensity.current

    val rowWidth = remember {
        mutableStateOf(0.dp)
    }

    val xOffset = animateDpAsState(
        targetValue = rowWidth.value / (models.size).coerceAtLeast(1) * selectedIndex,
        label = "Background offset"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorStyle.background)
            .windowInsetsPadding(
                WindowInsets.systemBars.only(
                    WindowInsetsSides.Bottom
                )
            )
            .padding(OceanSpacing.xxxs)
            .height(64.dp)
    ) {
        val backgroundWidth = if (models.isNotEmpty()) rowWidth.value / models.size
        else 0.dp

        Box(
            modifier = Modifier
                .offset(x = xOffset.value)
                .width(backgroundWidth)
                .fillMaxHeight()
                .borderBackground(
                    color = colorStyle.selectionColor,
                    borderRadius = OceanBorderRadius.SM.allCorners
                )
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged {
                    rowWidth.value = density.run { it.width.toDp() }
                },
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            models.forEachIndexed { index, model ->
                OceanBottomNavigationMenuItem(
                    model = model,
                    isSelected = index == selectedIndex,
                    colorStyle = colorStyle,
                    modifier = Modifier
                        .weight(1f)
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
    isSelected: Boolean,
    colorStyle: OceanBottomNavigationColorStyle
) {
    val color = if (isSelected) colorStyle.itemSelected else colorStyle.item
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OceanIcon(
            iconType = if (isSelected) model.activeIcon else model.inactiveIcon,
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
