package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Column
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
class OceanTransactionFooterTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun rendersFooterRowsTotalAndActionWithoutOptionalNotice() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                items = listOf(
                    OceanTransactionFooterItem(
                        content = ContentListStyle.Default(title = "Compra"),
                        amount = ContentListStyle.Amount(amount = "R$ 10,00")
                    ),
                    OceanTransactionFooterItem(
                        content = ContentListStyle.Default(title = "Desconto"),
                        amount = ContentListStyle.Amount(amount = "R$ 2,00")
                    )
                ),
                total = OceanTransactionFooterTotal("Total", "R$ 8,00"),
                button = OceanButtonModel(
                    text = "Continuar",
                    onClick = {},
                    buttonStyle = OceanButtonStyle.PrimaryMedium
                ),
                type = OceanTransactionFooterType.Highlight
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
    fun rendersAllRowsAndNotice() {
        val items = (1..6).map { index ->
            OceanTransactionFooterItem(
                content = ContentListStyle.Default(title = "Linha $index"),
                amount = ContentListStyle.Amount(amount = "R$ $index")
            )
        }

        composeTestRule.setContent {
            OceanTransactionFooter(
                items = items,
                total = OceanTransactionFooterTotal("Total", "R$ 15,00"),
                button = OceanButtonModel(
                    text = "Continuar",
                    onClick = {},
                    buttonStyle = OceanButtonStyle.PrimaryMedium
                ),
                notice = "Confira os valores"
            )
        }

        composeTestRule.onNodeWithText("Confira os valores").assertIsDisplayed()
        (1..6).forEach { index ->
            composeTestRule.onNodeWithText("Linha $index").assertIsDisplayed()
        }
    }

    @Test
    fun forwardsButtonAction() {
        var clicks = 0

        composeTestRule.setContent {
            OceanTransactionFooter(
                items = emptyList(),
                total = OceanTransactionFooterTotal("Total", "R$ 8,00"),
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

    @Test
    fun supportsLegacyAndTransactionFooterOverloads() {
        composeTestRule.setContent {
            Column {
                OceanTransactionFooter(
                    entries = emptyList(),
                    firstButton = OceanButtonModel(
                        text = "Legado",
                        onClick = {},
                        buttonStyle = OceanButtonStyle.PrimaryMedium
                    )
                )
                OceanTransactionFooter(
                    items = emptyList(),
                    total = OceanTransactionFooterTotal("Total", "R$ 8,00"),
                    button = OceanButtonModel(
                        text = "Transaction 2.0",
                        onClick = {},
                        buttonStyle = OceanButtonStyle.PrimaryMedium
                    )
                )
            }
        }

        composeTestRule.onNodeWithText("Legado").assertIsDisplayed()
        composeTestRule.onNodeWithText("Transaction 2.0").assertIsDisplayed()
    }
}
