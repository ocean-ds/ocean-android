package br.com.useblu.oceands.components.compose

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.useblu.oceands.model.compose.OceanTextListIconItemModel
import br.com.useblu.oceands.utils.OceanIcons
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Covers the custom icon slot of [OceanShortcut] (`customIcon`) and [OceanTextListIconItem]
 * (`leadingContent`): both are opt-in (null by default, so the catalogue icon keeps being drawn),
 * the custom content is rendered in the icon's place and the rest of the component (label,
 * description, click) is untouched.
 */
@RunWith(RobolectricTestRunner::class)
class OceanCustomIconSlotTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun shortcutRendersTheCustomIconAndKeepsLabelAndClick() {
        var clicks = 0

        composeTestRule.setContent {
            OceanShortcut(
                label = "Padaria Central",
                description = "12.345.678/0001-90",
                icon = OceanIcons.PLACEHOLDER_OUTLINE,
                layout = OceanShortcutLayout.MediumVertical,
                action = { clicks++ },
                customIcon = { Text("PC") }
            )
        }

        composeTestRule.onNodeWithText("PC").assertIsDisplayed()
        composeTestRule.onNodeWithText("Padaria Central").assertIsDisplayed()
        composeTestRule.onNodeWithText("12.345.678/0001-90").assertIsDisplayed()
        composeTestRule.onNodeWithText("Padaria Central").performClick()

        assertEquals(1, clicks)
    }

    @Test
    fun shortcutModelCarriesTheCustomIcon() {
        composeTestRule.setContent {
            OceanShortcut(
                model = OceanShortcutModel(
                    label = "Padaria Central",
                    icon = OceanIcons.PLACEHOLDER_OUTLINE,
                    customIcon = { Text("PC") }
                )
            )
        }

        composeTestRule.onNodeWithText("PC").assertIsDisplayed()
    }

    @Test
    fun shortcutWithoutCustomIconDoesNotRenderAnySlotContent() {
        composeTestRule.setContent {
            OceanShortcut(
                label = "Padaria Central",
                icon = OceanIcons.PLACEHOLDER_OUTLINE
            )
        }

        composeTestRule.onNodeWithText("Padaria Central").assertIsDisplayed()
        composeTestRule.onNodeWithText("PC").assertDoesNotExist()
    }

    @Test
    fun textListIconItemRendersTheLeadingContentAndKeepsTitleAndClick() {
        var clicks = 0

        composeTestRule.setContent {
            OceanTextListIconItem(
                title = "Padaria Central",
                description = "12.345.678/0001-90",
                leadingIcon = OceanIcons.PLACEHOLDER_OUTLINE,
                onClick = { clicks++ },
                leadingContent = { Text("PC") }
            )
        }

        composeTestRule.onNodeWithText("PC").assertIsDisplayed()
        composeTestRule.onNodeWithText("Padaria Central").assertIsDisplayed()
        composeTestRule.onNodeWithText("Padaria Central").performClick()

        assertEquals(1, clicks)
    }

    @Test
    fun textListIconItemModelCarriesTheLeadingContent() {
        composeTestRule.setContent {
            OceanTextListIconItem(
                model = OceanTextListIconItemModel(
                    title = "Padaria Central",
                    leadingContent = { Text("PC") }
                )
            )
        }

        composeTestRule.onNodeWithText("PC").assertIsDisplayed()
        composeTestRule.onNodeWithText("Padaria Central").assertIsDisplayed()
    }

    @Test
    fun textListIconItemWithoutLeadingContentDoesNotRenderAnySlotContent() {
        composeTestRule.setContent {
            OceanTextListIconItem(
                title = "Padaria Central",
                leadingIcon = OceanIcons.PLACEHOLDER_OUTLINE
            )
        }

        composeTestRule.onNodeWithText("Padaria Central").assertIsDisplayed()
        composeTestRule.onNodeWithText("PC").assertDoesNotExist()
    }
}
