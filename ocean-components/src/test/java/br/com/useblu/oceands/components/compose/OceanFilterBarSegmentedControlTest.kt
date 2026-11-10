package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertLeftPositionInRootIsEqualTo
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanBasicChip
import br.com.useblu.oceands.model.OceanChip
import br.com.useblu.oceands.model.OceanChipItemState
import br.com.useblu.oceands.model.compose.OceanFilterBarSegmentedControl
import br.com.useblu.oceands.model.compose.OceanSegmentedControlOption
import br.com.useblu.oceands.utils.OceanIcons
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

/**
 * Trava o Segmented Control opcional do [OceanFilterBar] contra a spec do MR-999 (Figma Filter Bar
 * 1.1.0, Mobile):
 *
 * - `segmentedControl = null` (padrão) não muda nada: divisor antes do último chip, 64dp, 16dp na
 *   borda;
 * - com Segmented: sem divisor antes do último chip (8dp entre chips, mesmo com
 *   `showDividerBeforeLastItem = true`), chips cortados colados no divisor, respiro de 16dp no fim
 *   da rolagem, divisor · 8 · Segmented · 16 fixos à direita, altura de 64dp;
 * - o Segmented aparece só com texto (ícone e contador removidos);
 * - texto longo: chip e opção do Segmented em uma linha.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class OceanFilterBarSegmentedControlTest {

    @get:Rule val composeTestRule = createComposeRule()

    private val filterBarTag = "filterBar"

    // Menor que a tela do Robolectric (320dp), para a largura não ser limitada pela raiz.
    private val barWidth = 300.dp

    private val sixteen = 16.dp
    private val eight = 8.dp
    private val hairline = 1.dp

    private fun chip(id: String, label: String) = OceanBasicChip(
        id = id,
        label = label,
        state = OceanChipItemState.HOVER_INACTIVE,
        onClick = {}
    )

    private val shortChips: List<OceanChip> = listOf(
        chip(id = "0", label = "Todos"),
        chip(id = "1", label = "Abertos"),
        chip(id = "2", label = "Todos os filtros")
    )

    private val overflowingChips: List<OceanChip> = listOf(
        chip(id = "0", label = "Todos"),
        chip(id = "1", label = "Pendentes"),
        chip(id = "2", label = "Aguardando pagamento"),
        chip(id = "3", label = "Canceladas"),
        chip(id = "4", label = "Estornadas"),
        chip(id = "5", label = "Todos os filtros")
    )

    private val storeAndNetwork = listOf(
        OceanSegmentedControlOption("Loja"),
        OceanSegmentedControlOption("Rede")
    )

    private fun segmented(
        options: List<OceanSegmentedControlOption> = storeAndNetwork,
        selectedIndex: Int = 0,
        onSelectedOption: (Int) -> Unit = {}
    ) = OceanFilterBarSegmentedControl(
        options = options,
        selectedIndex = selectedIndex,
        onSelectedOption = onSelectedOption
    )

    private fun setBar(
        filterList: List<OceanChip> = shortChips,
        showDividerBeforeLastItem: Boolean = true,
        segmentedControl: OceanFilterBarSegmentedControl? = segmented(),
        width: Dp = barWidth
    ) {
        composeTestRule.setContent {
            OceanFilterBar(
                modifier = Modifier
                    .testTag(filterBarTag)
                    .width(width),
                filterList = filterList,
                showDividerBeforeLastItem = showDividerBeforeLastItem,
                segmentedControl = segmentedControl
            )
        }
    }

    private fun boundsOf(label: String) =
        composeTestRule.onNodeWithText(label).getUnclippedBoundsInRoot()

    private fun gapBetween(left: String, right: String): Float =
        (boundsOf(right).left - boundsOf(left).right).value

    private fun segmentedBounds() =
        composeTestRule.onNodeWithTag(OCEAN_FILTER_BAR_SEGMENTED_CONTROL_TAG).getBoundsInRoot()

    private fun dividerBounds() =
        composeTestRule.onNodeWithTag(OCEAN_FILTER_BAR_SEGMENTED_DIVIDER_TAG, useUnmergedTree = true)
            .getBoundsInRoot()

    // --- default null: nada muda ---

    @Test
    fun nullSegmentedControlKeepsTheDividerBeforeTheLastChip() {
        setBar(segmentedControl = null)

        // 8 (espaço) + 1 (divisor) + 8 (espaço) entre o penúltimo e o último chip, como antes.
        assertEquals(
            (eight + hairline + eight).value,
            gapBetween("Abertos", "Todos os filtros"),
            0.5f
        )
        assertEquals(eight.value, gapBetween("Todos", "Abertos"), 0.5f)
    }

    @Test
    fun nullSegmentedControlKeepsTheBarGeometryAndShowsNoSegmentedControl() {
        setBar(segmentedControl = null)

        composeTestRule.onNodeWithTag(filterBarTag).assertHeightIsEqualTo(64.dp)
        composeTestRule.onNodeWithText("Todos").assertLeftPositionInRootIsEqualTo(sixteen)
        composeTestRule.onNodeWithTag(OCEAN_FILTER_BAR_SEGMENTED_CONTROL_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(OCEAN_FILTER_BAR_SEGMENTED_DIVIDER_TAG, useUnmergedTree = true)
            .assertDoesNotExist()
    }

    // --- com Segmented ---

    @Test
    fun segmentedControlRemovesTheDividerBeforeTheLastChipEvenWhenAskedFor() {
        setBar(showDividerBeforeLastItem = true)

        assertEquals(eight.value, gapBetween("Abertos", "Todos os filtros"), 0.5f)
        assertEquals(eight.value, gapBetween("Todos", "Abertos"), 0.5f)
    }

    @Test
    fun segmentedControlKeepsTheBarHeightAndTheFirstChipSixteenDpFromTheEdge() {
        setBar()

        composeTestRule.onNodeWithTag(filterBarTag).assertHeightIsEqualTo(64.dp)
        composeTestRule.onNodeWithText("Todos").assertLeftPositionInRootIsEqualTo(sixteen)
        composeTestRule.onNodeWithTag(OCEAN_FILTER_BAR_SEGMENTED_CONTROL_TAG)
            .assertHeightIsEqualTo(40.dp)
    }

    @Test
    fun segmentedControlIsPinnedToTheRightAfterTheDivider() {
        setBar()

        val segmented = segmentedBounds()
        val divider = dividerBounds()

        // Segmented · 16 até a borda direita.
        assertEquals((barWidth - sixteen).value, segmented.right.value, 0.5f)
        // Divisor · 8 · Segmented.
        assertEquals(eight.value, (segmented.left - divider.right).value, 0.5f)
        assertEquals(hairline.value, (divider.right - divider.left).value, 0.5f)
        assertEquals(32f, (divider.bottom - divider.top).value, 0.5f)
        // Centralizados na barra de 64dp.
        assertEquals(12f, segmented.top.value, 0.5f)
        assertEquals(16f, divider.top.value, 0.5f)
    }

    @Test
    fun overflowingChipsAreClippedFlushAgainstTheDivider() {
        setBar(filterList = overflowingChips)

        val dividerLeft = dividerBounds().left.value
        val straddling = overflowingChips
            .map { it.label }
            .firstOrNull { label ->
                val bounds = boundsOf(label)
                bounds.left.value < dividerLeft && bounds.right.value > dividerLeft
            }
        assertTrue("nenhum chip atravessa o divisor — o cenário não exercita o corte", straddling != null)

        val clipped = composeTestRule.onNodeWithText(straddling!!).getBoundsInRoot()
        assertEquals(dividerLeft, clipped.right.value, 0.5f)
    }

    @Test
    fun leavesSixteenDpOfRoomBeforeTheDividerAtTheEndOfTheScroll() {
        setBar(filterList = overflowingChips)
        composeTestRule.onNode(hasScrollAction())
            .performSemanticsAction(SemanticsActions.ScrollBy) { it(10_000f, 0f) }

        val lastChip = composeTestRule.onNodeWithText("Todos os filtros")
        lastChip.assertIsDisplayed()
        assertEquals(
            sixteen.value,
            (dividerBounds().left - lastChip.getBoundsInRoot().right).value,
            0.5f
        )
        // O Segmented não rola.
        assertEquals((barWidth - sixteen).value, segmentedBounds().right.value, 0.5f)
    }

    @Test
    fun segmentedControlSelectsAndCallsBack() {
        var selected = -1
        setBar(segmentedControl = segmented(onSelectedOption = { selected = it }))

        composeTestRule.onNodeWithText("Rede").performClick()

        assertEquals(1, selected)
        composeTestRule.onNodeWithText("Rede").assertIsSelected()
    }

    // --- só texto ---

    @Test
    fun textOnlyOptionsDropIconAndCounterAndKeepTheLabel() {
        val control = segmented(
            options = listOf(
                OceanSegmentedControlOption("Loja", icon = OceanIcons.SHOPPING_BAG_OUTLINE, counter = 3),
                OceanSegmentedControlOption("Rede", counter = 12)
            )
        )

        val options = control.textOnlyOptions()

        assertEquals(listOf("Loja", "Rede"), options.map { it.label })
        options.forEach {
            assertNull(it.icon)
            assertNull(it.counter)
        }
    }

    @Test
    fun segmentedControlInTheBarShowsNoCounter() {
        setBar(
            segmentedControl = segmented(
                options = listOf(
                    OceanSegmentedControlOption("Loja", icon = OceanIcons.SHOPPING_BAG_OUTLINE, counter = 3),
                    OceanSegmentedControlOption("Rede", counter = 12)
                )
            )
        )

        composeTestRule.onNodeWithText("Loja").assertIsDisplayed()
        composeTestRule.onNodeWithText("3", useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithText("12", useUnmergedTree = true).assertDoesNotExist()
    }

    // --- texto longo ---

    @Test
    fun longChipLabelStaysOnOneLineAndTheBarKeepsItsHeight() {
        val longLabel = "Aguardando confirmação do pagamento pelo banco emissor"
        setBar(filterList = listOf(chip(id = "0", label = longLabel), chip(id = "1", label = "Abertos")))

        assertEquals(1, composeTestRule.onNodeWithText(longLabel, useUnmergedTree = true).textLayout().lineCount)
        composeTestRule.onNodeWithTag(filterBarTag).assertHeightIsEqualTo(64.dp)
        assertEquals((barWidth - sixteen).value, segmentedBounds().right.value, 0.5f)
    }

    @Test
    fun longSegmentedLabelStaysOnOneLineInsideTheBar() {
        val longLabel = "Todas as lojas da rede com um nome muito comprido mesmo"
        setBar(
            segmentedControl = segmented(
                options = listOf(
                    OceanSegmentedControlOption(longLabel),
                    OceanSegmentedControlOption("Rede")
                )
            )
        )

        val layout = composeTestRule.onNodeWithText(longLabel, useUnmergedTree = true).textLayout()
        assertEquals(1, layout.lineCount)
        assertTrue("o texto precisa ser cortado com …", layout.isLineEllipsized(0))
        composeTestRule.onNodeWithTag(filterBarTag).assertHeightIsEqualTo(64.dp)
        val segmented = segmentedBounds()
        assertEquals((barWidth - sixteen).value, segmented.right.value, 0.5f)
        // O Segmented ocupa no máximo metade da barra.
        assertTrue(
            "o Segmented passou da metade da barra: ${segmented.right - segmented.left}",
            (segmented.right - segmented.left).value <= barWidth.value / 2 + 0.5f
        )
        // A área dos chips não some: o primeiro chip aparece, com largura visível > 0, antes do divisor.
        val firstChip = composeTestRule.onNodeWithText("Todos")
        firstChip.assertIsDisplayed()
        val firstChipVisible = firstChip.getBoundsInRoot()
        assertTrue((firstChipVisible.right - firstChipVisible.left).value > 0f)
        assertTrue(firstChipVisible.right.value <= dividerBounds().left.value + 0.5f)
        composeTestRule.onNodeWithText("Todos").assertLeftPositionInRootIsEqualTo(sixteen)
    }

    @Test
    fun shortSegmentedLabelsKeepTheirNaturalWidth() {
        setBar()

        val loja = composeTestRule.onNodeWithText("Loja", useUnmergedTree = true).textLayout()
        val rede = composeTestRule.onNodeWithText("Rede", useUnmergedTree = true).textLayout()
        assertTrue(!loja.isLineEllipsized(0) && !rede.isLineEllipsized(0))
        assertTrue((segmentedBounds().right - segmentedBounds().left).value < barWidth.value / 2)
    }

    private fun SemanticsNodeInteraction.textLayout(): TextLayoutResult {
        val results = mutableListOf<TextLayoutResult>()
        fetchSemanticsNode().config.getOrNull(SemanticsActions.GetTextLayoutResult)
            ?.action?.invoke(results)
        return results.first()
    }
}
