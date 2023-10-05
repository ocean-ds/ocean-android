package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay

@Preview
@Composable
fun OceanCarouselPreview() {
    val tucano = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCYLQmqlADnQuH6Of-4GBuuwXrZj16UEM2Bqu_faLa-Q&s"

    Column {
        OceanCarousel(
            listOf(tucano, tucano, tucano),
            showPageIndicator = true,
            autoCycle = true,
            autoCycleTime = 1500
        )

        OceanCarousel(
            listOf(tucano, tucano, tucano),
            showPageIndicator = true,
            autoCycle = true,
            autoCycleTime = 1500,
            initialPage = 1
        )

        OceanCarousel(
            listOf(tucano, tucano, tucano),
            showPageIndicator = true,
            autoCycle = true,
            autoCycleTime = 1500,
            initialPage = 2
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OceanCarousel(
    items: List<String>,
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
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % items.size
                )
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
                )
            } else {
                GlideImage(
                    imageModel = { items[it] },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
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