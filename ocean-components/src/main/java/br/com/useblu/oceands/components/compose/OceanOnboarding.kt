package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.compose.OceanOnboardingPageModel
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import kotlinx.coroutines.launch


@Preview
@Composable
fun OceanOnboardingPreview() {
    val pages = listOf(
        OceanOnboardingPageModel(
            image = R.drawable.ocean_icon_paper_clip_solid,
            title = "Title 1",
            subtitle = "Subtitle 1"
        ),
        OceanOnboardingPageModel(
            image = R.drawable.ocean_icon_paper_clip_solid,
            title = "Title 2",
            subtitle = "Subtitle 2"
        ),
        OceanOnboardingPageModel(
            image = R.drawable.ocean_icon_paper_clip_solid,
            title = "Title 3",
            subtitle = "Subtitle 3"
        )
    )

    OceanOnboardingPage(
        pages = pages,
        finishButtonLabel = "Registrar chave",
        finishButtonAction = {}
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OceanOnboardingPage(
    pages: List<OceanOnboardingPageModel>,
    finishButtonLabel: String,
    finishButtonAction: () -> Unit
) {
    fun PagerState.isLastPage(): Boolean {
        return currentPage == pages.size - 1
    }
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        HorizontalPager(
            pageCount = pages.size,
            state = pagerState,
            modifier = Modifier.background(color = OceanColors.interfaceLightPure)
        ) {
            OceanOnboardingPage(page = pages[it])
        }

        PageIndicator(pages, pagerState)

        OceanButton(
            text = if (pagerState.isLastPage()) finishButtonLabel else stringResource(R.string.button_advance),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            buttonStyle = OceanButtonStyle.PrimaryMedium,
            onClick = {
                if (pagerState.isLastPage()) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    finishButtonAction()
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PageIndicator(
    pages: List<OceanOnboardingPageModel>,
    pagerState: PagerState
) {
    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pages.size) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(20.dp)
            )
        }
    }
}

@Composable
private fun OceanOnboardingPage(
    page: OceanOnboardingPageModel
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = page.image), contentDescription = null)


        OceanSpacing.StackSM()

        Text(
            text = page.title,
            style = OceanTextStyle.heading4
        )

        OceanSpacing.StackXXS()

        Text(text = page.subtitle, style = OceanTextStyle.description)
    }
}