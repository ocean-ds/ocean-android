package br.com.useblu.oceands.components.compose.banner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.utils.image.OceanImageProxy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

/**
 * Covers the two review points of the Banner spec alignment:
 * the Small image width is opt-in ([OceanBannerImageSize.Proportional] stays the default so no
 * current consumer changes) and the Emphasys/Brand secondary CTA is a real [OceanButtonStyle]
 * (TertiaryInverse) instead of a hand-built button.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class OceanBannerTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun smallKindDefaultsToProportionalImageSize() {
        assertEquals(OceanBannerImageSize.Proportional, OceanBannerKind.Small().imageSize)
        assertEquals(OceanBannerImageSize.Proportional, OceanBannerKind.Small(image = null).imageSize)
    }

    @Test
    fun fixedImageSizeDefaultsToSpecWidth() {
        assertEquals(82.dp, OceanBannerImageSize.Fixed().width)
    }

    @Test
    fun proportionalImageSizeCapsWidthAtAQuarterOfTheScreen() {
        var expected: Dp = 0.dp

        composeTestRule.setContent {
            expected = LocalConfiguration.current.screenWidthDp.dp * OceanBannerImageSize.SCREEN_WIDTH_FRACTION
            ImageSizeProbe(size = OceanBannerImageSize.Proportional)
        }

        composeTestRule.onNodeWithTag(PROBE_TAG).assertWidthIsEqualTo(expected)
    }

    @Test
    fun fixedImageSizeUsesTheRequestedWidth() {
        composeTestRule.setContent {
            ImageSizeProbe(size = OceanBannerImageSize.Fixed())
        }

        composeTestRule.onNodeWithTag(PROBE_TAG).assertWidthIsEqualTo(82.dp)
    }

    @Test
    fun emphasysAndBrandSecondaryCtaUseTertiaryInverse() {
        assertEquals(OceanButtonStyle.TertiaryInverseSmall, OceanBannerStyle.Emphasys.getSecondaryButtonStyle())
        assertEquals(OceanButtonStyle.TertiaryInverseSmall, OceanBannerStyle.Brand.getSecondaryButtonStyle())
    }

    @Test
    fun otherStylesKeepTheirSecondaryCtaStyle() {
        assertEquals(OceanButtonStyle.TertiarySmall, OceanBannerStyle.Neutral.getSecondaryButtonStyle())
        assertEquals(OceanButtonStyle.TertiaryWarningSmall, OceanBannerStyle.Warning.getSecondaryButtonStyle())
        assertEquals(OceanButtonStyle.TertiaryCriticalSmall, OceanBannerStyle.Negative.getSecondaryButtonStyle())
    }

    @Test
    fun emphasysSecondaryCtaIsRenderedAndClickable() {
        var clicks = 0

        composeTestRule.setContent {
            OceanBanner(
                modifier = Modifier,
                style = OceanBannerStyle.Emphasys,
                kind = OceanBannerKind.Small(),
                title = "Oferta especial",
                description = "Condições exclusivas",
                ctaTitle = "Saiba mais",
                secondaryCtaTitle = "Agora não",
                onSecondaryCtaClick = { clicks++ }
            )
        }

        composeTestRule.onNodeWithText("Agora não").assertIsDisplayed().performClick()

        assertEquals(1, clicks)
    }

    @Test
    fun longCtasWrapToNextLineInNarrowSmallBannerWithImage() {
        composeTestRule.setContent {
            NarrowBanner(
                kind = OceanBannerKind.Small(image = testImage()),
                ctaTitle = LONG_PRIMARY,
                secondaryCtaTitle = LONG_SECONDARY
            )
        }

        assertSecondaryWrappedBelowPrimary(LONG_PRIMARY, LONG_SECONDARY)
    }

    @Test
    fun longCtasWrapToNextLineInNarrowLargeBanner() {
        composeTestRule.setContent {
            NarrowBanner(
                kind = OceanBannerKind.Large(),
                ctaTitle = LONG_PRIMARY,
                secondaryCtaTitle = LONG_SECONDARY
            )
        }

        assertSecondaryWrappedBelowPrimary(LONG_PRIMARY, LONG_SECONDARY)
    }

    @Test
    fun shortCtasStaySideBySideLeftAligned() {
        composeTestRule.setContent {
            NarrowBanner(
                kind = OceanBannerKind.Large(),
                ctaTitle = "Saiba mais",
                secondaryCtaTitle = "Agora não"
            )
        }

        val primary = composeTestRule.onNodeWithText("Saiba mais").assertIsDisplayed().getBoundsInRoot()
        val secondary = composeTestRule.onNodeWithText("Agora não").assertIsDisplayed().getBoundsInRoot()

        assertEquals(primary.top, secondary.top)
        assertTrue("secondary should sit right of primary", secondary.left > primary.right)
    }

    private fun assertSecondaryWrappedBelowPrimary(primaryText: String, secondaryText: String) {
        val primary = composeTestRule.onNodeWithText(primaryText).assertIsDisplayed().getBoundsInRoot()
        val secondary = composeTestRule.onNodeWithText(secondaryText).assertIsDisplayed().getBoundsInRoot()

        assertTrue("secondary CTA should wrap below the primary: primary=$primary secondary=$secondary", secondary.top >= primary.bottom)
        assertEquals(primary.left, secondary.left)
    }

    @Composable
    private fun NarrowBanner(
        kind: OceanBannerKind,
        ctaTitle: String,
        secondaryCtaTitle: String
    ) {
        Box(modifier = Modifier.width(NARROW_WIDTH)) {
            OceanBanner(
                modifier = Modifier,
                style = OceanBannerStyle.Emphasys,
                kind = kind,
                title = "Título",
                description = "Descrição",
                ctaTitle = ctaTitle,
                onCtaClick = {},
                secondaryCtaTitle = secondaryCtaTitle,
                onSecondaryCtaClick = {}
            )
        }
    }

    private fun testImage() = OceanImageProxy.Resource(
        resId = R.drawable.image_blocked,
        contentScale = ContentScale.FillHeight,
        alignment = Alignment.Center
    )

    @Composable
    private fun ImageSizeProbe(size: OceanBannerImageSize) {
        Box(
            modifier = Modifier
                .testTag(PROBE_TAG)
                .then(size.widthModifier())
                .fillMaxWidth()
                .height(1.dp)
        )
    }

    private companion object {
        const val PROBE_TAG = "image-size-probe"
        const val LONG_PRIMARY = "Adicionar saldo agora"
        const val LONG_SECONDARY = "Cancelar operação"
        val NARROW_WIDTH = 320.dp
    }
}
