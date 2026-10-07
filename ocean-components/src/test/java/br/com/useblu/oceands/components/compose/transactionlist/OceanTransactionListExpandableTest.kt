package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.useblu.oceands.components.compose.ContentListSize
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.list.OceanTransactionListExpandable
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

// CT-4 / CA-4
@RunWith(RobolectricTestRunner::class)
class OceanTransactionListExpandableTest {

    @get:Rule val composeTestRule = createComposeRule()

    private val children = listOf("Child 1", "Child 2", "Child 3").map {
        OceanTransactionListChildItem(
            content = ContentListStyle.Inverted(title = it, description = "Child description", size = ContentListSize.Sm),
            amount = Samples.amount.copy(size = ContentListSize.Sm),
            icon = Samples.icon
        )
    }

    @Test
    fun expandsAndCollapsesShowingChildrenAndFooter() {
        val changes = mutableListOf<Boolean>()
        composeTestRule.setContent {
            OceanTransactionListExpandable(
                content = ContentListStyle.Inverted(title = "Parent", description = "Parent description"),
                amount = Samples.amount,
                items = children,
                footerText = "Additional information",
                onExpandedChange = { changes += it }
            )
        }

        composeTestRule.onNodeWithText("Child 1").assertDoesNotExist()

        composeTestRule.onNodeWithText("Parent description").performClick()
        composeTestRule.onNodeWithText("Child 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Child 3").assertIsDisplayed()
        composeTestRule.onNodeWithText("Additional information").assertIsDisplayed()

        composeTestRule.onNodeWithText("Parent description").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Child 1").assertDoesNotExist()

        assertEquals(listOf(true, false), changes)
    }

    @Test
    fun childWithOnClickIsActionable() {
        var clicks = 0
        composeTestRule.setContent {
            OceanTransactionListExpandable(
                content = ContentListStyle.Inverted(title = "Parent", description = "Parent description"),
                items = listOf(children.first().copy(onClick = { clicks++ })),
                startExpanded = true
            )
        }

        composeTestRule.onNodeWithText("Child 1").performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun footerSlotReplacesFooterText() {
        composeTestRule.setContent {
            OceanTransactionListExpandable(
                content = ContentListStyle.Inverted(title = "Parent", description = "Parent description"),
                items = children,
                footerText = "Additional information",
                footer = { br.com.useblu.oceands.components.compose.OceanText(text = "Custom footer") },
                startExpanded = true
            )
        }

        composeTestRule.onNodeWithText("Custom footer").assertIsDisplayed()
        composeTestRule.onNodeWithText("Additional information").assertDoesNotExist()
    }

    @Test
    fun disabledParentDoesNotExpand() {
        composeTestRule.setContent {
            OceanTransactionListExpandable(
                content = ContentListStyle.Inverted(title = "Parent", description = "Parent description"),
                items = children,
                state = OceanTransactionListState.Disabled
            )
        }

        composeTestRule.onNodeWithText("Parent description").performClick()
        assertEquals(
            0,
            composeTestRule.onAllNodesWithText("Child 1").fetchSemanticsNodes().size
        )
    }
}
