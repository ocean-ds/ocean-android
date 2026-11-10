package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.Badge
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanBasicChip
import br.com.useblu.oceands.model.OceanChip
import br.com.useblu.oceands.model.OceanChipFilterOptions
import br.com.useblu.oceands.model.OceanChipItemState
import br.com.useblu.oceands.model.OceanFilterChip
import br.com.useblu.oceands.model.compose.OceanFilterBarSegmentedControl
import br.com.useblu.oceands.model.compose.OceanSegmentedControlOption
import br.com.useblu.oceands.ui.compose.OceanBorderWidth
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons

@Composable
@Preview
fun OceanFilterBarPreview() {
    OceanFilterBar(
        filterList = listOf(
            OceanBasicChip(
                id = "0",
                label = "Todos",
                badge = Badge(
                    text = 2,
                    type = OceanBadgeType.PRIMARY_INVERTED
                ),
                state = OceanChipItemState.DEFAULT_ACTIVE,
                onClick = {}
            ),
            OceanBasicChip(
                id = "1",
                label = "Pendentes",
                badge = Badge(
                    text = 2,
                    type = OceanBadgeType.PRIMARY
                ),
                state = OceanChipItemState.HOVER_INACTIVE,
                onClick = {}
            ),
            OceanFilterChip(
                id = "2",
                label = "Canceladas",
                badge = null,
                state = OceanChipItemState.HOVER_INACTIVE,
                filterOptions = OceanChipFilterOptions.SingleChoice("", emptyList(), {})
            ),
            OceanBasicChip(
                id = "3",
                label = "Todos os filtros",
                badge = Badge(
                    text = 2,
                    type = OceanBadgeType.PRIMARY
                ),
                icon = OceanIcons.ADJUSTMENTS_OUTLINE,
                state = OceanChipItemState.HOVER_INACTIVE,
                onClick = {}
            )
        )
    )
}

@Composable
@Preview(widthDp = 393)
private fun OceanFilterBarWithSegmentedControlPreview() {
    OceanFilterBar(
        filterList = listOf(
            OceanBasicChip(
                id = "0",
                label = "Todos",
                state = OceanChipItemState.DEFAULT_ACTIVE,
                onClick = {}
            ),
            OceanBasicChip(
                id = "1",
                label = "Pendentes",
                state = OceanChipItemState.DEFAULT_INACTIVE,
                onClick = {}
            ),
            OceanBasicChip(
                id = "2",
                label = "Todos os filtros",
                icon = OceanIcons.ADJUSTMENTS_OUTLINE,
                state = OceanChipItemState.DEFAULT_INACTIVE,
                onClick = {}
            )
        ),
        segmentedControl = OceanFilterBarSegmentedControl(
            options = listOf(
                OceanSegmentedControlOption("Loja"),
                OceanSegmentedControlOption("Rede")
            ),
            selectedIndex = 0,
            onSelectedOption = {}
        )
    )
}

internal const val OCEAN_FILTER_BAR_SEGMENTED_CONTROL_TAG = "oceanFilterBarSegmentedControl"
internal const val OCEAN_FILTER_BAR_SEGMENTED_DIVIDER_TAG = "oceanFilterBarSegmentedDivider"

// Altura da linha dos chips (a altura do chip). Somada aos 16dp ([OceanSpacing.xs]) em cima e
// embaixo, dá os 64dp da barra. Não existe token de tamanho de componente: literal, como no chip.
private val FilterBarRowHeight = 32.dp

/**
 * Filter Bar do Ocean (Figma: *Filter Bar*, plataforma Mobile).
 *
 * Barra branca de 64dp de altura com os filtros de uma lista: chips ([OceanChip]), o último
 * normalmente o "Todos os filtros", e, opcionalmente, um Segmented Control fixo à direita
 * ([segmentedControl]) para trocar o escopo da mesma lista (ex.: Loja | Rede). A barra deve ter pelo
 * menos um chip (regra de uso: a API aceita a lista vazia por compatibilidade).
 *
 * **Sem [segmentedControl]** (padrão): todos os chips rolam
 * juntos na horizontal, com 16dp ([OceanSpacing.xs]) de respiro nas duas pontas do conteúdo rolável
 * e 8dp ([OceanSpacing.xxs]) entre os chips. Com [showDividerBeforeLastItem] `true`, há um divisor
 * antes do último chip.
 *
 * **Com [segmentedControl]**:
 * - os chips continuam rolando juntos, numa área que ocupa o espaço livre da barra e é cortada
 *   colada no divisor; 16dp ([OceanSpacing.xs]) antes do primeiro chip, 8dp ([OceanSpacing.xxs])
 *   entre os chips e 16dp ([OceanSpacing.xs]) depois do último (só aparece ao rolar até o fim);
 * - não há divisor antes do último chip: [showDividerBeforeLastItem] é ignorado;
 * - fixos à direita, fora da rolagem: divisor ([OceanBorderWidth.Hairline],
 *   `ocean_color_interface_light_down`, 32dp de altura), 8dp ([OceanSpacing.xxs]), o
 *   [OceanSegmentedControl] (fundo [OceanSegmentedControlBackground.Default], 40dp de altura) e
 *   16dp ([OceanSpacing.xs]) até a borda direita;
 * - o Segmented aparece só com texto: ícone e contador das opções são ignorados;
 * - a altura da barra continua 64dp.
 *
 * Texto longo: chip e opção do Segmented ficam sempre em uma linha. O chip longo continua inteiro e
 * a área dos chips rola. O Segmented ocupa no máximo metade da largura da barra; passando disso, o
 * texto das opções é cortado com "…" (comportamento do [OceanSegmentedControl]). Assim a área dos
 * chips nunca some: sobra sempre pelo menos a outra metade, menos o divisor e os espaços.
 *
 * Acessibilidade: o leitor de tela lê os chips na ordem e, por último, o Segmented (grupo de duas
 * opções com a selecionada marcada).
 *
 * @param modifier [Modifier] aplicado à barra.
 * @param filterList chips, na ordem de exibição.
 * @param showDividerBeforeLastItem `true` (padrão) mostra um divisor antes do último chip. Ignorado
 * quando há [segmentedControl].
 * @param segmentedControl Segmented Control fixo à direita. `null` (padrão) não mostra o Segmented:
 * todos os chips rolam juntos e vale [showDividerBeforeLastItem]. Ver [OceanFilterBarSegmentedControl].
 */
