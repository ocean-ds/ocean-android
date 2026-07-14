package br.com.useblu.oceands.components.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import br.com.useblu.oceands.model.OceanInlineTextList
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanTransactionFooterTest {

    @get:Rule val composeTestRule = createComposeRule()

    private val entries = listOf(
        OceanInlineTextList(
            label = "Pagando",
            value = "R$ 42,00",
            color = "colorInterfaceDarkPure",
            isBold = true
        )
    )

    private val firstButton = OceanButtonModel(
        text = "Continuar",
        onClick = {},
        buttonStyle = OceanButtonStyle.PrimaryMedium
    )

    @Test
    fun whenVariantOmitted_rendersDefaultContent() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                entries = entries,
                firstButton = firstButton
            )
        }

        composeTestRule.onNodeWithText("Continuar").assertIsDisplayed()
        composeTestRule.onNodeWithText("Pagando").assertIsDisplayed()
    }

    @Test
    fun whenVariantHighlight_rendersContent() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                entries = entries,
                firstButton = firstButton,
                variant = OceanTransactionFooterVariant.Highlight
            )
        }

        composeTestRule.onNodeWithText("Continuar").assertIsDisplayed()
        composeTestRule.onNodeWithText("Pagando").assertIsDisplayed()
    }

    @Test
    fun whenSectionTitleAndBottomDivider_rendersHeaderAndEntries() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                entries = listOf(
                    OceanInlineTextList(label = "Subtotal", value = "R$ 40,00"),
                    OceanInlineTextList(label = "Total", value = "R$ 42,00", isBold = true)
                ),
                firstButton = firstButton,
                sectionTitle = "Resumo",
                showBottomDivider = true
            )
        }

        composeTestRule.onNodeWithText("Resumo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Subtotal").assertIsDisplayed()
        composeTestRule.onNodeWithText("Total").assertIsDisplayed()
    }
}
