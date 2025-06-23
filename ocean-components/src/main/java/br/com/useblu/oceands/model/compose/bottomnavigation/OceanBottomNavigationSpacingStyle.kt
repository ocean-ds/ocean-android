package br.com.useblu.oceands.model.compose.bottomnavigation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface OceanBottomNavigationSpacingStyle {
    data object Default : OceanBottomNavigationSpacingStyle
    data object Compact : OceanBottomNavigationSpacingStyle

    val height: Dp?
        get() {
            return when (this) {
                is Default -> 64.dp
                is Compact -> null
            }
        }

    val itemArrangementSpacing: Dp
        @Composable get() {
            return when (this) {
                is Default -> br.com.useblu.oceands.ui.compose.OceanSpacing.xxs
                is Compact -> br.com.useblu.oceands.ui.compose.OceanSpacing.xxxs
            }
        }

    val contentBoxModifier: Modifier
        @Composable get() {
            return when (this) {
                is Default -> Modifier.fillMaxHeight()
                is Compact -> Modifier
            }
        }

    val iconSize: Dp
        @Composable get() {
            return when (this) {
                is Default -> 22.dp
                is Compact -> 20.dp
            }
        }
}
