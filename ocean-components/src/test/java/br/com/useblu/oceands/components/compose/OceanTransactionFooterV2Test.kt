package br.com.useblu.oceands.components.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanTransactionFooterV2Test {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun rendersFooterRowsTotalAndActionWithoutOptionalNotice() {
        composeTestRule.setContent {
            OceanTransactionFooterV2(
                items = listOf(
                    OceanTransactionFooterV2Item(
                        content = ContentListStyle.Default(title = "Compra"),
                        amount = ContentListStyle.Amount(amount = "R$ 10,00")
                    ),
                    OceanTransactionFooterV2Item(
                        content = ContentListStyle.Default(title = "Desconto"),
                        amount = ContentListStyle.Amount(amount = "R$ 2,00")
                    )
                ),
                total = OceanTransactionFooterV2Total("Total", "R$ 8,00"),
                button = OceanButtonModel(
                    text = "Continuar",
                    onClick = {},
                    buttonStyle = OceanButtonStyle.PrimaryMedium
                ),
                type = OceanTransactionFooterV2Type.Highlight
            )
        }

        composeTestRule.onNodeWithText("Compra").assertIsDisplayed()
        composeTestRule.onNodeWithText("Desconto").assertIsDisplayed()
        composeTestRule.onNodeWithText("Total").assertIsDisplayed()
        composeTestRule.onNodeWithText("R$ 8,00").assertIsDisplayed()
        composeTestRule.onNodeWithText("Continuar").assertIsDisplayed()
        composeTestRule.onNodeWithText("Aviso").assertDoesNotExist()
    }

    @Test
    fun dropsRowsAfterTheFirstFiveAndRendersNotice() {
        val items = (1..6).map { index ->
            OceanTransactionFooterV2Item(
                content = ContentListStyle.Default(title = "Linha $index"),
                amount = ContentListStyle.Amount(amount = "R$ $index")
            )
        }

        composeTestRule.setContent {
            OceanTransactionFooterV2(
                items = items,
                total = OceanTransactionFooterV2Total("Total", "R$ 15,00"),
                button = OceanButtonModel(
                    text = "Continuar",
                    onClick = {},
                    buttonStyle = OceanButtonStyle.PrimaryMedium
                ),
                notice = "Confira os valores"
            )
        }

        composeTestRule.onNodeWithText("Confira os valores").assertIsDisplayed()
        composeTestRule.onNodeWithText("Linha 5").assertIsDisplayed()
        composeTestRule.onNodeWithText("Linha 6").assertDoesNotExist()
    }

    @Test
    fun forwardsButtonAction() {
        var clicks = 0

        composeTestRule.setContent {
            OceanTransactionFooterV2(
                items = emptyList(),
                total = OceanTransactionFooterV2Total("Total", "R$ 8,00"),
                button = OceanButtonModel(
                    text = "Continuar",
                    onClick = { clicks++ },
                    buttonStyle = OceanButtonStyle.PrimaryMedium
                )
            )
        }

        composeTestRule.onNodeWithText("Continuar").performClick()

        assertEquals(1, clicks)
    }
}
