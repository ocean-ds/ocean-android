package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

// CT-3 / CA-3
@RunWith(RobolectricTestRunner::class)
class OceanTransactionListSelectableTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun checkboxTogglesOnItemTap() {
        composeTestRule.setContent {
            var selected by remember { mutableStateOf(false) }
            OceanTransactionListSelectable(
                content = Samples.content,
                amount = Samples.amount,
                selected = selected,
                onSelectedChange = { selected = it }
            )
        }

        val item = composeTestRule.onNodeWithText("Description")
        item.assertIsNotSelected()
        composeTestRule.onNodeWithText("Description").performClick()
        item.assertIsSelected()
        composeTestRule.onNodeWithText("Description").performClick()
        item.assertIsNotSelected()
    }

    @Test
    fun radioOnlySelects() {
        val values = mutableListOf<Boolean>()
        composeTestRule.setContent {
            OceanTransactionListSelectable(
                content = Samples.content,
                controller = OceanTransactionListController.Radio,
                selected = true,
                onSelectedChange = { values += it }
            )
        }

        composeTestRule.onNodeWithText("Description").performClick()

        assertEquals(listOf(true), values)
    }

    @Test
    fun indeterminateSelectsOnTap() {
        val values = mutableListOf<Boolean>()
        composeTestRule.setContent {
            OceanTransactionListSelectable(
                content = Samples.content,
                selected = false,
                indeterminate = true,
                onSelectedChange = { values += it }
            )
        }

        composeTestRule.onNodeWithText("Description").performClick()

        assertEquals(listOf(true), values)
    }

    @Test
    fun disabledDoesNotChangeSelection() {
        val values = mutableListOf<Boolean>()
        composeTestRule.setContent {
            OceanTransactionListSelectable(
                content = Samples.content,
                selected = true,
                state = OceanTransactionListState.Disabled,
                onSelectedChange = { values += it }
            )
        }

        composeTestRule.onNodeWithText("Description").performClick()
        composeTestRule.onNodeWithTag(TransactionListTestTags.CONTROL, useUnmergedTree = true).assertExists()

        assertTrue(values.isEmpty())
    }
}
