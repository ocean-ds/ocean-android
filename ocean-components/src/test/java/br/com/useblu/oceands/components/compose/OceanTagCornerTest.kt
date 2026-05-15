package br.com.useblu.oceands.components.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import br.com.useblu.oceands.model.OceanTagType
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanTagCornerTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun rendersWithCornerLayoutAndHighlightType() {
        composeTestRule.setContent {
            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Recomendado",
                    layout = OceanTagLayout.Corner(),
                    type = OceanTagType.Highlight
                )
            )
        }

        composeTestRule.onNodeWithText("Recomendado").assertIsDisplayed()
    }

    @Test
    fun rendersWithCornerLayoutAndHighlightComplementaryType() {
        composeTestRule.setContent {
            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Em breve",
                    layout = OceanTagLayout.Corner(),
                    type = OceanTagType.HighlightComplementary
                )
            )
        }

        composeTestRule.onNodeWithText("Em breve").assertIsDisplayed()
    }
}
