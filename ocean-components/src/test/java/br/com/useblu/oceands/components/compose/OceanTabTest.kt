package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.compose.OceanTabItemModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

/**
 * Trava o Tab do Compose contra o Figma (MR-980): seleção, linha de baixo, contador e o texto
 * longo, que nunca quebra nem é cortado — a barra fixa passa a rolar.
 *
 * Os testes de texto longo comparam bounds clipados vs. não-clipados: só a posição do label
 * passaria mesmo com o texto cortado pelo clip da barra.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class OceanTabTest {

    @get:Rule val composeTestRule = createComposeRule()

    private val barTag = "tabBar"
    private val barWidth = 320.dp

    // 159,6dp em heading4 (ocean-tab-label-overflow): + 32dp de padding não cabe em meia barra de 320dp.
    private val longLabel = "Pague com seu limite"

    private val isTab = SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)

    private fun setFixedTab(
        tabs: List<OceanTabItemModel>,
        selectedTabIndex: Int = 0,
        onSelectedTab: (Int) -> Unit = {}
    ) {
        composeTestRule.setContent {
            OceanTab(
                modifier = Modifier
                    .testTag(barTag)
                    .width(barWidth),
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
                onSelectedTab = onSelectedTab
            )
        }
    }

    @Test
    fun callsOnSelectedTabWithTheTappedIndexAndSelectsIt() {
        var selected = -1
        setFixedTab(
            tabs = listOf(OceanTabItemModel("Vendas"), OceanTabItemModel("Recebíveis")),
            onSelectedTab = { selected = it }
        )

        composeTestRule.onNodeWithText("Recebíveis").performClick()

        assertEquals(1, selected)
        composeTestRule.onAllNodes(isTab)[1].assertIsSelected()
    }

    @Test
    fun scrollableCallsOnSelectedTabWithTheTappedIndex() {
        var selected = -1
        composeTestRule.setContent {
            OceanTabScrollable(
                tabs = (1..5).map { OceanTabItemModel("Tab $it") },
                defaultSelectedTab = 0,
                onSelectedTab = { selected = it }
            )
        }

        composeTestRule.onNodeWithText("Tab 3").performClick()

        assertEquals(2, selected)
    }

    @Test
    fun sharesTheWidthEquallyAndDoesNotScrollWhenAllLabelsFit() {
        setFixedTab(tabs = listOf(OceanTabItemModel("Vendas"), OceanTabItemModel("Recebíveis")))

        composeTestRule.onAllNodes(isTab).assertCountEquals(2)
        composeTestRule.onAllNodes(isTab)[0].assertWidthIsEqualTo(barWidth / 2)
        composeTestRule.onAllNodes(isTab)[1].assertWidthIsEqualTo(barWidth / 2)
        composeTestRule.onAllNodes(hasScrollAction()).assertCountEquals(0)
    }

    @Test
    fun keepsTheItemHeightAtFiftySixDp() {
        setFixedTab(tabs = listOf(OceanTabItemModel("Vendas"), OceanTabItemModel("Recebíveis")))

        composeTestRule.onNodeWithTag(barTag).assertHeightIsEqualTo(56.dp)
        composeTestRule.onAllNodes(isTab)[0].assertHeightIsEqualTo(56.dp)
    }

    @Test
    fun longLabelStaysOnOneLineAndTheFixedBarBecomesScrollable() {
        setFixedTab(tabs = listOf(OceanTabItemModel(longLabel), OceanTabItemModel("Antecipação")))

        assertEquals(1, composeTestRule.onNodeWithText(longLabel).lineCount())
        composeTestRule.onAllNodes(hasScrollAction()).assertCountEquals(1)
        composeTestRule.onNodeWithTag(barTag).assertWidthIsEqualTo(barWidth)
    }

    @Test
    fun longLabelIsNotCutByItsTab() {
        setFixedTab(tabs = listOf(OceanTabItemModel(longLabel), OceanTabItemModel("Antecipação")))

        val label = composeTestRule.onNodeWithText(longLabel)
        val clipped = label.getBoundsInRoot()
        val unclipped = label.getUnclippedBoundsInRoot()

        // O label inteiro cabe na barra (não é cortado pela aba nem pelo viewport do scroll).
        assertTrue(
            "o label precisa ser mais largo que meia barra para o cenário valer",
            (unclipped.right - unclipped.left) > barWidth / 2
        )
        assertEquals(unclipped.left.value, clipped.left.value, 0.5f)
        assertEquals(unclipped.right.value, clipped.right.value, 0.5f)
    }

    @Test
    fun overflowingFixedBarStartsTheFirstTabAfterTheMdEdgePadding() {
        setFixedTab(tabs = listOf(OceanTabItemModel(longLabel), OceanTabItemModel("Antecipação")))

        val firstTab = composeTestRule.onAllNodes(isTab)[0].getUnclippedBoundsInRoot()
        assertEquals(32f, firstTab.left.value, 0.5f)
    }

    @Test
    fun scrollableTabHugsAShortLabelBelowMaterialsNinetyDpMinimum() {
        composeTestRule.setContent {
            OceanTabScrollable(
                modifier = Modifier.width(barWidth),
                tabs = listOf(OceanTabItemModel("Pix"), OceanTabItemModel("Boleto")),
                defaultSelectedTab = 0,
                onSelectedTab = {}
            )
        }

        val shortTab = composeTestRule.onAllNodes(isTab)[0].getUnclippedBoundsInRoot()
        val label = composeTestRule.onNodeWithText("Pix", useUnmergedTree = true)
            .getUnclippedBoundsInRoot()
        val tabWidth = shortTab.right - shortTab.left

        assertTrue("a aba curta deveria ter menos de 90dp, tem $tabWidth", tabWidth < 90.dp)
        // 16 + texto + 16: o item abraça o conteúdo.
        assertEquals((label.right - label.left).value + 32f, tabWidth.value, 0.5f)
    }

    @Test
    fun drawsAOneDpDividerAcrossTheWholeFixedBar() {
        setFixedTab(tabs = listOf(OceanTabItemModel("Vendas"), OceanTabItemModel("Recebíveis")))

        composeTestRule.onAllNodesWithTag(OCEAN_TAB_DIVIDER_TAG, useUnmergedTree = true)
            .assertCountEquals(1)
        composeTestRule.onNodeWithTag(OCEAN_TAB_DIVIDER_TAG, useUnmergedTree = true)
            .assertHeightIsEqualTo(1.dp)
            .assertWidthIsEqualTo(barWidth)
    }

    @Test
    fun drawsAOneDpDividerAcrossTheWholeScrollableBar() {
        composeTestRule.setContent {
            OceanTabScrollable(
                modifier = Modifier.width(barWidth),
                tabs = (1..5).map { OceanTabItemModel("Tab $it") },
                defaultSelectedTab = 0,
                onSelectedTab = {}
            )
        }

        composeTestRule.onNodeWithTag(OCEAN_TAB_DIVIDER_TAG, useUnmergedTree = true)
            .assertHeightIsEqualTo(1.dp)
            .assertWidthIsEqualTo(barWidth)
    }

    @Test
    fun scrollableStartsTheFirstTabAfterTheMdEdgePadding() {
        composeTestRule.setContent {
            OceanTabScrollable(
                modifier = Modifier.width(barWidth),
                tabs = (1..5).map { OceanTabItemModel("Tab $it") },
                defaultSelectedTab = 0,
                onSelectedTab = {}
            )
        }

        val firstTab = composeTestRule.onAllNodes(isTab)[0].getUnclippedBoundsInRoot()
        assertEquals(32f, firstTab.left.value, 0.5f)
    }

    @Test
    fun showsTheCounterOnSelectedAndInactiveTabs() {
        setFixedTab(
            tabs = listOf(
                OceanTabItemModel("Vendas", counter = 3),
                OceanTabItemModel("Pendências", counter = 12, badgeType = OceanBadgeType.WARNING),
                OceanTabItemModel("Sem contador")
            )
        )

        // O contador entra na semântica mesclada da própria aba (texto + número).
        composeTestRule.onAllNodes(isTab)[0].assert(hasText("Vendas") and hasText("3")).assertIsDisplayed()
        composeTestRule.onAllNodes(isTab)[1].assert(hasText("Pendências") and hasText("12")).assertIsDisplayed()
        composeTestRule.onAllNodes(isTab)[2].assert(hasText("Sem contador"))
    }

    private fun SemanticsNodeInteraction.lineCount(): Int {
        val results = mutableListOf<TextLayoutResult>()
        fetchSemanticsNode().config.getOrNull(SemanticsActions.GetTextLayoutResult)
            ?.action?.invoke(results)
        return results.first().lineCount
    }
}
