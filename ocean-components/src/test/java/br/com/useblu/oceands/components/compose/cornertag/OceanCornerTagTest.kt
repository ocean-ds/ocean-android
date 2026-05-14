package br.com.useblu.oceands.components.compose.cornertag

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanCornerTagTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun rendersLabelText() {
        composeTestRule.setContent {
            OceanCornerTag(label = "Recomendado")
        }

        composeTestRule.onNodeWithText("Recomendado").assertIsDisplayed()
    }

    @Test
    fun setsAccessibilityContentDescription() {
        composeTestRule.setContent {
            OceanCornerTag(label = "Novo")
        }

        composeTestRule.onNodeWithContentDescription("Novo").assertIsDisplayed()
    }

    @Test
    fun doesNotRenderWhenLabelIsBlank() {
        composeTestRule.setContent {
            OceanCornerTag(label = "")
        }

        composeTestRule.onAllNodesWithText("").assertCountEquals(0)
    }

    @Test
    fun supportsComplementaryPureColor() {
        composeTestRule.setContent {
            OceanCornerTag(
                label = "Em breve",
                color = OceanCornerTagColor.ComplementaryPure
            )
        }

        composeTestRule.onNodeWithText("Em breve").assertIsDisplayed()
    }

    @Test
    fun cornerTagStyleDefaultsToPrimaryDown() {
        val style = OceanCornerTagStyle(label = "Recomendado")
        assertEquals(OceanCornerTagColor.PrimaryDown, style.color)
        assertEquals("Recomendado", style.label)
    }

    @Test
    fun cornerTagStyleAcceptsCustomColor() {
        val style = OceanCornerTagStyle(
            label = "Mais vendido",
            color = OceanCornerTagColor.ComplementaryPure
        )
        assertEquals(OceanCornerTagColor.ComplementaryPure, style.color)
    }
}
