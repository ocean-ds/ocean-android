package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.compose.OceanTabItemModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

/**
 * No [OceanScrollableTab], tocar numa aba distante leva a seleção direto até ela: o scroll-spy
 * fica suspenso enquanto a lista anima até a seção, para não selecionar as abas do meio.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class OceanScrollableTabTapTest {

    @get:Rule val composeTestRule = createComposeRule()

    private val tabs = (1..5).map { OceanTabItemModel("Seção $it") }
    private val isTab = SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)

    private fun setContent(onSelected: (Int) -> Unit) {
        composeTestRule.setContent {
            var selected by remember { mutableIntStateOf(0) }
            OceanScrollableTab(
                modifier = Modifier
                    .width(360.dp)
                    .height(480.dp),
                tabs = tabs,
                defaultSelectedTab = selected,
                onSelectedTab = {
                    onSelected(it)
                    selected = it
                },
                content = {
                    tabs.forEach { tab ->
                        item {
                            Column {
                                repeat(20) { Text("${tab.label} · linha $it", Modifier.height(40.dp)) }
                            }
                        }
                    }
                }
            )
        }
    }

    @Test
    fun tappingAFarTabSelectsOnlyThatTabWhileTheContentScrolls() {
        val selections = mutableListOf<Int>()
        setContent { selections += it }
        composeTestRule.waitForIdle()
        selections.clear()

        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.onAllNodes(isTab)[3].performClick()
        repeat(120) { composeTestRule.mainClock.advanceTimeByFrame() }
        composeTestRule.mainClock.autoAdvance = true
        composeTestRule.waitForIdle()

        assertEquals("seleções durante o scroll do toque", listOf(3), selections.distinct())
        composeTestRule.onAllNodes(isTab)[3].assertIsSelected()
    }

    @Test
    fun scrollSpyResumesAfterTheTapScrollEnds() {
        val selections = mutableListOf<Int>()
        setContent { selections += it }
        composeTestRule.onAllNodes(isTab)[3].performClick()
        composeTestRule.waitForIdle()
        selections.clear()

        // Rolagem da lista (não um toque): item 0 é a barra, a seção 2 (aba 1) é o item 2.
        composeTestRule.onNode(hasScrollToIndexAction()).performScrollToIndex(2)
        composeTestRule.waitForIdle()

        assertEquals("o scroll-spy precisa voltar a reportar a seção", 1, selections.lastOrNull())
        composeTestRule.onAllNodes(isTab)[1].assertIsSelected()
    }

    @Test
    fun stickyBarKeepsTheMdSideMargin() {
        setContent { }
        composeTestRule.onAllNodes(isTab)[1].performClick()
        composeTestRule.waitForIdle()

        // A lista rolou até a seção 2: a barra está presa no topo, sem rolagem horizontal.
        val firstTab = composeTestRule.onAllNodes(isTab)[0].getUnclippedBoundsInRoot()
        assertEquals("barra presa no topo", 0f, firstTab.top.value, 0.5f)
        assertEquals("margem lateral de 32dp", 32f, firstTab.left.value, 0.5f)
    }
}
