package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.components.compose.ContentListSize
import br.com.useblu.oceands.components.compose.ContentListStyle
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

// CT-5 / CA-5
@RunWith(RobolectricTestRunner::class)
class OceanChildTransactionListTest {

    @get:Rule val composeTestRule = createComposeRule()

    private fun assertLines(above: Boolean, below: Boolean) {
        val lineAbove = composeTestRule.onNodeWithTag(TransactionListTestTags.LINE_ABOVE, useUnmergedTree = true)
        val lineBelow = composeTestRule.onNodeWithTag(TransactionListTestTags.LINE_BELOW, useUnmergedTree = true)
        if (above) lineAbove.assertExists() else lineAbove.assertDoesNotExist()
        if (below) lineBelow.assertExists() else lineBelow.assertDoesNotExist()
    }

    private fun renderReadOnly(position: OceanTransactionListPosition) {
        composeTestRule.setContent {
            OceanChildTransactionListReadOnly(
                content = Samples.content,
                amount = Samples.amount,
                icon = Samples.icon,
                position = position
            )
        }
    }

    @Test
    fun standaloneHasNoTimeline() {
        renderReadOnly(OceanTransactionListPosition.Standalone)
        assertLines(above = false, below = false)
    }

    @Test
    fun firstHasLineBelowOnly() {
        renderReadOnly(OceanTransactionListPosition.First)
        assertLines(above = false, below = true)
    }

    @Test
    fun middleHasBothLines() {
        renderReadOnly(OceanTransactionListPosition.Middle)
        assertLines(above = true, below = true)
    }

    @Test
    fun lastHasLineAboveOnly() {
        renderReadOnly(OceanTransactionListPosition.Last)
        assertLines(above = true, below = false)
    }

    @Test
    fun readOnlyHasNoChevron() {
        renderReadOnly(OceanTransactionListPosition.Middle)
        composeTestRule.onNodeWithTag(TransactionListTestTags.CHEVRON, useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun actionHasChevronAndCallsOnClick() {
        var clicks = 0
        composeTestRule.setContent {
            OceanChildTransactionListAction(
                content = Samples.content,
                amount = Samples.amount,
                icon = Samples.icon,
                position = OceanTransactionListPosition.Middle,
                onClick = { clicks++ }
            )
        }

        composeTestRule.onNodeWithTag(TransactionListTestTags.CHEVRON, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText("Description").performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun disabledActionDoesNotCallOnClick() {
        var clicks = 0
        composeTestRule.setContent {
            OceanChildTransactionListAction(
                content = Samples.content,
                state = OceanTransactionListState.Disabled,
                onClick = { clicks++ }
            )
        }

        composeTestRule.onNodeWithText("Description").performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun childrenDefaultToSmContentAndAmount() {
        composeTestRule.setContent {
            Column {
                OceanChildTransactionListReadOnly(content = Samples.content, amount = Samples.amount.copy(amount = "R$ 1,00"))
                OceanChildTransactionListAction(
                    content = ContentListStyle.Inverted(title = "Action title", description = "Action description"),
                    amount = Samples.amount.copy(amount = "R$ 2,00"),
                    onClick = {}
                )
            }
        }

        assertEquals(12.sp, composeTestRule.onNodeWithText("Title", useUnmergedTree = true).textStyle().fontSize)
        assertEquals(14.sp, composeTestRule.onNodeWithText("Description", useUnmergedTree = true).textStyle().fontSize)
        assertEquals(14.sp, composeTestRule.onNodeWithText("R$ 1,00", useUnmergedTree = true).textStyle().fontSize)
        assertEquals(12.sp, composeTestRule.onNodeWithText("Action title", useUnmergedTree = true).textStyle().fontSize)
        assertEquals(14.sp, composeTestRule.onNodeWithText("R$ 2,00", useUnmergedTree = true).textStyle().fontSize)
    }

    @Test
    fun childrenKeepAnExplicitMdSize() {
        composeTestRule.setContent {
            OceanChildTransactionListReadOnly(
                content = Samples.content.copy(size = ContentListSize.Md),
                amount = Samples.amount.copy(size = ContentListSize.Md)
            )
        }

        assertEquals(14.sp, composeTestRule.onNodeWithText("Title", useUnmergedTree = true).textStyle().fontSize)
        assertEquals(16.sp, composeTestRule.onNodeWithText("Description", useUnmergedTree = true).textStyle().fontSize)
        assertEquals(16.sp, composeTestRule.onNodeWithText("R$ 0,00", useUnmergedTree = true).textStyle().fontSize)
    }

    @Test
    fun positionIsDerivedFromIndex() {
        assertEquals(OceanTransactionListPosition.Standalone, OceanTransactionListPosition.of(0, 1))
        assertEquals(OceanTransactionListPosition.First, OceanTransactionListPosition.of(0, 3))
        assertEquals(OceanTransactionListPosition.Middle, OceanTransactionListPosition.of(1, 3))
        assertEquals(OceanTransactionListPosition.Last, OceanTransactionListPosition.of(2, 3))
    }
}
