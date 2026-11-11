package br.com.useblu.oceands.client.ui.filterbar

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.OceanFilterBar
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.model.Badge
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanBasicChip
import br.com.useblu.oceands.model.OceanChip
import br.com.useblu.oceands.model.OceanChipFilterOptions
import br.com.useblu.oceands.model.OceanChipItemState
import br.com.useblu.oceands.model.OceanFilterChip
import br.com.useblu.oceands.model.compose.OceanFilterBarSegmentedControl
import br.com.useblu.oceands.model.compose.OceanSegmentedControlOption
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

/**
 * Amostra do [OceanFilterBar] na galeria. Cada exemplo tem uma linha `propriedade: valor`.
 *
 * Sem Segmented, a primeira amostra **precisa** ter chips que estourem a largura da tela, porque é
 * a única que exercita o scroll — sem overflow não dá para ver o padding rolando junto do conteúdo
 * (último chip integral com respiro no fim, e nenhuma moldura fixa durante o movimento). A segunda
 * é curta de propósito: prova que sem overflow o visual continua idêntico.
 *
 * Com Segmented (`segmentedControl`), o Segmented fica fixo à direita depois de um divisor e só os
 * chips rolam, cortados colados no divisor. O Segmented ocupa no máximo metade da barra (texto longo
 * cortado com "…") e aparece só com texto (uma amostra passa ícone e contador de propósito, e eles
 * não aparecem).
 */
class FilterBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier
                    .systemBarsPadding()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(OceanSpacing.sm)
            ) {
                SampleLabel(text = "segmentedControl: null · showDividerBeforeLastItem: true (com overflow)")
                OceanFilterBar(
                    filterList = rememberToggleableChips(overflowingChips, OceanChipItemState.HOVER_INACTIVE)
                )

                SampleLabel(text = "segmentedControl: null · showDividerBeforeLastItem: false (sem overflow)")
                OceanFilterBar(
                    filterList = rememberToggleableChips(shortChips, OceanChipItemState.HOVER_INACTIVE),
                    showDividerBeforeLastItem = false
                )

                SampleLabel(text = "segmentedControl: Loja | Rede · com \"Todos os filtros\"")
                SegmentedSample(filterList = segmentedShortChips + allFiltersChip)

                SampleLabel(text = "segmentedControl: Loja | Rede · sem \"Todos os filtros\"")
                SegmentedSample(filterList = segmentedShortChips)

                SampleLabel(text = "segmentedControl: options com icon e counter (descartados: só texto)")
                SegmentedSample(
                    filterList = segmentedShortChips + allFiltersChip,
                    options = storeAndNetworkWithIconAndCounter
                )

                SampleLabel(text = "segmentedControl: Loja | Rede · muitos chips (corte no divisor)")
                SegmentedSample(filterList = segmentedOverflowingChips)

                SampleLabel(text = "segmentedControl: texto longo · Segmented até metade da barra, com …")
                SegmentedSample(
                    filterList = longLabelChips,
                    options = listOf(
                        OceanSegmentedControlOption("Todas as lojas da rede"),
                        OceanSegmentedControlOption("Rede")
                    )
                )

                SampleLabel(text = "segmentedControl: enabled: false")
                SegmentedSample(filterList = segmentedShortChips + allFiltersChip, enabled = false)
            }
        }
    }

    @Composable
    private fun SegmentedSample(
        filterList: List<OceanChip>,
        options: List<OceanSegmentedControlOption> = storeAndNetwork,
        enabled: Boolean = true
    ) {
        var selectedIndex by remember { mutableIntStateOf(0) }

        OceanFilterBar(
            filterList = rememberToggleableChips(filterList, OceanChipItemState.DEFAULT_INACTIVE),
            segmentedControl = OceanFilterBarSegmentedControl(
                options = options,
                selectedIndex = selectedIndex,
                enabled = enabled,
                onSelectedOption = { selectedIndex = it }
            )
        )
    }

    /**
     * Guarda a seleção dos chips da amostra: o toque num [OceanBasicChip] liga e desliga o chip
     * ([OceanChipItemState.DEFAULT_ACTIVE] ↔ estado inativo), como na demo do iOS. O chip não guarda
     * estado sozinho (ele só chama `onClick`); quem guarda é a tela. Desligado, o chip volta ao estado
     * inativo da própria lista, ou a [inactiveState] se começou ligado. O [OceanFilterChip] continua
     * abrindo as opções.
     */
    @Composable
    private fun rememberToggleableChips(
        chips: List<OceanChip>,
        inactiveState: OceanChipItemState
    ): List<OceanChip> {
        val selected = remember(chips) {
            mutableStateMapOf<String, Boolean>().apply {
                chips.forEach { put(it.id, it.state == OceanChipItemState.DEFAULT_ACTIVE) }
            }
        }

        return chips.map { chip ->
            // "Todos os filtros" é um botão (abre os filtros na tela), não um chip que fica marcado.
            if (chip !is OceanBasicChip || chip.icon == OceanIcons.ADJUSTMENTS_OUTLINE) return@map chip

            val isSelected = selected[chip.id] == true
            chip.copy(
                state = when {
                    isSelected -> OceanChipItemState.DEFAULT_ACTIVE
                    chip.state != OceanChipItemState.DEFAULT_ACTIVE -> chip.state
                    else -> inactiveState
                },
                badge = chip.badge?.copy(
                    type = if (isSelected) OceanBadgeType.PRIMARY_INVERTED else OceanBadgeType.PRIMARY
                ),
                onClick = { selected[chip.id] = !isSelected }
            )
        }
    }

    @Composable
    private fun SampleLabel(text: String) {
        OceanText(
            text = text,
            modifier = Modifier.padding(horizontal = OceanSpacing.xs),
            style = OceanTextStyle.description,
            color = OceanColors.interfaceDarkDown
        )
    }

    private val storeAndNetwork = listOf(
        OceanSegmentedControlOption("Loja"),
        OceanSegmentedControlOption("Rede")
    )

    // Ícone e contador de propósito: no Filter Bar eles são ignorados e o Segmented fica só com texto.
    private val storeAndNetworkWithIconAndCounter = listOf(
        OceanSegmentedControlOption("Loja", icon = OceanIcons.SHOPPING_BAG_OUTLINE, counter = 3),
        OceanSegmentedControlOption("Rede", icon = OceanIcons.OFFICE_BUILDING_OUTLINE, counter = 12)
    )

    private val allFiltersChip = OceanBasicChip(
        id = "all",
        label = "Todos os filtros",
        badge = Badge(text = 2, type = OceanBadgeType.PRIMARY),
        icon = OceanIcons.ADJUSTMENTS_OUTLINE,
        state = OceanChipItemState.DEFAULT_INACTIVE,
        onClick = {}
    )

    private val longLabelChips: List<OceanChip> = listOf(
        OceanBasicChip(
            id = "0",
            label = "Aguardando confirmação do pagamento",
            state = OceanChipItemState.DEFAULT_ACTIVE,
            onClick = {}
        ),
        OceanBasicChip(
            id = "1",
            label = "Pendentes",
            state = OceanChipItemState.DEFAULT_INACTIVE,
            onClick = {}
        )
    )

    private val overflowingChips: List<OceanChip> = listOf(
        OceanBasicChip(
            id = "0",
            label = "Todos",
            badge = Badge(text = 12, type = OceanBadgeType.PRIMARY_INVERTED),
            state = OceanChipItemState.DEFAULT_ACTIVE,
            onClick = {}
        ),
        OceanBasicChip(
            id = "1",
            label = "Pendentes",
            badge = Badge(text = 4, type = OceanBadgeType.PRIMARY),
            state = OceanChipItemState.HOVER_INACTIVE,
            onClick = {}
        ),
        OceanBasicChip(
            id = "2",
            label = "Aguardando pagamento",
            state = OceanChipItemState.HOVER_INACTIVE,
            onClick = {}
        ),
        OceanBasicChip(
            id = "3",
            label = "Canceladas",
            state = OceanChipItemState.HOVER_INACTIVE,
            onClick = {}
        ),
        OceanBasicChip(
            id = "4",
            label = "Estornadas",
            state = OceanChipItemState.HOVER_INACTIVE,
            onClick = {}
        ),
        OceanFilterChip(
            id = "5",
            label = "Período",
            badge = null,
            state = OceanChipItemState.HOVER_INACTIVE,
            filterOptions = OceanChipFilterOptions.SingleChoice("", emptyList(), {})
        ),
        OceanBasicChip(
            id = "6",
            label = "Todos os filtros",
            badge = Badge(text = 2, type = OceanBadgeType.PRIMARY),
            icon = OceanIcons.ADJUSTMENTS_OUTLINE,
            state = OceanChipItemState.HOVER_INACTIVE,
            onClick = {}
        )
    )

    private val shortChips: List<OceanChip> = listOf(
        OceanBasicChip(
            id = "0",
            label = "Todos",
            state = OceanChipItemState.DEFAULT_ACTIVE,
            onClick = {}
        ),
        OceanBasicChip(
            id = "1",
            label = "Abertos",
            state = OceanChipItemState.HOVER_INACTIVE,
            onClick = {}
        )
    )

    // Listas das linhas com Segmented: mesmos textos de [shortChips] e [overflowingChips], no estado
    // padrão (ativo DEFAULT_ACTIVE, demais DEFAULT_INACTIVE), sem o estado de hover.
    private val segmentedShortChips: List<OceanChip> = listOf(
        OceanBasicChip(
            id = "0",
            label = "Todos",
            state = OceanChipItemState.DEFAULT_ACTIVE,
            onClick = {}
        ),
        OceanBasicChip(
            id = "1",
            label = "Abertos",
            state = OceanChipItemState.DEFAULT_INACTIVE,
            onClick = {}
        )
    )

    private val segmentedOverflowingChips: List<OceanChip> = listOf(
        OceanBasicChip(
            id = "0",
            label = "Todos",
            badge = Badge(text = 12, type = OceanBadgeType.PRIMARY_INVERTED),
            state = OceanChipItemState.DEFAULT_ACTIVE,
            onClick = {}
        ),
        OceanBasicChip(
            id = "1",
            label = "Pendentes",
            badge = Badge(text = 4, type = OceanBadgeType.PRIMARY),
            state = OceanChipItemState.DEFAULT_INACTIVE,
            onClick = {}
        ),
        OceanBasicChip(
            id = "2",
            label = "Aguardando pagamento",
            state = OceanChipItemState.DEFAULT_INACTIVE,
            onClick = {}
        ),
        OceanBasicChip(
            id = "3",
            label = "Canceladas",
            state = OceanChipItemState.DEFAULT_INACTIVE,
            onClick = {}
        ),
        OceanBasicChip(
            id = "4",
            label = "Estornadas",
            state = OceanChipItemState.DEFAULT_INACTIVE,
            onClick = {}
        ),
        OceanFilterChip(
            id = "5",
            label = "Período",
            badge = null,
            state = OceanChipItemState.DEFAULT_INACTIVE,
            filterOptions = OceanChipFilterOptions.SingleChoice("", emptyList(), {})
        ),
        OceanBasicChip(
            id = "6",
            label = "Todos os filtros",
            badge = Badge(text = 2, type = OceanBadgeType.PRIMARY),
            icon = OceanIcons.ADJUSTMENTS_OUTLINE,
            state = OceanChipItemState.DEFAULT_INACTIVE,
            onClick = {}
        )
    )
}
