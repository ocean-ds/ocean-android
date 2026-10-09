package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

// CT-2 / CA-2 — Type Menu (Figma 24289-64384): kebab, Active while the options are open, disabled.
@RunWith(RobolectricTestRunner::class)
class OceanTransactionListActionMenuTest {

    @get:Rule val composeTestRule = createComposeRule()

    private val menu get() = composeTestRule.onNodeWithTag(TransactionListTestTags.MENU, useUnmergedTree = true)
    private val chevron get() = composeTestRule.onNodeWithTag(TransactionListTestTags.CHEVRON, useUnmergedTree = true)

    @Test
    fun chevronIsStillTheDefaultType() {
        composeTestRule.setContent {
            OceanTransactionListAction(content = Samples.content, onClick = {})
        }

        chevron.assertExists()
        menu.assertDoesNotExist()
    }

    @Test
    fun menuTypeRendersKebabInsteadOfChevron() {
        composeTestRule.setContent {
            OceanTransactionListAction(
                content = Samples.content,
                amount = Samples.amount,
                type = OceanTransactionListActionType.Menu,
                onClick = {}
            )
        }

        menu.assertExists()
        chevron.assertDoesNotExist()
        menu.assertIsNotSelected()
    }

    @Test
    fun kebabTapCallsOnMenuClickNotOnClick() {
        var menuClicks = 0
        var rowClicks = 0
        composeTestRule.setContent {
            OceanTransactionListAction(
                content = Samples.content,
                type = OceanTransactionListActionType.Menu,
                onMenuClick = { menuClicks++ },
                onClick = { rowClicks++ }
            )
        }

        menu.performClick()
        assertEquals(1, menuClicks)
        assertEquals(0, rowClicks)

        composeTestRule.onNodeWithText("Description").performClick()
        assertEquals(1, rowClicks)
        assertEquals(1, menuClicks)
    }

    @Test
    fun menuShowsActiveWhileOpen() {
        composeTestRule.setContent {
            var open by remember { mutableStateOf(false) }
            OceanTransactionListAction(
                content = Samples.content,
                type = OceanTransactionListActionType.Menu,
                menuActive = open,
                onMenuClick = { open = !open },
                onClick = {}
            )
        }

        menu.assertIsNotSelected()
        menu.performClick()
        menu.assertIsSelected()
        menu.performClick()
        menu.assertIsNotSelected()
    }

    @Test
    fun disabledKebabDoesNotCallBackNorShowActive() {
        var menuClicks = 0
        composeTestRule.setContent {
            OceanTransactionListAction(
                content = Samples.content,
                type = OceanTransactionListActionType.Menu,
                state = OceanTransactionListState.Disabled,
                menuActive = true,
                onMenuClick = { menuClicks++ },
                onClick = {}
            )
        }

        menu.assertIsNotEnabled()
        menu.assertIsNotSelected()
        menu.performClick()
        assertEquals(0, menuClicks)
    }

    @Test
    fun loadingHidesKebab() {
        composeTestRule.setContent {
            OceanTransactionListAction(
                content = Samples.content,
                type = OceanTransactionListActionType.Menu,
                state = OceanTransactionListState.Loading,
                onClick = {}
            )
        }

        menu.assertDoesNotExist()
    }
}
