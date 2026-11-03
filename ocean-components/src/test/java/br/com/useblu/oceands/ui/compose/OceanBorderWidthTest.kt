package br.com.useblu.oceands.ui.compose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanBorderWidthTest {
    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun noneHasZeroDpValue() {
        var value = 0.dp
        composeTestRule.setContent { value = OceanBorderWidth.None.value }
        assertEquals(0.dp, value)
    }

    @Test
    fun hairlineHasOneDpValue() {
        var value = 0.dp
        composeTestRule.setContent { value = OceanBorderWidth.Hairline.value }
        assertEquals(1.dp, value)
    }

    @Test
    fun thinHasTwoDpValue() {
        var value = 0.dp
        composeTestRule.setContent { value = OceanBorderWidth.Thin.value }
        assertEquals(2.dp, value)
    }

    @Test
    fun thickHasFourDpValue() {
        var value = 0.dp
        composeTestRule.setContent { value = OceanBorderWidth.Thick.value }
        assertEquals(4.dp, value)
    }

    @Test
    fun heavyHasEightDpValue() {
        var value = 0.dp
        composeTestRule.setContent { value = OceanBorderWidth.Heavy.value }
        assertEquals(8.dp, value)
    }
}
