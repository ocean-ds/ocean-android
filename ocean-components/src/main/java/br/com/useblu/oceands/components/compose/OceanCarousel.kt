package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanCarouselItem
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay

@Preview
@Composable
fun OceanCarouselPreview() {
    val tucano = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCYLQmqlADnQuH6Of-4GBuuwXrZj16UEM2Bqu_faLa-Q&s"
    val carouselItems = listOf(
        OceanCarouselItem(tucano) {
            println("tucano 1")
        },
        OceanCarouselItem(tucano) {
            println("tucano 2")
        },
        OceanCarouselItem(tucano) {
            println("tucano 3")
        }
    )
    Column {
        OceanCarousel(
            carouselItems,
            showPageIndicator = true,
            autoCycle = true,
            autoCycleTime = 1500
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OceanCarousel(
    items: List<OceanCarouselItem>,
    modifier: Modifier = Modifier,
    showPageIndicator: Boolean = true,
    autoCycle: Boolean = false,
    autoCycleTime: Long = 3000,
    initialPage: Int = 0
) {
    val pagerState = rememberPagerState(initialPage = initialPage)

    LaunchedEffect(key1 = autoCycle) {
        if (autoCycle) {
            while (true) {
                delay(autoCycleTime)
                try {
                    pagerState.animateScrollToPage(
                        page = (pagerState.currentPage + 1) % items.size
                    )
                } catch (_: Exception) {}
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = OceanColors.interfaceLightPure)
    ) {
        HorizontalPager(
            pageCount = items.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 8.dp,
            modifier = Modifier.height(140.dp)
        ) {
            if (LocalInspectionMode.current) {
                Box(
                    modifier = Modifier.background(Color.Blue)
                        .fillMaxSize()
                        .clickable { items[it].action() }
                )
            } else {
                GlideImage(
                    imageModel = { items[it].url },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    ),
                    modifier = Modifier.clickable { items[it].action() }
                )
            }
        }

        if (showPageIndicator) {
            OceanSpacing.StackXS()

            OceanPageIndicator(
                pages = items.size,
                pagerState = pagerState
            )
        }
    }
}