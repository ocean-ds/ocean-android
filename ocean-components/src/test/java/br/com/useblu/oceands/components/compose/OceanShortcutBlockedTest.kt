package br.com.useblu.oceands.components.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.useblu.oceands.utils.OceanIcons
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Covers the [OceanShortcut] `blocked` contract: it draws the lock and keeps the item
 * clickable, because the click is what opens the explanation of why the function is
 * restricted. `disabled` is the inert state, and stays inert.
 */
@RunWith(RobolectricTestRunner::class)
class OceanShortcutBlockedTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun blockedShortcutDeliversTheClick() {
        var clicks = 0

        composeTestRule.setContent {
            OceanShortcut(
                label = "Antecipar vendas",
                icon = OceanIcons.PLACEHOLDER_OUTLINE,
                blocked = true,
                action = { clicks++ }
            )
        }

        composeTestRule.onNodeWithText("Antecipar vendas").assertIsDisplayed()
        composeTestRule.onNodeWithText("Antecipar vendas").performClick()

        assertEquals(1, clicks)
    }

    @Test
    fun disabledShortcutDoesNotDeliverTheClick() {
        var clicks = 0

        composeTestRule.setContent {
            OceanShortcut(
                label = "Antecipar vendas",
                icon = OceanIcons.PLACEHOLDER_OUTLINE,
                disabled = true,
                action = { clicks++ }
            )
        }

        composeTestRule.onNodeWithText("Antecipar vendas").performClick()

        assertEquals(0, clicks)
    }

    @Test
    fun blockedAndDisabledTogetherStayInert() {
        var clicks = 0

        composeTestRule.setContent {
            OceanShortcut(
                label = "Antecipar vendas",
                icon = OceanIcons.PLACEHOLDER_OUTLINE,
                blocked = true,
                disabled = true,
                action = { clicks++ }
            )
        }

        composeTestRule.onNodeWithText("Antecipar vendas").performClick()

        assertEquals(0, clicks)
    }

    @Test
    fun shortcutWithoutActionStaysInert() {
        composeTestRule.setContent {
            OceanShortcut(
                label = "Extrato",
                icon = OceanIcons.PLACEHOLDER_OUTLINE,
                blocked = true
            )
        }

        composeTestRule.onNodeWithText("Extrato").assertIsDisplayed()
        composeTestRule.onNodeWithText("Extrato").performClick()
    }
}
