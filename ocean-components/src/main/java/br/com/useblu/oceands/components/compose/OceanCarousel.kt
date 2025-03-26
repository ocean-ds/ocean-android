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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.OceanCarouselItem
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.borderRadius
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay

@Preview
@Composable
fun OceanCarouselPreview() {
    val tucano = "https://static.mundoeducacao.uol.com.br/mundoeducacao/2022/06/tucano-toco.jpg"
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

        OceanCarousel(
            carouselItems,
            showPageIndicator = true,
            autoCycle = false,
            autoCycleTime = 1500
        )

        OceanCarousel(
            items = carouselItems,
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
    items: List<OceanCarouselItem>,
    modifier: Modifier = Modifier,
    showPageIndicator: Boolean = true,
    autoCycle: Boolean = false,
    autoCycleTime: Long = 3000,
    initialPage: Int = 0
) {
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { items.size }
    )

    LaunchedEffect(key1 = autoCycle) {
        if (autoCycle) {
            while (true) {
                runCatching {
                    delay(autoCycleTime)
                    pagerState.animateScrollToPage(
                        page = (pagerState.currentPage + 1) % items.size
                    )
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = OceanColors.interfaceLightPure)
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = OceanSpacing.xs),
            pageSpacing = 8.dp,
            modifier = Modifier.height(140.dp)
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
                GlideImage(
                    imageModel = { items[page].url },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.Center
                    ),
                    modifier = Modifier
                        .borderRadius(OceanBorderRadius.MD.allCorners)
                        .clickable { items[page].action() },
                    failure = {
                        Image(
                            painter = painterResource(id = R.drawable.image_placeholder),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
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
