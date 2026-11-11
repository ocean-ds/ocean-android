package br.com.useblu.oceands.ui.compose

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanOpacityTest {
    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun semiopaqueHasPointEightValue() {
        var value = 0f
        composeTestRule.setContent { value = OceanOpacity.Semiopaque.value }
        assertEquals(0.8f, value, 0f)
    }

    @Test
    fun intenseHasPointSixFourValue() {
        var value = 0f
        composeTestRule.setContent { value = OceanOpacity.Intense.value }
        assertEquals(0.64f, value, 0f)
    }

    @Test
    fun mediumHasPointFourValue() {
        var value = 0f
        composeTestRule.setContent { value = OceanOpacity.Medium.value }
        assertEquals(0.4f, value, 0f)
    }

    @Test
    fun lightHasPointOneSixValue() {
        var value = 0f
        composeTestRule.setContent { value = OceanOpacity.Light.value }
        assertEquals(0.16f, value, 0f)
    }

    @Test
    fun semitransparentHasPointZeroEightValue() {
        var value = 0f
        composeTestRule.setContent { value = OceanOpacity.Semitransparent.value }
        assertEquals(0.08f, value, 0f)
    }
}
