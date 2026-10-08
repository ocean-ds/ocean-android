package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.list.OceanTransactionListExpandable
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Density (Figma 26804-18127): Compact changes only the top/bottom padding — 16 → 8 on top-level
 * rows (row 16dp shorter), 12 → 8 around the children's content (8dp shorter); skeleton follows.
 */
@RunWith(RobolectricTestRunner::class)
class TransactionListDensityTest {

    @get:Rule val composeTestRule = createComposeRule()

    private fun heights(item: @Composable (TransactionListDensity) -> Unit): Pair<Dp, Dp> {
        composeTestRule.setContent {
            Column(Modifier.width(360.dp)) {
                Box(Modifier.testTag("default")) { item(TransactionListDensity.Default) }
                Box(Modifier.testTag("compact")) { item(TransactionListDensity.Compact) }
            }
        }
        fun h(tag: String) = composeTestRule.onNodeWithTag(tag).getUnclippedBoundsInRoot().height
        return h("default") to h("compact")
    }

    private fun assertDiff(expected: Dp, item: @Composable (TransactionListDensity) -> Unit) {
        val (default, compact) = heights(item)
        assertEquals("default=$default compact=$compact", expected.value, (default - compact).value, 0.5f)
    }

    @Test
    fun readOnlyCompactIs16dpShorter() = assertDiff(16.dp) {
        OceanTransactionListReadOnly(content = Samples.content, amount = Samples.amount, icon = Samples.icon, density = it)
    }

    @Test
    fun readOnlyLoadingSkeletonFollows() = assertDiff(16.dp) {
        OceanTransactionListReadOnly(
            content = Samples.content,
            icon = Samples.icon,
            state = OceanTransactionListState.Loading,
            density = it
        )
    }

    @Test
    fun actionCompactIs16dpShorter() = assertDiff(16.dp) {
        OceanTransactionListAction(content = Samples.content, amount = Samples.amount, density = it, onClick = {})
    }

    @Test
    fun selectableCompactIs16dpShorter() = assertDiff(16.dp) {
        OceanTransactionListSelectable(
            content = Samples.content,
            amount = Samples.amount,
            selected = false,
            onSelectedChange = {},
            density = it
        )
    }

    @Test
    fun expandableParentCompactIs16dpShorter() = assertDiff(16.dp) {
        OceanTransactionListExpandable(
            content = ContentListStyle.Inverted(title = "Parent", description = "Parent description"),
            amount = Samples.amount,
            density = it
        )
    }

    @Test
    fun childrenCompactIs8dpShorter() {
        assertDiff(8.dp) {
            OceanChildTransactionListReadOnly(content = Samples.content, amount = Samples.amount, icon = Samples.icon, density = it)
        }
    }

    @Test
    fun childActionCompactIs8dpShorter() = assertDiff(8.dp) {
        OceanChildTransactionListAction(content = Samples.content, amount = Samples.amount, density = it, onClick = {})
    }

    @Test
    fun childLoadingSkeletonMainPaddingGoesTo8dp() = assertDiff(16.dp) {
        OceanChildTransactionListReadOnly(
            content = Samples.content,
            icon = Samples.icon,
            state = OceanTransactionListState.Loading,
            density = it
        )
    }

    @Test
    fun expandableChildrenFollowTheExpandableDensity() {
        val child = OceanTransactionListChildItem(content = Samples.content, amount = Samples.amount)
        // parent -16, each of the 2 children -8
        assertDiff(32.dp) {
            OceanTransactionListExpandable(
                content = ContentListStyle.Inverted(title = "Parent", description = "Parent description"),
                items = listOf(child, child),
                startExpanded = true,
                density = it
            )
        }
    }
}
