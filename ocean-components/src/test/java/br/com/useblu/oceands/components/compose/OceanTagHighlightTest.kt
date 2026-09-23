package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanTagHighlightTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun highlightDefaultsToMediumLayout() {
        val style = OceanTagStyle.Highlight(label = "Novo", type = OceanTagType.Highlight)

        assertEquals(OceanTagLayout.Medium(), style.layout)
        assertEquals(12.sp, (style.layout as OceanTagLayout.Medium).fontSize)
    }

    @Test
    fun highlightMediumHasSameHeightAsDefaultMedium() {
        composeTestRule.setContent {
            Box(modifier = Modifier.testTag("highlight")) {
                OceanTag(
                    style = OceanTagStyle.Highlight(
                        label = "3x sem acréscimo",
                        type = OceanTagType.Highlight
                    )
                )
            }
            Box(modifier = Modifier.testTag("default")) {
                OceanTag(
                    style = OceanTagStyle.Default(
                        label = "Pagamento agendado",
                        layout = OceanTagLayout.Medium(),
                        type = OceanTagType.Warning
                    )
                )
            }
        }

        composeTestRule.onNodeWithText("3x sem acréscimo").assertIsDisplayed()
        composeTestRule.onNodeWithTag("highlight").assertHeightIsEqualTo(20.dp)
        composeTestRule.onNodeWithTag("default").assertHeightIsEqualTo(20.dp)
    }

    @Test
    fun highlightSmallKeepsSmallHeight() {
        composeTestRule.setContent {
            Box(modifier = Modifier.testTag("highlight")) {
                OceanTag(
                    style = OceanTagStyle.Highlight(
                        label = "Novo",
                        type = OceanTagType.Important,
                        layout = OceanTagLayout.Small()
                    )
                )
            }
        }

        composeTestRule.onNodeWithTag("highlight").assertHeightIsEqualTo(16.dp)
    }

    @Test
    fun highlightLongLabelStaysOnOneLine() {
        composeTestRule.setContent {
            Box(modifier = Modifier.width(80.dp).testTag("narrow")) {
                OceanTag(
                    style = OceanTagStyle.Highlight(
                        label = "Possível bloqueio de vendas",
                        type = OceanTagType.Highlight
                    )
                )
            }
        }

        composeTestRule.onNodeWithTag("narrow").assertHeightIsEqualTo(20.dp)
    }

    @Test
    fun highlightNeutralUsesBrandPrimaryDown() {
        var neutralMatchesDown = false
        var importantMatchesCoral = false

        composeTestRule.setContent {
            neutralMatchesDown =
                getBackgroundColor(OceanTagType.Highlight) == OceanColors.brandPrimaryDown
            importantMatchesCoral =
                getBackgroundColor(OceanTagType.Important) == OceanColors.highlightPure
        }

        assertTrue(neutralMatchesDown)
        assertTrue(importantMatchesCoral)
    }

    @Test
    fun complementaryTextUsesComplementaryDeep() {
        var textMatchesDeep = false
        var backgroundKeepsPureTint = false

        composeTestRule.setContent {
            textMatchesDeep =
                getTextColor(OceanTagType.Complementary) == OceanColors.complementaryDeep
            val expectedBackground = OceanColors.complementaryPure.copy(alpha = 0.08f)
            backgroundKeepsPureTint =
                getBackgroundColor(OceanTagType.Complementary) == expectedBackground
        }

        assertTrue(textMatchesDeep)
        assertTrue(backgroundKeepsPureTint)
    }

    @Test
    fun complementaryIsParsedFromPayloadToken() {
        assertEquals(OceanTagType.Complementary, OceanTagType.fromString("complementary"))
        assertEquals(OceanTagType.NeutralPrimary, OceanTagType.fromString("neutral-02"))
    }
}
