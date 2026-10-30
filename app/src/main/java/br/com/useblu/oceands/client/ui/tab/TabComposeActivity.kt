package br.com.useblu.oceands.client.ui.tab

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanScrollableTab
import br.com.useblu.oceands.components.compose.OceanTab
import br.com.useblu.oceands.components.compose.OceanTabScrollable
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.compose.OceanTabItemModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

/**
 * Amostra do Tab em Compose ([OceanTab], [OceanTabScrollable] e [OceanScrollableTab]).
 *
 * O [TabActivity] continua mostrando a versão antiga em XML. Aqui, cada exemplo tem uma linha
 * `propriedade: valor`. O exemplo de texto longo só faz sentido em telas estreitas (360dp): é onde
 * a barra fixa rola em vez de quebrar o texto em duas linhas.
 */
class TabComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(OceanColors.interfaceLightPure)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
            ) {
                SampleLabel(text = "OceanTab · tabs: 2")
                FixedSample(tabs = listOf(OceanTabItemModel("Vendas"), OceanTabItemModel("Recebíveis")))

                SampleLabel(text = "OceanTab · tabs: 3")
                FixedSample(
                    tabs = listOf(
                        OceanTabItemModel("Hoje"),
                        OceanTabItemModel("Semana"),
                        OceanTabItemModel("Mês")
                    )
                )

                SampleLabel(text = "OceanTab · label: \"Pague com seu limite\" (texto longo: rola)")
                FixedSample(
                    tabs = listOf(
                        OceanTabItemModel("Pague com seu limite"),
                        OceanTabItemModel("Antecipação")
                    )
                )

                SampleLabel(text = "OceanTab · counter: 3 · badgeType: PRIMARY / WARNING")
                FixedSample(
                    tabs = listOf(
                        OceanTabItemModel("Vendas", counter = 3),
                        OceanTabItemModel("Pendências", counter = 12, badgeType = OceanBadgeType.WARNING),
                        OceanTabItemModel("Pagas")
                    )
                )

                SampleLabel(text = "OceanTabScrollable · tabs: 5")
                ScrollableSample(tabs = (1..5).map { OceanTabItemModel("Seção $it") })

                SampleLabel(text = "OceanTabScrollable · counter: 1..5 · badgeType: PRIMARY / WARNING")
                ScrollableSample(
                    tabs = (1..5).map {
                        OceanTabItemModel(
                            label = "Seção $it",
                            counter = it,
                            badgeType = if (it == 2) OceanBadgeType.WARNING else OceanBadgeType.PRIMARY
                        )
                    }
                )

                SampleLabel(text = "OceanScrollableTab · headerContent: sim · content: 4 seções")
                ScrollableTabScreenSample()
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
    private fun FixedSample(tabs: List<OceanTabItemModel>) {
        var selected by remember { mutableIntStateOf(0) }
        OceanTab(
            tabs = tabs,
            selectedTabIndex = selected,
            onSelectedTab = { selected = it }
        )
    }

    @Composable
    private fun ScrollableSample(tabs: List<OceanTabItemModel>) {
        var selected by remember { mutableIntStateOf(0) }
        OceanTabScrollable(
            tabs = tabs,
            defaultSelectedTab = selected,
            onSelectedTab = { selected = it }
        )
    }

    @Composable
    private fun ScrollableTabScreenSample() {
        var selected by remember { mutableIntStateOf(0) }
        val tabs = (1..4).map { OceanTabItemModel("Seção $it", counter = it) }

        OceanScrollableTab(
            // Altura fixa: a amostra fica dentro de uma coluna que também rola.
            modifier = Modifier.height(480.dp),
            tabs = tabs,
            defaultSelectedTab = selected,
            onSelectedTab = { selected = it },
            headerContent = {
                OceanText(
                    text = "Cabeçalho que rola junto",
                    modifier = Modifier.padding(OceanSpacing.xs),
                    style = OceanTextStyle.heading4
                )
            },
            content = {
                tabs.forEach { tab ->
                    item {
                        Column(modifier = Modifier.padding(OceanSpacing.xs)) {
                            repeat(12) { line ->
                                OceanText(
                                    text = "${tab.label} · linha ${line + 1}",
                                    modifier = Modifier.padding(vertical = OceanSpacing.xxs)
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}
