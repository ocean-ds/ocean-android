package br.com.useblu.oceands.components.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanEyebrowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rendersLowercaseTextInUpperCase() {
        composeTestRule.setContent {
            OceanEyebrow(text = "categoria")
        }

        composeTestRule.onNodeWithText("CATEGORIA").assertIsDisplayed()
    }

    @Test
    fun isIdempotentWhenTextIsAlreadyUpperCase() {
        composeTestRule.setContent {
            OceanEyebrow(text = "DESTAQUE")
        }

        composeTestRule.onNodeWithText("DESTAQUE").assertIsDisplayed()
    }
}
