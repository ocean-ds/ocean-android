package br.com.useblu.oceands.components.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanTagCornerTest {

    @get:Rule val composeTestRule = createComposeRule()

    private val cornerTextStyle = TextStyle(
        fontFamily = OceanFontFamily.BaseExtraBold,
        fontSize = 10.sp
    )

    @Test
    fun rendersWithCornerLayoutAndHighlightType() {
        composeTestRule.setContent {
            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Recomendado",
                    layout = OceanTagLayout.Corner(),
                    type = OceanTagType.Highlight,
                    textStyle = cornerTextStyle
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
                    type = OceanTagType.HighlightComplementary,
                    textStyle = cornerTextStyle
                )
            )
        }

        composeTestRule.onNodeWithText("Em breve").assertIsDisplayed()
    }

    @Test
    fun defaultMediumTagRendersWithoutTextStyleOverride() {
        composeTestRule.setContent {
            OceanTag(
                style = OceanTagStyle.Default(
                    label = "Default",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Positive
                )
            )
        }

        composeTestRule.onNodeWithText("Default").assertIsDisplayed()
    }
}
