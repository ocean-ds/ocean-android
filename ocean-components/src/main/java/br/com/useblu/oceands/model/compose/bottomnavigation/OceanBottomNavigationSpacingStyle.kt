package br.com.useblu.oceands.model.compose.bottomnavigation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.ui.compose.OceanSpacing

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

    @Composable
    fun getModifier(modifier: Modifier): Modifier {
        return when (this) {
            is Default -> modifier.wrapContentHeight()
            is Compact ->
                modifier
                    .padding(horizontal = OceanSpacing.xxsExtra)
                    .padding(vertical = OceanSpacing.xxxs)
        }
    }

    val fontSize: TextUnit
        get() =
            when (this) {
                is Default -> 10.sp
                is Compact -> 12.sp
            }
}
