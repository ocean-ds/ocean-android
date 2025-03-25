package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.compose.carouselwithcomponents.OceanCarouselComponentItem
import br.com.useblu.oceands.model.compose.carouselwithcomponents.OceanCarouselCycle
import br.com.useblu.oceands.model.compose.carouselwithcomponents.OceanCarouselIndicator
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OceanCarouselWithComponents(
    modifier: Modifier = Modifier,
    pagerModifier: Modifier = Modifier,
    items: List<OceanCarouselComponentItem>,
    indicator: OceanCarouselIndicator = OceanCarouselIndicator.PAGE,
    cycle: OceanCarouselCycle = OceanCarouselCycle.Manual,
    initialPage: Int = 0
) {
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { items.size }
    )

    CarouselCycle(
        cycle = cycle,
        pagerState = pagerState
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = OceanColors.interfaceLightPure)
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = OceanSpacing.xs),
            pageSpacing = OceanSpacing.xxs,
            modifier = pagerModifier
        ) { page ->
            if (LocalInspectionMode.current) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { items[page].action() }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image_placeholder),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            } else {
                Box(
                    modifier = Modifier.clickable { items[page].action() },
                    contentAlignment = Alignment.Center
                ) {
                    items[page].component()
                }
            }
        }

        PageIndicator(
            type = indicator,
            pages = items.size,
            pagerState = pagerState
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CarouselCycle(
    cycle: OceanCarouselCycle,
    pagerState: PagerState
) {
    LaunchedEffect(key1 = cycle) {
        when (cycle) {
            is OceanCarouselCycle.Manual -> {
                return@LaunchedEffect
            }
            is OceanCarouselCycle.Auto -> {
                doCycle(cycle = cycle, pagerState = pagerState)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PageIndicator(
    type: OceanCarouselIndicator,
    pages: Int,
    pagerState: PagerState
) {
    when (type) {
        OceanCarouselIndicator.PAGE -> {
            if (type.canShowPages(count = pages)) {
                OceanPageIndicator(
                    modifier = Modifier
                        .padding(top = OceanSpacing.xxs),
                    pages = pages,
                    pagerState = pagerState
                )
            }
        }
        OceanCarouselIndicator.NONE -> {
            return
        }
    }
}

@Preview
@Composable
fun OceanCarouselWithComponentsPreview() {
    val carouselItems = listOf(
        OceanCarouselComponentItem(
            component = {
                Image(
                    painter = painterResource(id = R.drawable.image_placeholder),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
        ) {}
    )

    OceanCarouselWithComponents(
        modifier = Modifier,
        items = carouselItems,
        indicator = OceanCarouselIndicator.PAGE,
        cycle = OceanCarouselCycle.Manual,
        initialPage = 0
    )
}

@OptIn(ExperimentalFoundationApi::class)
private suspend fun doCycle(cycle: OceanCarouselCycle.Auto, pagerState: PagerState) {
    runCatching {
        delay(timeMillis = cycle.time)
        pagerState.animateScrollToPage(
            page = (pagerState.currentPage + 1) % pagerState.pageCount
        )
        doCycle(cycle = cycle, pagerState = pagerState)
    }
}
