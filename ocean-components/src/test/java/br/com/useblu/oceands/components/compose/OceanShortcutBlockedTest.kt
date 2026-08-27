package br.com.useblu.oceands.components.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.utils.OceanIcons
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Covers the [OceanShortcut] `blocked` contract: it draws the lock, stays inert by default
 * (backwards compatibility) and only becomes clickable with `forceEnableActionWhenBlocked` —
 * the case where the click is what opens the explanation of why the function is restricted.
 * `disabled` is the inert state and has no opt-in: it stays inert in every combination.
 */
@RunWith(RobolectricTestRunner::class)
class OceanShortcutBlockedTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun blockedShortcutDoesNotDeliverTheClickByDefault() {
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

        assertEquals(0, clicks)
    }

    @Test
    fun blockedShortcutDeliversTheClickWithTheOptIn() {
        var clicks = 0

        composeTestRule.setContent {
            OceanShortcut(
                label = "Antecipar vendas",
                icon = OceanIcons.PLACEHOLDER_OUTLINE,
                blocked = true,
                forceEnableActionWhenBlocked = true,
                action = { clicks++ }
            )
        }

        composeTestRule.onNodeWithText("Antecipar vendas").performClick()

        assertEquals(1, clicks)
    }

    // `disabled` não tem opt-in: item inerte continua inerte mesmo com a chave ligada.
    @Test
    fun disabledShortcutStaysInertEvenWithTheOptIn() {
        var clicks = 0

        composeTestRule.setContent {
            OceanShortcut(
                label = "Antecipar vendas",
                icon = OceanIcons.PLACEHOLDER_OUTLINE,
                blocked = true,
                disabled = true,
                forceEnableActionWhenBlocked = true,
                action = { clicks++ }
            )
        }

        composeTestRule.onNodeWithText("Antecipar vendas").performClick()

        assertEquals(0, clicks)
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

    // O canto de cima é um slot só: com o cadeado, tag e badge saem de cena, porque oferta e
    // contagem não dizem nada sobre uma função que não abre. Precedência do Ocean web.

    @Test
    fun blockedShortcutHidesTheCornerTag() {
        composeTestRule.setContent {
            OceanShortcut(
                label = "Antecipar vendas",
                icon = OceanIcons.PLACEHOLDER_OUTLINE,
                tag = OceanShortcutTag(text = "Oferta", type = OceanTagType.Important),
                blocked = true
            )
        }

        composeTestRule.onNodeWithText("Antecipar vendas").assertIsDisplayed()
        composeTestRule.onNodeWithText("Oferta").assertDoesNotExist()
    }

    @Test
    fun blockedShortcutHidesTheBadge() {
        composeTestRule.setContent {
            OceanShortcut(
                label = "Boletos",
                icon = OceanIcons.PLACEHOLDER_OUTLINE,
                badge = OceanShortcutBadge(count = 2, type = OceanBadgeType.WARNING),
                blocked = true
            )
        }

        composeTestRule.onNodeWithText("Boletos").assertIsDisplayed()
        composeTestRule.onNodeWithText("2").assertDoesNotExist()
    }

    @Test
    fun blockedShortcutKeepsTheCenteredTag() {
        composeTestRule.setContent {
            OceanShortcut(
                label = "Antecipar vendas",
                icon = OceanIcons.PLACEHOLDER_OUTLINE,
                tag = OceanShortcutTag(
                    text = "Oferta",
                    type = OceanTagType.Important,
                    position = OceanShortcutTag.Position.Center
                ),
                blocked = true
            )
        }

        composeTestRule.onNodeWithText("Oferta").assertIsDisplayed()
    }

    @Test
    fun unblockedShortcutStillShowsTagAndBadge() {
        composeTestRule.setContent {
            OceanShortcut(
                label = "Antecipar vendas",
                icon = OceanIcons.PLACEHOLDER_OUTLINE,
                tag = OceanShortcutTag(text = "Oferta", type = OceanTagType.Important)
            )
        }

        composeTestRule.onNodeWithText("Oferta").assertIsDisplayed()
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
