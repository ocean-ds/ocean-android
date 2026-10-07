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

// CT-2 / CA-2
@RunWith(RobolectricTestRunner::class)
class OceanTransactionListActionTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun clickCallsOnClickOncePerTapAndShowsChevron() {
        var clicks = 0
        composeTestRule.setContent {
            OceanTransactionListAction(
                content = Samples.content,
                amount = Samples.amount,
                onClick = { clicks++ }
            )
        }

        composeTestRule.onNodeWithTag(TransactionListTestTags.CHEVRON, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText("Description").performClick()
        composeTestRule.onNodeWithText("Description").performClick()

        assertEquals(2, clicks)
    }

    @Test
    fun disabledDoesNotCallOnClick() {
        var clicks = 0
        composeTestRule.setContent {
            OceanTransactionListAction(
                content = Samples.content,
                amount = Samples.amount,
                state = OceanTransactionListState.Disabled,
                onClick = { clicks++ }
            )
        }

        composeTestRule.onNodeWithText("Description").performClick()

        assertEquals(0, clicks)
    }

    @Test
    fun loadingHidesChevronAndDoesNotCallOnClick() {
        var clicks = 0
        composeTestRule.setContent {
            OceanTransactionListAction(
                content = Samples.content,
                state = OceanTransactionListState.Loading,
                onClick = { clicks++ }
            )
        }

        composeTestRule.onNodeWithTag(TransactionListTestTags.CHEVRON, useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TransactionListTestTags.LOADING, useUnmergedTree = true).performClick()

        assertEquals(0, clicks)
    }
}
