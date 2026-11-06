package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.compose.OceanSegmentedControlOption
import br.com.useblu.oceands.utils.OceanIcons
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

/**
 * Trava o Segmented Control do Compose contra a spec do MR-990: seleção e semântica, ícone e
 * contador opcionais, texto longo em uma linha com "…", lista com tamanho diferente de 2 e o
 * deslize do fundo de seleção.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class OceanSegmentedControlTest {

    @get:Rule val composeTestRule = createComposeRule()

    private val controlTag = "segmentedControl"

    private val isButton = SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button)

    private val storeAndNetwork = listOf(
        OceanSegmentedControlOption("Loja"),
        OceanSegmentedControlOption("Rede")
    )

    private fun setControl(
        options: List<OceanSegmentedControlOption> = storeAndNetwork,
        selectedIndex: Int = 0,
        modifier: Modifier = Modifier,
        onSelectedOption: (Int) -> Unit = {}
    ) {
        composeTestRule.setContent {
            Box(modifier = modifier) {
                OceanSegmentedControl(
                    modifier = Modifier.testTag(controlTag),
                    options = options,
                    selectedIndex = selectedIndex,
                    onSelectedOption = onSelectedOption
                )
            }
        }
    }

    @Test
    fun callsOnSelectedOptionWithTheTappedIndexAndSelectsIt() {
        var selected = -1
        setControl(onSelectedOption = { selected = it })

        composeTestRule.onNodeWithText("Rede").performClick()

        assertEquals(1, selected)
        composeTestRule.onAllNodes(isButton)[0].assertIsNotSelected()
        composeTestRule.onAllNodes(isButton)[1].assertIsSelected()
    }

    @Test
    fun tappingTheSelectedOptionDoesNotCallOnSelectedOption() {
        var calls = 0
        setControl(onSelectedOption = { calls++ })

        composeTestRule.onNodeWithText("Loja").performClick()

        assertEquals(0, calls)
        composeTestRule.onAllNodes(isButton)[0].assertIsSelected()
    }

    @Test
    fun eachOptionIsAButtonWithTheSelectedState() {
        setControl(selectedIndex = 1)

        composeTestRule.onAllNodes(isButton).assertCountEquals(2)
        composeTestRule.onAllNodes(isButton)[0].assertIsNotSelected()
        composeTestRule.onAllNodes(isButton)[1].assertIsSelected()
    }

    @Test
    fun keepsTheControlAtFortyDpAndTheOptionAtThirtyTwoDp() {
        setControl()

        composeTestRule.onNodeWithTag(controlTag).assertHeightIsEqualTo(40.dp)
        composeTestRule.onAllNodes(isButton)[0].assertHeightIsEqualTo(32.dp)
    }

    @Test
    fun hugsTheContentOfTheOptions() {
        setControl(modifier = Modifier.width(360.dp))

        val control = composeTestRule.onNodeWithTag(controlTag).getBoundsInRoot()
        val first = composeTestRule.onAllNodes(isButton)[0].getBoundsInRoot()
        val second = composeTestRule.onAllNodes(isButton)[1].getBoundsInRoot()

        // 4dp de padding em volta e 4dp entre as opções.
        val expected = (first.right - first.left) + (second.right - second.left) + 12.dp
        assertEquals(expected.value, (control.right - control.left).value, 0.5f)
        assertTrue("não estica na largura disponível", control.right - control.left < 360.dp)
        assertEquals(4f, (first.left - control.left).value, 0.5f)
        assertEquals(4f, (second.left - first.right).value, 0.5f)
    }

    @Test
    fun showsTheIconOnlyWhenItIsPassed() {
        setControl(
            options = listOf(
                OceanSegmentedControlOption("Lista", icon = OceanIcons.VIEW_LIST_OUTLINE),
                OceanSegmentedControlOption("Grade")
            )
        )

        composeTestRule.onNodeWithTag(OceanIcons.VIEW_LIST_OUTLINE.name, useUnmergedTree = true)
            .assertHeightIsEqualTo(16.dp)
        composeTestRule.onAllNodes(
            SemanticsMatcher.expectValue(SemanticsProperties.TestTag, OceanIcons.VIEW_GRID_OUTLINE.name),
            useUnmergedTree = true
        ).assertCountEquals(0)
    }

    @Test
    fun showsTheCounterOnlyWhenItIsPassed() {
        setControl(
            options = listOf(
                OceanSegmentedControlOption("Pendentes", counter = 3),
                OceanSegmentedControlOption("Pagas")
            )
        )

        composeTestRule.onAllNodes(hasExactText("3"), useUnmergedTree = true).assertCountEquals(1)
        // Só o texto das duas opções e o contador da primeira.
        composeTestRule.onAllNodes(hasAnyText(), useUnmergedTree = true).assertCountEquals(3)
    }

    @Test
    fun longLabelStaysOnOneLineAndEndsWithAnEllipsis() {
        val longLabel = "Todas as lojas da rede com um nome muito comprido"
        setControl(
            options = listOf(
                OceanSegmentedControlOption(longLabel, counter = 8),
                OceanSegmentedControlOption("Rede")
            ),
            modifier = Modifier.width(240.dp)
        )

        val label = composeTestRule.onNodeWithText(longLabel, useUnmergedTree = true)
        val layout = label.textLayout()
        assertEquals(1, layout.lineCount)
        assertTrue("o texto precisa ser cortado com …", layout.isLineEllipsized(0))

        // O controle cabe na largura disponível e o contador e a outra opção continuam visíveis.
        val control = composeTestRule.onNodeWithTag(controlTag).getBoundsInRoot()
        assertTrue(control.right - control.left <= 240.dp)
        composeTestRule.onAllNodes(hasExactText("8"), useUnmergedTree = true).assertCountEquals(1)
        val other = composeTestRule.onNodeWithText("Rede", useUnmergedTree = true)
        assertEquals(other.getUnclippedBoundsInRoot().right.value, other.getBoundsInRoot().right.value, 0.5f)
    }

    @Test
    fun drawsOnlyTheFirstTwoOptions() {
        setControl(
            options = listOf(
                OceanSegmentedControlOption("Dia"),
                OceanSegmentedControlOption("Semana"),
                OceanSegmentedControlOption("Mês")
            )
        )

        composeTestRule.onAllNodes(isButton).assertCountEquals(2)
        composeTestRule.onAllNodes(hasExactText("Mês"), useUnmergedTree = true).assertCountEquals(0)
    }

    @Test
    fun drawsASingleOptionSelected() {
        setControl(options = listOf(OceanSegmentedControlOption("Loja")), selectedIndex = 1)

        composeTestRule.onAllNodes(isButton).assertCountEquals(1)
        composeTestRule.onAllNodes(isButton)[0].assertIsSelected()
    }

    @Test
    fun drawsNothingForAnEmptyList() {
        setControl(options = emptyList())

        composeTestRule.onAllNodes(SemanticsMatcher.expectValue(SemanticsProperties.TestTag, controlTag))
            .assertCountEquals(0)
    }

    @Test
    fun limitsAnOutOfRangeSelectedIndexToTheDrawnOptions() {
        setControl(selectedIndex = 5)

        composeTestRule.onAllNodes(isButton)[1].assertIsSelected()
    }

    @Test
    fun selectionBackgroundSlidesToTheNewOptionIn300Ms() {
        setControl(modifier = Modifier.width(360.dp))

        val selection = composeTestRule.onNodeWithTag(OCEAN_SEGMENTED_CONTROL_SELECTION_TAG)
        val first = composeTestRule.onAllNodes(isButton)[0].getBoundsInRoot()
        val second = composeTestRule.onAllNodes(isButton)[1].getBoundsInRoot()
        assertEquals(first.left.value, selection.getBoundsInRoot().left.value, 0.5f)

        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.onNodeWithText("Rede").performClick()
        composeTestRule.mainClock.advanceTimeBy(150)

        val middle = selection.getBoundsInRoot().left
        assertTrue("no meio da animação o fundo está entre as opções", middle > first.left && middle < second.left)

        composeTestRule.mainClock.advanceTimeBy(300)

        val end = selection.getBoundsInRoot()
        assertEquals(second.left.value, end.left.value, 0.5f)
        assertEquals((second.right - second.left).value, (end.right - end.left).value, 0.5f)
    }

    @Test
    fun optionWidthsKeepTheContentWhenItFits() {
        assertEquals(listOf(40, 50), oceanSegmentedControlOptionWidths(listOf(40, 50), availableWidth = 200))
        assertEquals(listOf(40, 50), oceanSegmentedControlOptionWidths(listOf(40, 50), availableWidth = null))
    }

    @Test
    fun optionWidthsGiveTheRestToTheLongOptionWhenTheShortOneFitsInHalf() {
        assertEquals(listOf(160, 40), oceanSegmentedControlOptionWidths(listOf(300, 40), availableWidth = 200))
        assertEquals(listOf(40, 160), oceanSegmentedControlOptionWidths(listOf(40, 300), availableWidth = 200))
    }

    @Test
    fun optionWidthsSplitInHalfWhenBothAreLong() {
        assertEquals(listOf(100, 100), oceanSegmentedControlOptionWidths(listOf(300, 250), availableWidth = 200))
    }

    private fun hasExactText(text: String) = SemanticsMatcher("text == $text") { node ->
        node.config.getOrNull(SemanticsProperties.Text)?.any { it.text == text } == true
    }

    private fun hasAnyText() = SemanticsMatcher("has text") { node ->
        node.config.getOrNull(SemanticsProperties.Text)?.isNotEmpty() == true
    }

    private fun SemanticsNodeInteraction.textLayout(): TextLayoutResult {
        val results = mutableListOf<TextLayoutResult>()
        fetchSemanticsNode().config.getOrNull(SemanticsActions.GetTextLayoutResult)
            ?.action?.invoke(results)
        return results.first()
    }
}
