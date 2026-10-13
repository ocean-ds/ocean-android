package br.com.useblu.oceands.components.compose

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.utils.OceanIcons
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanTransactionFooterTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun noticeIsSeparateAndOverlappedByBody() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                total = total,
                button = button,
                notice = OceanTransactionFooterNotice(description = "Pagamento aprovado")
            )
        }

        composeTestRule.onNodeWithTag("transaction-footer-notice").assertIsDisplayed()
        composeTestRule.onNodeWithTag("transaction-footer-body").assertIsDisplayed()
        val noticeBottom = composeTestRule.onNodeWithTag("transaction-footer-notice")
            .fetchSemanticsNode().boundsInRoot.bottom
        val bodyTop = composeTestRule.onNodeWithTag("transaction-footer-body")
            .fetchSemanticsNode().boundsInRoot.top
        val overlap = with(composeTestRule.density) { 16.dp.toPx() }
        assertEquals(overlap, noticeBottom - bodyTop, 1f)
    }

    @Test
    fun omitsNoticeWhenNotProvided() {
        composeTestRule.setContent {
            OceanTransactionFooter(total = total, button = button)
        }

        composeTestRule.onNodeWithTag("transaction-footer-notice").assertDoesNotExist()
        composeTestRule.onNodeWithText("Continuar").assertIsDisplayed()
    }

    @Test
    fun rendersNoticeTitleAndIconWhenProvided() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                total = total,
                button = button,
                notice = OceanTransactionFooterNotice(
                    description = "Economia aplicada",
                    title = "Você economizou",
                    icon = OceanIcons.CHECK_CIRCLE_OUTLINE
                )
            )
        }

        composeTestRule.onNodeWithTag("transaction-footer-notice-title").assertIsDisplayed()
        composeTestRule.onNodeWithTag("transaction-footer-notice-icon").assertIsDisplayed()
    }

    @Test
    fun allowsNoticeTitleAndIconToBeOmitted() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                total = total,
                button = button,
                notice = OceanTransactionFooterNotice(description = "Aviso", icon = null)
            )
        }

        composeTestRule.onNodeWithTag("transaction-footer-notice-title").assertDoesNotExist()
        composeTestRule.onNodeWithTag("transaction-footer-notice-icon").assertDoesNotExist()
    }

    @Test
    fun detailsOnlyRendersNoMainAndOneDivider() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                total = total,
                button = button,
                details = listOf(OceanTransactionFooterItem("Detalhe", "R$ 1,00"))
            )
        }
        composeTestRule.onNodeWithTag("transaction-footer-main-row").assertDoesNotExist()
        composeTestRule.onAllNodesWithTag("transaction-footer-detail-row").assertCountEquals(1)
        composeTestRule.onAllNodesWithTag("transaction-footer-divider").assertCountEquals(1)
    }

    @Test
    fun mainOnlyRendersOneDivider() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                total = total,
                button = button,
                mainItem = OceanTransactionFooterItem("Pedido", "R$ 10,00")
            )
        }
        composeTestRule.onNodeWithTag("transaction-footer-main-row").assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("transaction-footer-divider").assertCountEquals(1)
    }

    @Test
    fun mainAndDetailsRenderTwoDividers() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                total = total,
                button = button,
                mainItem = OceanTransactionFooterItem("Pedido", "R$ 10,00"),
                details = listOf(OceanTransactionFooterItem("Detalhe", "R$ 1,00"))
            )
        }
        composeTestRule.onAllNodesWithTag("transaction-footer-divider").assertCountEquals(2)
    }

    @Test
    fun totalOnlyRendersNoDividers() {
        composeTestRule.setContent {
            OceanTransactionFooter(total = total, button = button)
        }
        composeTestRule.onAllNodesWithTag("transaction-footer-divider").assertCountEquals(0)
    }

    @Test
    fun rendersSixDetailsAndTotal() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                total = total,
                button = button,
                details = (1..6).map { OceanTransactionFooterItem("Detalhe $it", "R$ $it,00") }
            )
        }

        composeTestRule.onAllNodesWithTag("transaction-footer-detail-row").assertCountEquals(6)
        (1..6).forEach { composeTestRule.onNodeWithText("Detalhe $it").assertIsDisplayed() }
        composeTestRule.onNodeWithTag("transaction-footer-total").assertIsDisplayed()
    }

    @Test
    fun forwardsButtonAction() {
        var clicks = 0
        composeTestRule.setContent {
            OceanTransactionFooter(
                total = total,
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

    @Suppress("DEPRECATION")
    @Test
    fun keepsLegacyOverloadAvailable() {
        composeTestRule.setContent {
            OceanTransactionFooter(entries = emptyList(), firstButton = button)
        }

        composeTestRule.onNodeWithText("Continuar").assertIsDisplayed()
    }

    private companion object {
        val total = OceanTransactionFooterTotal("Total", "R$ 10,00")
        val button = OceanButtonModel(
            text = "Continuar",
            onClick = {},
            buttonStyle = OceanButtonStyle.PrimaryMedium
        )
    }
}
