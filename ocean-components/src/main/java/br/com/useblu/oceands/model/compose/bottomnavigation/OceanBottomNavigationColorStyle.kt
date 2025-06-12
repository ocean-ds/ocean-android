package br.com.useblu.oceands.model.compose.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.ui.compose.OceanColors

sealed interface OceanBottomNavigationColorStyle {
    @get:Composable
    val background: Color

    @get:Composable
    val itemSelected: Color

    @get:Composable
    val item: Color

    @get:Composable
    val selectionColor: Color

    data object Default : OceanBottomNavigationColorStyle {
        override val background: Color
            @Composable get() = OceanColors.brandPrimaryPure
        override val itemSelected: Color
            @Composable get() = OceanColors.interfaceLightPure
        override val item: Color
            @Composable get() = OceanColors.brandPrimaryUp
        override val selectionColor: Color
            @Composable get() = Color(0xA35872F5)
    }

    data object Inverse : OceanBottomNavigationColorStyle {
        override val background: Color
            @Composable get() = OceanColors.interfaceLightUp
        override val itemSelected: Color
            @Composable get() = OceanColors.brandPrimaryPure
        override val item: Color
            @Composable get() = OceanColors.interfaceDarkUp
        override val selectionColor: Color
            @Composable get() = Color(0xFFB8C3F5)
    }
}