@Composable
fun OceanFilterBar(
    modifier: Modifier = Modifier,
    filterList: List<OceanChip>,
    showDividerBeforeLastItem: Boolean = true,
    segmentedControl: OceanFilterBarSegmentedControl? = null
) {
    if (segmentedControl == null) {
        OceanFilterBarScrollingChips(
            modifier = modifier,
            filterList = filterList,
            showDividerBeforeLastItem = showDividerBeforeLastItem
        )
    } else {
        OceanFilterBarWithSegmentedControl(
            modifier = modifier,
            filterList = filterList,
            segmentedControl = segmentedControl
        )
    }
}

/** Barra sem Segmented: todos os chips rolam juntos, com divisor opcional antes do último. */
@Composable
private fun OceanFilterBarScrollingChips(
    modifier: Modifier,
    filterList: List<OceanChip>,
    showDividerBeforeLastItem: Boolean
) {
    Column(
        modifier = modifier
            .background(color = colorResource(R.color.ocean_color_interface_light_pure))
            .padding(vertical = OceanSpacing.xs)
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                // Depois do horizontalScroll: o padding entra no conteúdo rolável e rola junto
                // dele. O clip do scroll passa a ser a borda real do container, e os 16dp viram
                // respiro nas pontas do conteúdo em vez de moldura fixa do viewport.
                .padding(horizontal = OceanSpacing.xs)
                .height(32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            filterList.forEachIndexed { index, itemModel ->
                if (index == filterList.size - 1 && showDividerBeforeLastItem) {
                    VerticalDivider(
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight(),
                        thickness = OceanBorderWidth.Hairline.value,
                        color = colorResource(R.color.ocean_color_interface_light_down)
                    )
                }
                OceanChip(
                    model = itemModel
                )
            }
        }
    }
}

/**
 * Barra com Segmented (Figma 1.1.0, Mobile): chips rolando no espaço livre, cortados colados no
 * divisor; divisor · 8 · Segmented · 16 fixos à direita.
 */
@Composable
private fun OceanFilterBarWithSegmentedControl(
    modifier: Modifier,
    filterList: List<OceanChip>,
    segmentedControl: OceanFilterBarSegmentedControl
) {
    BoxWithConstraints(
        modifier = modifier
            .background(color = colorResource(R.color.ocean_color_interface_light_pure))
            .padding(vertical = OceanSpacing.xs)
    ) {
        // O Segmented ocupa no máximo metade da barra, para a área dos chips nunca sumir.
        val segmentedMaxWidth = if (constraints.hasBoundedWidth) maxWidth / 2 else Dp.Infinity

        Row(
            modifier = Modifier
                .height(FilterBarRowHeight)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    // Ocupa o espaço que sobra depois do divisor e do Segmented: o clip do scroll
                    // fica na borda esquerda do divisor (0dp entre o corte e o divisor).
                    .weight(1f)
                    .horizontalScroll(rememberScrollState())
                    // Dentro do conteúdo rolável: 16dp antes do primeiro chip e 16dp de respiro
                    // depois do último, que só aparecem nas pontas da rolagem.
                    .padding(horizontal = OceanSpacing.xs)
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Sem divisor antes do último chip: com o Segmented, o único divisor é o da direita.
                filterList.forEach { itemModel ->
                    OceanChip(model = itemModel)
                }
            }

            VerticalDivider(
                modifier = Modifier
                    .width(OceanBorderWidth.Hairline.value)
                    .fillMaxHeight()
                    .testTag(OCEAN_FILTER_BAR_SEGMENTED_DIVIDER_TAG),
                thickness = OceanBorderWidth.Hairline.value,
                color = colorResource(R.color.ocean_color_interface_light_down)
            )

            OceanSegmentedControl(
                modifier = Modifier
                    .padding(start = OceanSpacing.xxs, end = OceanSpacing.xs)
                    // O Segmented tem 40dp e a linha 32dp: ele transborda 4dp para o padding de
                    // 16dp em cima e embaixo, centralizado, e a barra continua com 64dp.
                    .wrapContentHeight(unbounded = true)
                    // Passando da metade, as opções encolhem e o texto é cortado com "…".
                    .widthIn(max = segmentedMaxWidth)
                    .testTag(OCEAN_FILTER_BAR_SEGMENTED_CONTROL_TAG),
                options = segmentedControl.textOnlyOptions(),
                selectedIndex = segmentedControl.selectedIndex,
                enabled = segmentedControl.enabled,
                background = OceanSegmentedControlBackground.Default,
                onSelectedOption = segmentedControl.onSelectedOption
            )
        }
    }
}

/** No Filter Bar (mobile) o Segmented aparece só com texto: ícone e contador saem das opções. */
internal fun OceanFilterBarSegmentedControl.textOnlyOptions(): List<OceanSegmentedControlOption> =
    options.map { it.copy(icon = null, counter = null) }
