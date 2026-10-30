package br.com.useblu.oceands.ui.compose

import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Test

class OceanBorderWidthTest {
    @Test
    fun noneHasZeroDpValue() {
        assertEquals(0.dp, OceanBorderWidth.None.value)
    }

    @Test
    fun hairlineHasOneDpValue() {
        assertEquals(1.dp, OceanBorderWidth.Hairline.value)
    }

    @Test
    fun thinHasTwoDpValue() {
        assertEquals(2.dp, OceanBorderWidth.Thin.value)
    }

    @Test
    fun thickHasFourDpValue() {
        assertEquals(4.dp, OceanBorderWidth.Thick.value)
    }

    @Test
    fun heavyHasEightDpValue() {
        assertEquals(8.dp, OceanBorderWidth.Heavy.value)
    }
}
