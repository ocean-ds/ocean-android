package br.com.useblu.oceands.components.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.utils.OceanIcons
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Covers the [OceanStatusListItem] leadingIcon prop, mirroring the OceanCardListItem contract,
 * and the non-regression of the item without a leading icon.
 */
@RunWith(RobolectricTestRunner::class)
class OceanStatusListItemLeadingIconTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun rendersContentWhenLeadingIconIsOmitted() {
        composeTestRule.setContent {
            OceanStatusListItem(
                title = "Title",
                description = "Description",
                caption = "Caption"
            )
        }

        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Description").assertIsDisplayed()
        composeTestRule.onNodeWithText("Caption").assertIsDisplayed()
    }

    @Test
    fun rendersContentWithLeadingIcon() {
        composeTestRule.setContent {
            OceanStatusListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                leadingIcon = OceanIcons.PAGBLU_OUTLINE
            )
        }

        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Description").assertIsDisplayed()
        composeTestRule.onNodeWithText("Caption").assertIsDisplayed()
    }

    @Test
    fun rendersLeadingIconWithTagAndBadge() {
        composeTestRule.setContent {
            OceanStatusListItem(
                title = "Title",
                description = "Description",
                leadingIcon = OceanIcons.DOCUMENT_TEXT_OUTLINE,
                tagLabel = "Tag",
                tagType = OceanTagType.Warning,
                tagPosition = OceanStatusListItemTagPosition.RIGHT,
                badge = "9"
            )
        }

        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Tag").assertIsDisplayed()
        composeTestRule.onNodeWithText("9").assertIsDisplayed()
    }

    @Test
    fun rendersLeadingIconWhenReadOnly() {
        composeTestRule.setContent {
            OceanStatusListItem(
                title = "Title",
                description = "Description",
                leadingIcon = OceanIcons.PAGBLU_OUTLINE,
                isReadOnly = true
            )
        }

        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Description").assertIsDisplayed()
    }

    @Test
    fun rendersLeadingIconWhenInactive() {
        composeTestRule.setContent {
            OceanStatusListItem(
                title = "Title",
                description = "Description",
                leadingIcon = OceanIcons.PAGBLU_OUTLINE,
                isInactive = true,
                onClick = {}
            )
        }

        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Description").assertIsDisplayed()
    }

    @Test
    fun rendersLeadingIconWithContextMenuRightIcon() {
        composeTestRule.setContent {
            OceanStatusListItem(
                title = "Title",
                leadingIcon = OceanIcons.PAGBLU_OUTLINE,
                rightIconType = OceanStatusListItemRightIconType.CONTEXT_MENU,
                onClick = {},
                onClickRightIcon = {}
            )
        }

        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
    }
}
