package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.OceanContentList
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.compose.OceanTagModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

/** Text/Tag overflow rule shared with ocean-web (#1275), in every item of the family. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class TransactionListOverflowTest {

    @get:Rule val composeTestRule = createComposeRule()

    private val description = "Seashell Corporation Wholesale and Distribution Ltda"
    private val caption = "Order #7182, invoice 4821, scheduled for Oct 15"
    private val value = "R$ 1.314,28"
    private val tag = "Payment scheduled for Oct 15 by bank transfer"
    private val info = "Transfer to Seashell Corporation, account 4821"

    private val content = ContentListStyle.Inverted(
        title = "Bank transfer",
        description = description,
        caption = caption
    )

    private val amount = ContentListStyle.Amount(
        amount = value,
        tag = OceanTagModel(type = OceanTagType.Warning, text = tag),
        additionalData = info
    )

    private fun render(item: @Composable () -> Unit) {
        composeTestRule.setContent {
            Column(modifier = Modifier.width(ROW_WIDTH).testTag(CONTAINER)) { item() }
        }
    }

    private fun layout(text: String): TextLayoutResult {
        val results = mutableListOf<TextLayoutResult>()
        composeTestRule.onNodeWithText(text, useUnmergedTree = true)
            .fetchSemanticsNode().config.getOrNull(SemanticsActions.GetTextLayoutResult)
            ?.action?.invoke(results)
        return results.first()
    }

    private fun SemanticsNodeInteraction.width() = getUnclippedBoundsInRoot().let { it.right - it.left }

    private fun assertOverflowRule() {
        listOf("Bank transfer", description, caption, info).forEach {
            val result = layout(it)
            assertEquals(it, 2, result.layoutInput.maxLines)
            assertEquals(it, TextOverflow.Ellipsis, result.layoutInput.overflow)
        }
        assertTrue("description ends in an ellipsis", layout(description).isLineEllipsized(1))

        val tagLayout = layout(tag)
        assertEquals(1, tagLayout.layoutInput.maxLines)
        assertEquals(TextOverflow.Ellipsis, tagLayout.layoutInput.overflow)
        assertTrue("tag ends in an ellipsis", tagLayout.isLineEllipsized(0))
        val tagNode = composeTestRule.onNodeWithText(tag, useUnmergedTree = true).fetchSemanticsNode()
        assertEquals(listOf(tag), tagNode.config[SemanticsProperties.ContentDescription])

        val valueLayout = layout(value)
        assertEquals(1, valueLayout.layoutInput.maxLines)
        assertFalse(valueLayout.layoutInput.softWrap)
        assertFalse("value is never truncated", valueLayout.hasVisualOverflow)
        assertFalse(valueLayout.isLineEllipsized(0))

        val rowWidth = composeTestRule.onNodeWithTag(CONTAINER).width()
        val amountWidth = composeTestRule.onNodeWithTag(TransactionListTestTags.AMOUNT, useUnmergedTree = true).width()
        assertTrue("amount ($amountWidth) <= 50% of $rowWidth", amountWidth <= rowWidth * AMOUNT_MAX_WIDTH_FRACTION)
    }

    @Test
    fun readOnlyFollowsOverflowRule() {
        render { OceanTransactionListReadOnly(content = content, amount = amount, icon = Samples.icon) }
        assertOverflowRule()
    }

    @Test
    fun actionFollowsOverflowRule() {
        render { OceanTransactionListAction(content = content, amount = amount, icon = Samples.icon, onClick = {}) }
        assertOverflowRule()
    }

    @Test
    fun selectableFollowsOverflowRule() {
        render {
            OceanTransactionListSelectable(
                content = content,
                amount = amount,
                selected = false,
                onSelectedChange = {}
            )
        }
        assertOverflowRule()
    }

    @Test
    fun childReadOnlyFollowsOverflowRule() {
        render { OceanChildTransactionListReadOnly(content = content, amount = amount, icon = Samples.icon) }
        assertOverflowRule()
    }

    @Test
    fun childActionFollowsOverflowRule() {
        render {
            OceanChildTransactionListAction(content = content, amount = amount, icon = Samples.icon, onClick = {})
        }
        assertOverflowRule()
    }

    @Test
    fun shortTextsAreNotEllipsizedAndAmountWrapsItsContent() {
        render { OceanTransactionListReadOnly(content = Samples.content, amount = Samples.amount) }

        assertFalse(layout("Description").hasVisualOverflow)
        assertFalse(layout("Label").isLineEllipsized(0))
        val rowWidth = composeTestRule.onNodeWithTag(CONTAINER).width()
        val amountWidth = composeTestRule.onNodeWithTag(TransactionListTestTags.AMOUNT, useUnmergedTree = true).width()
        assertTrue("amount wraps its content", amountWidth < rowWidth * AMOUNT_MAX_WIDTH_FRACTION)
    }

    @Test
    fun legacyContentListOutsideTheFamilyIsUnchanged() {
        render { OceanContentList(style = ContentListStyle.Default(title = "Legacy", description = description)) }

        assertEquals(Int.MAX_VALUE, layout(description).layoutInput.maxLines)
        assertEquals(TextOverflow.Clip, layout(description).layoutInput.overflow)
    }

    private companion object {
        val ROW_WIDTH = 360.dp
        const val CONTAINER = "container"
    }
}
