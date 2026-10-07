package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertLeftPositionInRootIsEqualTo
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanBasicChip
import br.com.useblu.oceands.model.OceanChip
import br.com.useblu.oceands.model.OceanChipItemState
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Trava a geometria do [OceanFilterBar] depois de o padding horizontal sair do container e
 * entrar no conteúdo rolável (MR-693).
 *
 * Os testes se dividem em dois papéis, de propósito:
 *
 * - **Não-regressão** (`keepsThe*`): provam que o fix não custou nada em repouso — mesma altura
 *   de 64dp, mesmo primeiro chip a 16dp da borda. Passam antes e depois do fix, e é isso que se
 *   espera deles: existem para pegar regressão futura, não para provar a correção.
 * - **Correção** (`clipsThe*`): provam o fix em si. O que muda não é a posição dos chips — os
 *   bounds não-clipados são idênticos nas duas versões — e sim o **retângulo de clip** do scroll.
 *   Antes, o clip era o viewport encolhido pela moldura de 16dp; agora é a borda real do
 *   container. Por isso a asserção compara clipado vs. não-clipado: é o único par que enxerga a
 *   diferença. Sem o fix esses dois testes ficam vermelhos.
 */
@RunWith(RobolectricTestRunner::class)
class OceanFilterBarPaddingTest {

    @get:Rule val composeTestRule = createComposeRule()

    private val filterBarTag = "filterBar"
    private val barWidth = 200.dp
    private val padding = 16.dp

    private val firstChipLabel = "Todos"
    private val lastChipLabel = "Todos os filtros"

    private fun chip(id: String, label: String) = OceanBasicChip(
        id = id,
        label = label,
        state = OceanChipItemState.HOVER_INACTIVE,
        onClick = {}
    )

    private val overflowingChips: List<OceanChip> = listOf(
        chip(id = "0", label = firstChipLabel),
        chip(id = "1", label = "Pendentes"),
        chip(id = "2", label = "Aguardando pagamento"),
        chip(id = "3", label = "Canceladas"),
        chip(id = "4", label = "Estornadas"),
        chip(id = "5", label = lastChipLabel)
    )

    private fun setOverflowingContent() {
        composeTestRule.setContent {
            OceanFilterBar(
                modifier = Modifier
                    .testTag(filterBarTag)
                    .width(barWidth),
                filterList = overflowingChips
            )
        }
    }

    private fun scrollToEnd() {
        composeTestRule.onNode(hasScrollAction())
            .performSemanticsAction(SemanticsActions.ScrollBy) { it(10_000f, 0f) }
    }

    private fun unclippedBoundsOf(label: String) =
        composeTestRule.onNodeWithText(label).getUnclippedBoundsInRoot()

    /**
     * Devolve o chip que atravessa [border] — o único lugar onde clipado e não-clipado divergem.
     * Falha explicitamente se nenhum atravessar: um cenário que não monta a situação não pode
     * passar por ausência de contradição.
     */
    private fun chipStraddling(border: Float): Pair<String, DpRect> {
        val straddling = overflowingChips
            .map { it.label to unclippedBoundsOf(it.label) }
            .firstOrNull { (_, bounds) ->
                bounds.left.value < border && bounds.right.value > border
            }

        Assert.assertNotNull(
            "nenhum chip atravessa a borda em ${border}dp — o cenário não exercita o clip",
            straddling
        )

        return straddling!!
    }

    // Não-regressão — a altura total (32 do Row + 16 + 16 de padding vertical) não muda.
    @Test
    fun keepsTheBarHeightAtSixtyFourDp() {
        setOverflowingContent()

        composeTestRule.onNodeWithTag(filterBarTag).assertHeightIsEqualTo(64.dp)
    }

    // Não-regressão — em repouso o primeiro chip continua a 16dp, agora por padding do conteúdo.
    @Test
    fun keepsTheFirstChipSixteenDpFromTheStartEdgeAtRest() {
        setOverflowingContent()

        composeTestRule.onNodeWithText(firstChipLabel).assertLeftPositionInRootIsEqualTo(padding)
    }

    // Não-regressão — sem overflow nada muda: é o caso em que o fix precisa ser imperceptível.
    @Test
    fun keepsTheGeometryWhenTheContentDoesNotOverflow() {
        composeTestRule.setContent {
            OceanFilterBar(
                modifier = Modifier
                    .testTag(filterBarTag)
                    .width(barWidth),
                filterList = listOf(chip(id = "0", label = firstChipLabel)),
                showDividerBeforeLastItem = false
            )
        }

        composeTestRule.onNodeWithTag(filterBarTag).assertHeightIsEqualTo(64.dp)
        composeTestRule.onNodeWithText(firstChipLabel).assertLeftPositionInRootIsEqualTo(padding)
    }

    // Correção — em repouso o conteúdo é visível até a borda REAL do fim do container.
    // Antes do fix, o chip que começa dentro da faixa final de 16dp não aparecia de jeito nenhum:
    // a moldura clipava em (largura − 16) e ele nascia depois disso.
    @Test
    fun clipsTheContentAtTheRealEndBorderInsteadOfSixteenDpEarlier() {
        setOverflowingContent()

        val (label, unclipped) = chipStraddling(border = barWidth.value)
        val clipped = composeTestRule.onNodeWithText(label).getBoundsInRoot()

        composeTestRule.onNodeWithText(label).assertIsDisplayed()
        Assert.assertTrue(
            "o chip precisa transbordar a borda para o cenário valer",
            unclipped.right.value > barWidth.value
        )
        Assert.assertEquals(barWidth.value, clipped.right.value, 0.5f)
    }

    // Correção — rolado até o fim, o conteúdo é visível até a borda REAL do início do container.
    // Antes do fix, o clip parava em 16dp e o chip aparecia recortado por dentro da moldura.
    @Test
    fun clipsTheScrolledContentAtTheRealStartBorderInsteadOfSixteenDpLater() {
        setOverflowingContent()
        scrollToEnd()

        val (label, unclipped) = chipStraddling(border = 0f)
        val clipped = composeTestRule.onNodeWithText(label).getBoundsInRoot()

        Assert.assertTrue(
            "o chip precisa transbordar a borda para o cenário valer",
            unclipped.left.value < 0f
        )
        Assert.assertEquals(0f, clipped.left.value, 0.5f)
    }

    // Guarda do respiro do fim: rolado até o fim, sobram 16dp depois do último chip.
    // Não distingue a moldura (o número é o mesmo nas duas versões), mas fica vermelho se
    // alguém tirar o padding do lado final do conteúdo.
    @Test
    fun leavesSixteenDpOfRoomAfterTheLastChipAtTheEndOfTheScroll() {
        setOverflowingContent()
        scrollToEnd()

        val barBounds = composeTestRule.onNodeWithTag(filterBarTag).getBoundsInRoot()
        val lastChipBounds = composeTestRule.onNodeWithText(lastChipLabel).getBoundsInRoot()

        composeTestRule.onNodeWithText(lastChipLabel).assertIsDisplayed()
        Assert.assertEquals(
            padding.value,
            (barBounds.right - lastChipBounds.right).value,
            0.5f
        )
    }
}
