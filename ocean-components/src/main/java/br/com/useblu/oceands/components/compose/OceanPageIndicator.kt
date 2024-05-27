package br.com.useblu.oceands.components.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OceanPageIndicator(
    pages: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pages) { iteration ->
            val isSelected = remember(pagerState.currentPage) {
                pagerState.currentPage == iteration
            }

            val color = animateColorAsState(
                targetValue = if (isSelected) OceanColors.brandPrimaryPure else OceanColors.interfaceLightDeep,
                label = "Page Indicator Color",
                animationSpec = tween(300)
            )

            val width = animateDpAsState(
                targetValue = if (isSelected) OceanSpacing.xxs else OceanSpacing.xxxs,
                label = "Page Indicator Width",
                animationSpec = tween(300)
            )

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color.value)
                    .height(4.dp)
                    .width(width.value)
            )
        }
    }
}