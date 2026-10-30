package br.com.useblu.oceands.ui.compose

import org.junit.Assert.assertEquals
import org.junit.Test

class OceanOpacityTest {
    @Test
    fun semiopaqueHasPointEightValue() {
        assertEquals(0.8f, OceanOpacity.Semiopaque.value, 0f)
    }

    @Test
    fun intenseHasPointSixFourValue() {
        assertEquals(0.64f, OceanOpacity.Intense.value, 0f)
    }

    @Test
    fun mediumHasPointFourValue() {
        assertEquals(0.4f, OceanOpacity.Medium.value, 0f)
    }

    @Test
    fun lightHasPointOneSixValue() {
        assertEquals(0.16f, OceanOpacity.Light.value, 0f)
    }

    @Test
    fun semitransparentHasPointZeroEightValue() {
        assertEquals(0.08f, OceanOpacity.Semitransparent.value, 0f)
    }
}
