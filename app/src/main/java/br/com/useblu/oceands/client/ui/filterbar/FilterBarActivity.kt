package br.com.useblu.oceands.client.ui.filterbar

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

/**
 * Amostra do [OceanFilterBar] na galeria.
 *
 * São duas: a primeira **precisa** ter chips que estourem a largura da tela, porque é a única
 * que exercita o scroll — sem overflow não dá para ver o padding rolando junto do conteúdo
 * (último chip integral com respiro no fim, e nenhuma moldura fixa durante o movimento).
 * A segunda é curta de propósito: prova que sem overflow o visual continua idêntico.
 */
class FilterBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(verticalArrangement = Arrangement.spacedBy(OceanSpacing.sm)) {
                SampleLabel(text = "Com overflow (showDividerBeforeLastItem = true)")
                OceanFilterBar(filterList = overflowingChips)

                SampleLabel(text = "Sem overflow (showDividerBeforeLastItem = false)")
                OceanFilterBar(
                    filterList = shortChips,
                    showDividerBeforeLastItem = false
                )
            }
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
}
