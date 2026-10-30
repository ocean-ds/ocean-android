package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.compose.OceanTabItemModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

/**
 * Rolagem até a aba selecionada (movimento da web, MR-980): a decisão de rolar
 * ([oceanTabScrollTarget]) e o resultado na barra — a aba selecionada termina inteira na tela.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class OceanTabScrollMotionTest {

    @get:Rule val composeTestRule = createComposeRule()

    // Viewport de 300px, conteúdo rolável até 700px, margem de 24px.
    private fun target(tabLeft: Int, tabRight: Int, scrollValue: Int = 0) = oceanTabScrollTarget(
        tabLeft = tabLeft,
        tabRight = tabRight,
        scrollValue = scrollValue,
        viewportWidth = 300,
        maxScroll = 700,
        edgeMarginPx = 24
    )

    @Test
    fun doesNotScrollWhenTheTabIsFullyVisibleAwayFromTheEdges() {
        assertNull(target(tabLeft = 32, tabRight = 120))
        assertNull(target(tabLeft = 24, tabRight = 276))
    }

    @Test
    fun scrollsWhenTheTabIsWithinTheEdgeMargin() {
        // borda direita em 290: dentro dos 24px finais (276..300).
        assertEquals(95, target(tabLeft = 200, tabRight = 290))
        // borda esquerda em 100 com scroll 90: dentro dos 24px iniciais (90..114).
        assertEquals(26, target(tabLeft = 100, tabRight = 252, scrollValue = 90))
    }

    @Test
    fun centersATabThatIsPartlyOffScreen() {
        // centro da aba em 450 → scroll 300 deixa a aba no meio do viewport.
        assertEquals(300, target(tabLeft = 400, tabRight = 500))
    }

    @Test
    fun clampsTheCenteredTargetToTheScrollRange() {
        assertEquals(0, target(tabLeft = 10, tabRight = 60, scrollValue = 200))
        assertEquals(700, target(tabLeft = 950, tabRight = 1000))
    }

    @Test
    fun doesNotScrollWhenTheClampedTargetIsTheCurrentPosition() {
        assertNull(target(tabLeft = 980, tabRight = 1000, scrollValue = 700))
    }

    @Test
    fun doesNotScrollBeforeTheViewportIsMeasured() {
        assertNull(
            oceanTabScrollTarget(
                tabLeft = 400,
                tabRight = 500,
                scrollValue = 0,
                viewportWidth = 0,
                maxScroll = 0,
                edgeMarginPx = 24
            )
        )
    }

    private val barTag = "tabBar"
    private val barWidth = 320.dp
    private val tabs = (1..10).map { OceanTabItemModel("Seção $it") }
    private val isTab = SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)

    private fun assertSelectedTabFullyVisible(index: Int) {
        val tab = composeTestRule.onAllNodes(isTab)[index]
        tab.assertIsSelected()
        val clipped = tab.getBoundsInRoot()
        val unclipped = tab.getUnclippedBoundsInRoot()
        val bar = composeTestRule.onNodeWithTag(barTag).getBoundsInRoot()

        assertEquals("a aba não pode ser cortada (esquerda)", unclipped.left.value, clipped.left.value, 0.5f)
        assertEquals("a aba não pode ser cortada (direita)", unclipped.right.value, clipped.right.value, 0.5f)
        assertTrue("aba dentro da barra", unclipped.left >= bar.left && unclipped.right <= bar.right)
    }

    @Test
    fun selectingAnOffScreenTabScrollsItIntoView() {
        var selected by mutableIntStateOf(0)
        composeTestRule.setContent {
            OceanTabScrollable(
                modifier = Modifier.testTag(barTag).width(barWidth),
                tabs = tabs,
                defaultSelectedTab = selected,
                onSelectedTab = { selected = it }
            )
        }

        val before = composeTestRule.onAllNodes(isTab)[8].getUnclippedBoundsInRoot()
        assertTrue("o cenário precisa começar com a aba fora da tela", before.left > barWidth)

        selected = 8
        composeTestRule.waitForIdle()

        assertSelectedTabFullyVisible(8)
    }

    @Test
    fun initialSelectionOffScreenIsShownWithoutInteraction() {
        composeTestRule.setContent {
            OceanTabScrollable(
                modifier = Modifier.testTag(barTag).width(barWidth),
                tabs = tabs,
                defaultSelectedTab = 9,
                onSelectedTab = {}
            )
        }

        assertSelectedTabFullyVisible(9)
    }

    @Test
    fun overflowingFixedTabScrollsToTheSelectedTab() {
        var selected by mutableIntStateOf(0)
        val longTabs = listOf(
            OceanTabItemModel("Pague com seu limite"),
            OceanTabItemModel("Antecipação de recebíveis"),
            OceanTabItemModel("Histórico de vendas")
        )
        composeTestRule.setContent {
            OceanTab(
                modifier = Modifier.testTag(barTag).width(barWidth),
                tabs = longTabs,
                selectedTabIndex = selected,
                onSelectedTab = { selected = it }
            )
        }

        selected = 2
        composeTestRule.waitForIdle()

        assertSelectedTabFullyVisible(2)
    }
}
