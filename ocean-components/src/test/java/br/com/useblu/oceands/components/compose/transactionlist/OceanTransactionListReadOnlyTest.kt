package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

// CT-1 / CA-1
@RunWith(RobolectricTestRunner::class)
class OceanTransactionListReadOnlyTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun defaultStateShowsContentAmountTagAndDivider() {
        composeTestRule.setContent {
            OceanTransactionListReadOnly(
                content = Samples.content,
                amount = Samples.amount,
                icon = Samples.icon
            )
        }

        listOf("Title", "Description", "Caption", "R$ 0,00", "Label", "Additional data").forEach {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }
        composeTestRule.onNodeWithTag(TransactionListTestTags.DIVIDER, useUnmergedTree = true).assertExists()
    }

    @Test
    fun hidesDividerWhenRequested() {
        composeTestRule.setContent {
            OceanTransactionListReadOnly(
                content = Samples.content,
                amount = Samples.amount,
                showDivider = false
            )
        }

        composeTestRule.onNodeWithTag(TransactionListTestTags.DIVIDER, useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun loadingStateShowsSkeletonInsteadOfTexts() {
        composeTestRule.setContent {
            OceanTransactionListReadOnly(
                content = Samples.content,
                amount = Samples.amount,
                icon = Samples.icon,
                state = OceanTransactionListState.Loading
            )
        }

        composeTestRule.onNodeWithTag(TransactionListTestTags.LOADING, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText("Title").assertDoesNotExist()
        composeTestRule.onNodeWithText("R$ 0,00").assertDoesNotExist()
    }

    @Test
    fun hasNoChevron() {
        composeTestRule.setContent {
            OceanTransactionListReadOnly(content = Samples.content, amount = Samples.amount)
        }

        assertEquals(
            0,
            composeTestRule.onAllNodesWithTag(TransactionListTestTags.CHEVRON, useUnmergedTree = true)
                .fetchSemanticsNodes().size
        )
    }
}
