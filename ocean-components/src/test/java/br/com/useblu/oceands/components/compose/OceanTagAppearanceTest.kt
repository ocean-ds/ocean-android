package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanTagAppearance
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.utils.OceanIcons
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanTagAppearanceTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun defaultAppearanceIsDefault() {
        val style = OceanTagStyle.Default(
            label = "Paid",
            layout = OceanTagLayout.Medium()
        )

        assertEquals(OceanTagAppearance.Default, style.appearance)
    }

    @Test
    fun translucentColorsMatchTokensForAllSupportedTypes() {
        val actual = mutableMapOf<OceanTagType, TagColors>()
        val expected = mutableMapOf<OceanTagType, TagColors>()

        composeTestRule.setContent {
            actual[OceanTagType.Positive] = getTranslucentColors(OceanTagType.Positive)
            expected[OceanTagType.Positive] = TagColors(
                background = OceanColors.statusPositiveDown.copy(alpha = 0.24f),
                text = OceanColors.interfaceLightPure,
                icon = OceanColors.statusPositiveUp
            )
            actual[OceanTagType.Warning] = getTranslucentColors(OceanTagType.Warning)
            expected[OceanTagType.Warning] = TagColors(
                background = OceanColors.statusWarningDown.copy(alpha = 0.24f),
                text = OceanColors.interfaceLightPure,
                icon = OceanColors.statusWarningUp
            )
            actual[OceanTagType.Negative] = getTranslucentColors(OceanTagType.Negative)
            expected[OceanTagType.Negative] = TagColors(
                background = OceanColors.statusNegativeDown.copy(alpha = 0.24f),
                text = OceanColors.interfaceLightPure,
                icon = OceanColors.statusNegativeUp
            )
            actual[OceanTagType.Complementary] = getTranslucentColors(OceanTagType.Complementary)
            expected[OceanTagType.Complementary] = TagColors(
                background = OceanColors.complementaryDown.copy(alpha = 0.24f),
                text = OceanColors.interfaceLightPure,
                icon = OceanColors.complementaryUp
            )
            actual[OceanTagType.Neutral] = getTranslucentColors(OceanTagType.Neutral)
            expected[OceanTagType.Neutral] = TagColors(
                background = OceanColors.interfaceDarkUp.copy(alpha = 0.24f),
                text = OceanColors.interfaceLightPure,
                icon = OceanColors.interfaceLightPure
            )
            actual[OceanTagType.NeutralPrimary] = getTranslucentColors(OceanTagType.NeutralPrimary)
            expected[OceanTagType.NeutralPrimary] = TagColors(
                background = OceanColors.brandPrimaryUp.copy(alpha = 0.24f),
                text = OceanColors.interfaceDarkDeep,
                icon = OceanColors.brandPrimaryDown
            )
        }

        assertEquals(expected, actual)
    }

    @Test
    fun translucentMediumTagKeepsTwentyDpHeight() {
        composeTestRule.setContent {
            Box(modifier = Modifier.testTag("translucent")) {
                OceanTag(
                    style = OceanTagStyle.Default(
                        label = "Paid",
                        layout = OceanTagLayout.Medium(icon = OceanIcons.CHECK_CIRCLE_SOLID),
                        type = OceanTagType.Positive,
                        appearance = OceanTagAppearance.Translucent
                    )
                )
            }
        }

        composeTestRule.onNodeWithTag("translucent").assertHeightIsEqualTo(20.dp)
    }

    @Test
    fun unsupportedStylesIgnoreTranslucentAppearance() {
        val mediumStyle = translucentStyle(OceanTagType.Positive, OceanTagLayout.Medium())

        assertTrue(shouldUseTranslucentAppearance(mediumStyle, mediumStyle.layout, enabled = true))
        assertFalse(
            shouldUseTranslucentAppearance(
                mediumStyle,
                OceanTagLayout.Small(),
                enabled = true
            )
        )
        assertFalse(
            shouldUseTranslucentAppearance(
                mediumStyle,
                OceanTagLayout.Corner(),
                enabled = true
            )
        )
        assertFalse(shouldUseTranslucentAppearance(mediumStyle, mediumStyle.layout, enabled = false))

        listOf(
            OceanTagType.Important,
            OceanTagType.Highlight,
            OceanTagType.HighlightComplementary
        ).forEach { type ->
            val style = translucentStyle(type, OceanTagLayout.Medium())
            assertFalse(shouldUseTranslucentAppearance(style, style.layout, enabled = true))
        }
    }

    @Composable
    private fun getTranslucentColors(type: OceanTagType) = TagColors(
        background = getTranslucentBackgroundColor(type),
        text = getTranslucentTextColor(type),
        icon = getTranslucentIconColor(type)
    )

    private fun translucentStyle(type: OceanTagType, layout: OceanTagLayout) =
        OceanTagStyle.Default(
            label = "Tag",
            layout = layout,
            type = type,
            appearance = OceanTagAppearance.Translucent
        )

    private data class TagColors(
        val background: Color,
        val text: Color,
        val icon: Color
    )
}
