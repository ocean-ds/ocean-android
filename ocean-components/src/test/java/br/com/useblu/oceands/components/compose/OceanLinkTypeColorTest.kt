package br.com.useblu.oceands.components.compose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import br.com.useblu.oceands.model.compose.OceanAlertType
import br.com.useblu.oceands.ui.compose.OceanColors
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanLinkTypeColorTest {

    @get:Rule val composeTestRule = createComposeRule()

    private fun resolve(vararg types: OceanLinkType): Map<OceanLinkType, Color> {
        val actual = mutableMapOf<OceanLinkType, Color>()
        composeTestRule.setContent {
            types.forEach { actual[it] = it.getTextColor() }
        }
        composeTestRule.waitForIdle()
        return actual
    }

    @Test
    fun positiveUsesPositiveStatusColor() {
        var expected = Color.Unspecified
        val actual = mutableMapOf<OceanLinkType, Color>()
        composeTestRule.setContent {
            expected = OceanColors.statusPositiveDeep
            actual[OceanLinkType.POSITIVE] = OceanLinkType.POSITIVE.getTextColor()
        }
        composeTestRule.waitForIdle()

        assertEquals(expected, actual[OceanLinkType.POSITIVE])
    }

    @Test
    fun negativeUsesNegativeStatusColor() {
        var expected = Color.Unspecified
        val actual = mutableMapOf<OceanLinkType, Color>()
        composeTestRule.setContent {
            expected = OceanColors.statusNegativePure
            actual[OceanLinkType.NEGATIVE] = OceanLinkType.NEGATIVE.getTextColor()
        }
        composeTestRule.waitForIdle()

        assertEquals(expected, actual[OceanLinkType.NEGATIVE])
    }

    @Test
    fun primaryKeepsBrandPrimaryColor() {
        var expected = Color.Unspecified
        val actual = mutableMapOf<OceanLinkType, Color>()
        composeTestRule.setContent {
            expected = OceanColors.brandPrimaryPure
            actual[OceanLinkType.PRIMARY] = OceanLinkType.PRIMARY.getTextColor()
        }
        composeTestRule.waitForIdle()

        assertEquals(expected, actual[OceanLinkType.PRIMARY])
    }

    @Test
    fun positiveAndNegativeDifferFromPrimary() {
        val colors = resolve(OceanLinkType.PRIMARY, OceanLinkType.POSITIVE, OceanLinkType.NEGATIVE)

        assertEquals(3, colors.values.toSet().size)
    }

    @Test
    fun labeledAlertDefaultsToPrimaryLink() {
        val type = OceanAlertType.Labeled(
            description = "d",
            link = "l"
        )

        assertEquals(OceanLinkType.PRIMARY, type.linkType)
    }
}
