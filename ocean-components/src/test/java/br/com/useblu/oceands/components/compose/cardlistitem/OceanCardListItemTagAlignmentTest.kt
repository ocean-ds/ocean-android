package br.com.useblu.oceands.components.compose.cardlistitem

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import br.com.useblu.oceands.components.compose.OceanTagLayout
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemType
import br.com.useblu.oceands.model.OceanTagType
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Cobre a prop [OceanCardListItemTagAlignment] — em especial os valores ABOVE e BELOW
 * acrescentados pelo MR-554, e a não-regressão dos valores preexistentes (RN-01).
 */
@RunWith(RobolectricTestRunner::class)
class OceanCardListItemTagAlignmentTest {

    @get:Rule val composeTestRule = createComposeRule()

    private val tagLabel = "3x sem acréscimo"

    private val tagStyle = OceanTagStyle.Default(
        label = tagLabel,
        layout = OceanTagLayout.Medium(),
        type = OceanTagType.Positive
    )

    // CT-16 — não-regressão: os valores preexistentes seguem renderizando a tag

    @Test
    fun rendersTagWhenAlignmentIsOmitted() {
        composeTestRule.setContent {
            OceanCardListItem(
                title = "Title",
                description = "Description",
                tagStyle = tagStyle
            )
        }

        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText(tagLabel).assertIsDisplayed()
    }

    @Test
    fun rendersTagWithStartAlignment() {
        composeTestRule.setContent {
            OceanCardListItem(
                title = "Title",
                tagStyle = tagStyle,
                tagAlignment = OceanCardListItemTagAlignment.START
            )
        }

        composeTestRule.onNodeWithText(tagLabel).assertIsDisplayed()
    }

    @Test
    fun rendersTagWithEndAlignment() {
        composeTestRule.setContent {
            OceanCardListItem(
                title = "Title",
                tagStyle = tagStyle,
                tagAlignment = OceanCardListItemTagAlignment.END
            )
        }

        composeTestRule.onNodeWithText(tagLabel).assertIsDisplayed()
    }

    // CT-17 — valores novos

    @Test
    fun rendersTagWithAboveAlignment() {
        composeTestRule.setContent {
            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                tagStyle = tagStyle,
                tagAlignment = OceanCardListItemTagAlignment.ABOVE
            )
        }

        composeTestRule.onNodeWithText(tagLabel).assertIsDisplayed()
        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Description").assertIsDisplayed()
        composeTestRule.onNodeWithText("Caption").assertIsDisplayed()
    }

    @Test
    fun rendersTagWithBelowAlignment() {
        composeTestRule.setContent {
            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                tagStyle = tagStyle,
                tagAlignment = OceanCardListItemTagAlignment.BELOW
            )
        }

        composeTestRule.onNodeWithText(tagLabel).assertIsDisplayed()
        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Caption").assertIsDisplayed()
    }

    @Test
    fun rendersTagAboveWhenSelectable() {
        composeTestRule.setContent {
            OceanCardListItem(
                title = "Title",
                description = "Description",
                tagStyle = tagStyle,
                tagAlignment = OceanCardListItemTagAlignment.ABOVE,
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Radiobutton,
                    didUpdate = {}
                )
            )
        }

        composeTestRule.onNodeWithText(tagLabel).assertIsDisplayed()
        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
    }

    // CT-18 — posição informada sem tag não deve renderizar nada de tag

    @Test
    fun rendersNoTagWhenTagStyleIsNull() {
        composeTestRule.setContent {
            OceanCardListItem(
                title = "Title",
                description = "Description",
                tagStyle = null,
                tagAlignment = OceanCardListItemTagAlignment.ABOVE
            )
        }

        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText(tagLabel).assertDoesNotExist()
    }
}
