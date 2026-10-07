package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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
    fun positionIsDerivedFromIndex() {
        assertEquals(OceanTransactionListPosition.Standalone, OceanTransactionListPosition.of(0, 1))
        assertEquals(OceanTransactionListPosition.First, OceanTransactionListPosition.of(0, 3))
        assertEquals(OceanTransactionListPosition.Middle, OceanTransactionListPosition.of(1, 3))
        assertEquals(OceanTransactionListPosition.Last, OceanTransactionListPosition.of(2, 3))
    }
}
