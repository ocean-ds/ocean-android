package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.components.compose.AmountType
import br.com.useblu.oceands.components.compose.ContentListSize
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.ContentListType
import br.com.useblu.oceands.components.compose.OceanContentList
import br.com.useblu.oceands.ui.compose.OceanColors
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TransactionListBlocksTest {

    @get:Rule val composeTestRule = createComposeRule()

    private var darkUp: Color = Color.Unspecified
    private var darkDeep: Color = Color.Unspecified
    private var darkPure: Color = Color.Unspecified
    private var positiveDeep: Color = Color.Unspecified
    private var warningDeep: Color = Color.Unspecified

    private fun render(content: @androidx.compose.runtime.Composable () -> Unit) {
        composeTestRule.setContent {
            darkUp = OceanColors.interfaceDarkUp
            darkDeep = OceanColors.interfaceDarkDeep
            darkPure = OceanColors.interfaceDarkPure
            positiveDeep = OceanColors.statusPositiveDeep
            warningDeep = OceanColors.statusWarningDeep
            Column { content() }
        }
    }

    private fun style(text: String) = composeTestRule.onNodeWithText(text, useUnmergedTree = true).textStyle()

    // CT-6 / CA-6
    @Test
    fun contentSmWithAmountMdKeepsEachSize() {
        render {
            OceanTransactionListReadOnly(
                content = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description",
                    size = ContentListSize.Sm
                ),
                amount = Samples.amount.copy(amount = "R$ 1,00", size = ContentListSize.Md)
            )
        }

        assertEquals(12.sp, style("Title").fontSize)
        assertEquals(14.sp, style("Description").fontSize)
        assertEquals(16.sp, style("R$ 1,00").fontSize)
    }

    @Test
    fun contentMdWithAmountSmKeepsEachSize() {
        render {
            OceanTransactionListReadOnly(
                content = Samples.content,
                amount = Samples.amount.copy(amount = "R$ 1,00", size = ContentListSize.Sm)
            )
        }

        assertEquals(14.sp, style("Title").fontSize)
        assertEquals(16.sp, style("Description").fontSize)
        assertEquals(14.sp, style("R$ 1,00").fontSize)
    }

    // CT-7 / CA-7
    @Test
    fun strikethroughAmountShowsOriginalStruckAndCurrentPositive() {
        render {
            OceanContentList(
                style = ContentListStyle.Amount(
                    amount = "Grátis",
                    strikethroughAmount = "3,99%",
                    type = AmountType.Strikethrough
                )
            )
        }

        val original = style("3,99%")
        assertEquals(TextDecoration.LineThrough, original.textDecoration)
        assertEquals(darkUp, original.color)
        assertEquals(positiveDeep, style("Grátis").color)
    }

    @Test
    fun strikethroughNeutralShowsCurrentInDarkDeep() {
        render {
            OceanContentList(
                style = ContentListStyle.Amount(
                    amount = "R$ 90,00",
                    strikethroughAmount = "R$ 100,00",
                    type = AmountType.StrikethroughNeutral
                )
            )
        }

        assertEquals(TextDecoration.LineThrough, style("R$ 100,00").textDecoration)
        assertEquals(darkDeep, style("R$ 90,00").color)
    }

    @Test
    fun negativeAmountHasMinusPrefix() {
        render {
            OceanContentList(style = ContentListStyle.Amount(amount = "R$ 5,00", type = AmountType.Negative))
        }

        composeTestRule.onNodeWithText("- R$ 5,00", useUnmergedTree = true).assertExists()
    }

    @Test
    fun contentTypesUseFigmaColors() {
        render {
            OceanContentList(
                style = ContentListStyle.Default(title = "Positive", type = ContentListType.Positive)
            )
            OceanContentList(
                style = ContentListStyle.Default(title = "Warning", type = ContentListType.Warning)
            )
            OceanContentList(
                style = ContentListStyle.Inverted(
                    title = "Lead title",
                    description = "Lead",
                    type = ContentListType.HighlightLead
                )
            )
        }

        assertEquals(positiveDeep, style("Positive").color)
        assertEquals(warningDeep, style("Warning").color)
        assertEquals(20.sp, style("Lead").fontSize)
    }

    // CT-8 / CA-8
    @Test
    fun disabledItemUsesInactiveContentAndAmount() {
        render {
            OceanTransactionListReadOnly(
                content = Samples.content,
                amount = Samples.amount.copy(type = AmountType.Positive),
                state = OceanTransactionListState.Disabled
            )
        }

        listOf("Title", "Description", "Caption", "R$ 0,00", "Additional data").forEach {
            assertEquals("$it should be inactive", darkUp, style(it).color)
        }
    }

    // CT-9 / CA-9: without the new props the sibling lists keep the legacy rendering.
    @Test
    fun legacyContentWithoutNewPropsIsUnchanged() {
        render {
            OceanContentList(style = ContentListStyle.Default(title = "Legacy title"))
            OceanContentList(style = ContentListStyle.Inverted(title = "Inv title", description = "Legacy description"))
        }

        assertEquals(darkPure, style("Legacy title").color)
        assertEquals(16.sp, style("Legacy title").fontSize)
        assertEquals(darkPure, style("Legacy description").color)
    }

    @Test
    fun familyOptsLegacyContentIntoTokens() {
        render {
            OceanTransactionListReadOnly(
                content = ContentListStyle.Inverted(title = "Title", description = "Family description")
            )
        }

        assertEquals(darkDeep, style("Family description").color)
        assertNotEquals(darkPure, darkDeep)
    }

    // CT-10 / CA-10
    @Test
    fun legacyTransactionListItemIsDeprecated() {
        val legacy = Class.forName(
            "br.com.useblu.oceands.components.compose.list.OceanTransactionListItemKt"
        )
        val publicLegacy = legacy.declaredMethods.filter {
            it.name in setOf("OceanTransactionListItem", "SelectableTransactionListItem", "OceanTransactionListItemSkeleton") &&
                java.lang.reflect.Modifier.isPublic(it.modifiers)
        }

        assertTrue(publicLegacy.isNotEmpty())
        publicLegacy.forEach {
            assertTrue("${it.name} must be @Deprecated", it.isAnnotationPresent(Deprecated::class.java))
        }
    }
}
