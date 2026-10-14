package br.com.useblu.oceands.components.compose

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.ContentListStyle.Amount
import br.com.useblu.oceands.components.compose.ContentListStyle.Default
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.compose.OceanTagModel
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
        val rootBounds = composeTestRule.onNodeWithTag("transaction-footer-root")
            .fetchSemanticsNode().boundsInRoot
        val noticeBounds = composeTestRule.onNodeWithTag("transaction-footer-notice")
            .fetchSemanticsNode().boundsInRoot
        val bodyBounds = composeTestRule.onNodeWithTag("transaction-footer-body")
            .fetchSemanticsNode().boundsInRoot
        val overlap = with(composeTestRule.density) { 16.dp.toPx() }
        assertEquals(overlap, noticeBounds.bottom - bodyBounds.top, 1f)
        assertEquals(
            noticeBounds.height + bodyBounds.height - overlap,
            rootBounds.height,
            1f
        )
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
                    description = "Texto de apoio do aviso.",
                    title = "Desconto aplicado",
                    icon = OceanIcons.CHECK_CIRCLE_SOLID
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
        composeTestRule.onAllNodesWithTag("transaction_list_divider").assertCountEquals(0)
    }

    @Test
    fun totalOnlyRendersNoDividers() {
        composeTestRule.setContent {
            OceanTransactionFooter(total = total, button = button)
        }
        composeTestRule.onAllNodesWithTag("transaction-footer-divider").assertCountEquals(0)
    }

    @Test
    fun leavesSixteenDpBetweenTotalAndAction() {
        composeTestRule.setContent {
            OceanTransactionFooter(total = total, button = button)
        }

        val totalBottom = composeTestRule.onNodeWithTag("transaction-footer-total")
            .fetchSemanticsNode().boundsInRoot.bottom
        val actionTop = composeTestRule.onNodeWithTag("transaction-footer-action")
            .fetchSemanticsNode().boundsInRoot.top
        val actionSpacing = with(composeTestRule.density) { 16.dp.toPx() }

        assertEquals(actionSpacing, actionTop - totalBottom, 1f)
    }

    @Test
    fun rendersOptionalInfoForMainItemAndDetails() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                total = total,
                button = button,
                mainItem = OceanTransactionFooterItem(
                    "Pedido",
                    "R$ 10,00",
                    "Pedido aprovado"
                ),
                details = listOf(
                    OceanTransactionFooterItem(
                        "Pague em",
                        "3x de R$ 10,00",
                        "sem acréscimo"
                    )
                )
            )
        }

        composeTestRule.onNodeWithText("Pedido").assertIsDisplayed()
        composeTestRule.onAllNodesWithText("R$ 10,00", substring = false).assertCountEquals(2)
        composeTestRule.onNodeWithText("Pedido aprovado").assertIsDisplayed()
        composeTestRule.onNodeWithText("Pague em").assertIsDisplayed()
        composeTestRule.onNodeWithText("3x de R$ 10,00").assertIsDisplayed()
        composeTestRule.onNodeWithText("sem acréscimo").assertIsDisplayed()
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
    fun usesDefaultDensityForMainAndCompactDensityForDetails() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                total = total,
                button = button,
                mainItem = OceanTransactionFooterItem("Main", "$10.00"),
                details = listOf(OceanTransactionFooterItem("Detail", "$1.00"))
            )
        }

        val mainHeight = composeTestRule.onNodeWithTag("transaction-footer-main-row")
            .fetchSemanticsNode().boundsInRoot.height
        val detailHeight = composeTestRule.onNodeWithTag("transaction-footer-detail-row")
            .fetchSemanticsNode().boundsInRoot.height
        val densityDifference = with(composeTestRule.density) { 16.dp.toPx() }

        assertEquals(densityDifference, mainHeight - detailHeight, 1f)
    }

    @Test
    fun rendersTransactionListAmountTypesTagsInfoAndContentDescription() {
        composeTestRule.setContent {
            OceanTransactionFooter(
                total = total,
                button = button,
                mainItem = OceanTransactionFooterItem(
                    content = Default(title = "Taxa"),
                    amount = Amount(
                        amount = "Grátis",
                        type = AmountType.Strikethrough,
                        strikethroughAmount = "3,99%"
                    )
                ),
                details = listOf(
                    OceanTransactionFooterItem(
                        content = Default(title = "Ajuste"),
                        amount = Amount(
                            amount = "R$ 0,00",
                            type = AmountType.StrikethroughNeutral,
                            strikethroughAmount = "R$ 1,00"
                        )
                    ),
                    OceanTransactionFooterItem(
                        content = Default(title = "Cashback"),
                        amount = Amount(amount = "R$ 10,00", type = AmountType.Positive)
                    ),
                    OceanTransactionFooterItem(
                        content = Default(title = "Tarifa"),
                        amount = Amount(amount = "R$ 2,00", type = AmountType.Negative)
                    ),
                    OceanTransactionFooterItem(
                        content = Default(title = "Taxa anterior"),
                        amount = Amount(amount = "R$ 2,00", type = AmountType.Inactive)
                    ),
                    OceanTransactionFooterItem(
                        content = Default(title = "Desconto"),
                        amount = Amount(
                            amount = "Grátis",
                            tag = OceanTagModel(OceanTagType.Positive, "Aplicado")
                        )
                    ),
                    OceanTransactionFooterItem(
                        content = Default(
                            title = "Parcelamento",
                            description = "Pagamento em cinco parcelas"
                        ),
                        amount = Amount(amount = "5x de R$ 250,00", additionalData = "sem acréscimo")
                    )
                )
            )
        }

        listOf(
            "Taxa",
            "3,99%",
            "Ajuste",
            "R$ 0,00",
            "R$ 1,00",
            "Cashback",
            "Tarifa",
            "Taxa anterior",
            "Aplicado",
            "Pagamento em cinco parcelas",
            "sem acréscimo"
        ).forEach { composeTestRule.onNodeWithText(it).assertIsDisplayed() }
        composeTestRule.onAllNodesWithText("Grátis", substring = false).assertCountEquals(2)
        composeTestRule.onNodeWithText("- R$ 2,00").assertIsDisplayed()
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
