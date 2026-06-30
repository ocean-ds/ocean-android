package br.com.useblu.oceands.ui.compose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanTextStyleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private class Resolved {
        lateinit var heading4: TextStyle
        lateinit var heading5: TextStyle
        lateinit var subtitle1: TextStyle
        lateinit var subtitle2: TextStyle
        lateinit var eyebrow: TextStyle
        lateinit var paragraph: TextStyle
        lateinit var caption: TextStyle
        lateinit var lead: TextStyle
        lateinit var baseExtraBold: FontFamily
        lateinit var baseBold: FontFamily
        var xxxs: TextUnit = TextUnit.Unspecified
        var xxs: TextUnit = TextUnit.Unspecified
        var xs: TextUnit = TextUnit.Unspecified
        var sm: TextUnit = TextUnit.Unspecified
    }

    private fun resolveStyles(): Resolved {
        val resolved = Resolved()
        composeTestRule.setContent {
            resolved.heading4 = OceanTextStyle.heading4
            resolved.heading5 = OceanTextStyle.heading5
            resolved.subtitle1 = OceanTextStyle.subtitle1
            resolved.subtitle2 = OceanTextStyle.subtitle2
            resolved.eyebrow = OceanTextStyle.eyebrow
            resolved.paragraph = OceanTextStyle.paragraph
            resolved.caption = OceanTextStyle.caption
            resolved.lead = OceanTextStyle.lead
            resolved.baseExtraBold = OceanFontFamily.BaseExtraBold
            resolved.baseBold = OceanFontFamily.BaseBold
            resolved.xxxs = OceanFontSize.xxxs
            resolved.xxs = OceanFontSize.xxs
            resolved.xs = OceanFontSize.xs
            resolved.sm = OceanFontSize.sm
        }
        composeTestRule.waitForIdle()
        return resolved
    }

    @Test
    fun heading4IsExtraBoldSizeXsWithLooseLineHeight() {
        val styles = resolveStyles()

        assertEquals(styles.baseExtraBold, styles.heading4.fontFamily)
        assertEquals(styles.xs, styles.heading4.fontSize)
        assertEquals(styles.xs * 1.32f, styles.heading4.lineHeight)
    }

    @Test
    fun heading5InheritsExtraBoldFromHeading4() {
        val styles = resolveStyles()

        assertEquals(styles.baseExtraBold, styles.heading5.fontFamily)
        assertEquals(styles.xxs, styles.heading5.fontSize)
        assertEquals(styles.xxs * 1.32f, styles.heading5.lineHeight)
    }

    @Test
    fun subtitlesUseCorrectedSizesAndLineHeight() {
        val styles = resolveStyles()

        assertEquals(styles.sm, styles.subtitle1.fontSize)
        assertEquals(styles.xs, styles.subtitle2.fontSize)
        assertEquals(styles.sm * 1.32f, styles.subtitle1.lineHeight)
        assertEquals(styles.xs * 1.32f, styles.subtitle2.lineHeight)
    }

    @Test
    fun eyebrowIsBoldTwelveSpWithLetterSpacingAndLineHeight() {
        val styles = resolveStyles()

        assertEquals(styles.baseBold, styles.eyebrow.fontFamily)
        assertEquals(styles.xxxs, styles.eyebrow.fontSize)
        assertEquals(2.16.sp, styles.eyebrow.letterSpacing)
        assertEquals(styles.xxxs * 1.5f, styles.eyebrow.lineHeight)
    }

    @Test
    fun bodyVariantsDeclareLineHeight() {
        val styles = resolveStyles()

        assertNotEquals(TextUnit.Unspecified, styles.paragraph.lineHeight)
        assertNotEquals(TextUnit.Unspecified, styles.caption.lineHeight)
        assertNotEquals(TextUnit.Unspecified, styles.lead.lineHeight)
    }
}
