package br.com.useblu.oceands.client.ui.segmentedcontrol

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanSegmentedControl
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.model.compose.OceanSegmentedControlOption
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

/**
 * Amostra do [OceanSegmentedControl] em Compose. Cada exemplo tem uma linha `propriedade: valor`.
 *
 * Toque e segure sobre um controle para ver o estado pressionado (fundo e seleção mais fortes); ao
 * trocar de opção, o fundo de seleção desliza. Com "Remover animações" ligado no sistema, a troca é
 * imediata.
 */
class SegmentedControlComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(OceanColors.interfaceLightPure)
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = OceanSpacing.xs),
                verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
            ) {
                SampleLabel(text = "Loja | Rede · selectedIndex: 0")
                Sample(
                    options = listOf(
                        OceanSegmentedControlOption("Loja"),
                        OceanSegmentedControlOption("Rede")
                    )
                )

                SampleLabel(text = "selectedIndex: 1")
                Sample(
                    options = listOf(
                        OceanSegmentedControlOption("Option 1"),
                        OceanSegmentedControlOption("Option 2")
                    ),
                    initialSelectedIndex = 1
                )

                SampleLabel(text = "icon: VIEW_LIST_OUTLINE / VIEW_GRID_OUTLINE")
                Sample(
                    options = listOf(
                        OceanSegmentedControlOption("Lista", icon = OceanIcons.VIEW_LIST_OUTLINE),
                        OceanSegmentedControlOption("Grade", icon = OceanIcons.VIEW_GRID_OUTLINE)
                    )
                )

                SampleLabel(text = "counter: 3 / 12")
                Sample(
                    options = listOf(
                        OceanSegmentedControlOption("Pendentes", counter = 3),
                        OceanSegmentedControlOption("Pagas", counter = 12)
                    )
                )

                SampleLabel(text = "icon: SHOPPING_BAG_OUTLINE · counter: 5 / 120 (mostra 99+)")
                Sample(
                    options = listOf(
                        OceanSegmentedControlOption("Loja", icon = OceanIcons.SHOPPING_BAG_OUTLINE, counter = 5),
                        OceanSegmentedControlOption("Rede", icon = OceanIcons.OFFICE_BUILDING_OUTLINE, counter = 120)
                    )
                )

                SampleLabel(text = "label: texto longo · largura disponível: 240dp (corta com …)")
                Sample(
                    modifier = Modifier.width(240.dp),
                    options = listOf(
                        OceanSegmentedControlOption("Todas as lojas da rede", counter = 8),
                        OceanSegmentedControlOption("Rede")
                    )
                )

                SampleLabel(text = "label: dois textos longos · largura disponível: 240dp")
                Sample(
                    modifier = Modifier.width(240.dp),
                    options = listOf(
                        OceanSegmentedControlOption("Vendas do mês corrente"),
                        OceanSegmentedControlOption("Vendas do mês anterior")
                    )
                )

                SampleLabel(text = "options: 3 itens (só os 2 primeiros são desenhados)")
                Sample(
                    options = listOf(
                        OceanSegmentedControlOption("Dia"),
                        OceanSegmentedControlOption("Semana"),
                        OceanSegmentedControlOption("Mês")
                    )
                )
            }
        }
    }

    @Composable
    private fun SampleLabel(text: String) {
        OceanText(
            text = text,
            modifier = Modifier.padding(horizontal = OceanSpacing.xs, vertical = OceanSpacing.xxs),
            style = OceanTextStyle.description,
            color = OceanColors.interfaceDarkDown
        )
    }

    @Composable
    private fun Sample(
        options: List<OceanSegmentedControlOption>,
        modifier: Modifier = Modifier,
        initialSelectedIndex: Int = 0
    ) {
        var selected by remember { mutableIntStateOf(initialSelectedIndex) }
        // O controle fica do tamanho do conteúdo; [modifier] limita a largura disponível.
        Column(modifier = Modifier.padding(horizontal = OceanSpacing.xs).then(modifier)) {
            OceanSegmentedControl(
                options = options,
                selectedIndex = selected,
                onSelectedOption = { selected = it }
            )
        }
    }
}
