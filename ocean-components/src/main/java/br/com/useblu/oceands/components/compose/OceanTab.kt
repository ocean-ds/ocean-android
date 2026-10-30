package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabIndicatorScope
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.compose.OceanTabItemModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

// Altura do item no Figma (_Tab Item). Não existe token de tamanho para ela: fica literal até o
// Ocean ganhar um token de altura de componente.
private val TabItemHeight = 56.dp

// Espessura da linha de seleção no Figma. Sem token de stroke/border width no Ocean: literal.
private val TabIndicatorHeight = 2.dp

// Espessura da linha de baixo da barra no Figma. Sem token de stroke/border width no Ocean: literal.
private val TabDividerThickness = 1.dp

internal const val OCEAN_TAB_DIVIDER_TAG = "oceanTabDivider"

private enum class OceanTabSlot { Measure, Content }

@Preview
@Composable
fun OceanTabPreview() {
    val tabs = listOf(
        OceanTabItemModel("Tab 1", 1),
        OceanTabItemModel("Tab 2", 2),
        OceanTabItemModel("Tab 3", 3)
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    MaterialTheme {
        Column(
            Modifier
                .background(color = OceanColors.interfaceLightPure)
                .height(200.dp)
        ) {
            OceanTab(
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
                onSelectedTab = { selectedTabIndex = it }
            )
        }
    }
}

@Preview(widthDp = 320)
@Composable
private fun OceanTabLongLabelPreview() {
    val tabs = listOf(
        OceanTabItemModel("Pague com seu limite", 1),
        OceanTabItemModel("Antecipação")
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    MaterialTheme {
        OceanTab(
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            onSelectedTab = { selectedTabIndex = it }
        )
    }
}

@Preview
@Composable
private fun OceanTabScrollablePreview() {
    val tabs = listOf(
        OceanTabItemModel("Tab 1", 1),
        OceanTabItemModel("Tab 2", 2, OceanBadgeType.WARNING),
        OceanTabItemModel("Tab 3", 3),
        OceanTabItemModel("Tab 4", 4),
        OceanTabItemModel("Tab 5", 5)
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    MaterialTheme {
        Column(
            Modifier
                .background(color = OceanColors.interfaceLightPure)
                .height(200.dp)
        ) {
            OceanTabScrollable(
                tabs = tabs,
                defaultSelectedTab = selectedTabIndex,
                onSelectedTab = { selectedTabIndex = it }
            )
        }
    }
}

/**
 * Barra de abas fixa do Ocean (Figma: *Tab Bar*, `With scroll=No`).
 *
 * Separa o conteúdo de uma tela em seções do mesmo nível, uma visível por vez. Use de 2 a 8 abas;
 * aba indisponível não aparece (não existe aba desabilitada).
 *
 * Largura dos itens:
 * - quando o texto de **todas** as abas cabe na sua fração da largura, as abas dividem a largura
 *   em partes iguais;
 * - quando o texto de **alguma** aba não cabe, cada aba fica do tamanho do seu conteúdo e a barra
 *   rola para o lado, com 32dp ([OceanSpacing.md]) de margem nas laterais.
 *
 * O texto ([OceanTextStyle.heading4]) fica sempre em uma linha: nunca quebra nem é cortado. Por
 * isso não é preciso medir a largura mínima nem envolver a barra em `horizontalScroll` no app.
 *
 * Visual: item de 56dp de altura, 16dp ([OceanSpacing.xs]) nas laterais, 8dp ([OceanSpacing.xxs])
 * entre texto e contador, linha de seleção de 2dp em [OceanColors.brandPrimaryPure] e linha de
 * baixo de 1dp em [OceanColors.interfaceLightDown] em toda a largura. Texto e contador ficam em
 * [OceanColors.brandPrimaryPure] na aba selecionada e enquanto a aba é pressionada.
 *
 * @param modifier [Modifier] aplicado à barra.
 * @param tabs abas, na ordem de exibição. Ver [OceanTabItemModel].
 * @param selectedTabIndex índice da aba selecionada ao compor. A barra guarda a seleção feita pelo
 * toque até o valor mudar.
 * @param onSelectedTab chamado com o índice da aba tocada.
 */
@Composable
fun OceanTab(
    modifier: Modifier = Modifier,
    tabs: List<OceanTabItemModel>,
    selectedTabIndex: Int,
    onSelectedTab: (Int) -> Unit
) {
    var selectedTabIndexInternal by remember(selectedTabIndex) {
        mutableIntStateOf(selectedTabIndex)
    }

    val onClickTab: (Int) -> Unit = { index ->
        onSelectedTab(index)
        selectedTabIndexInternal = index
    }

    // Margem lateral quando a barra fixa rola: a mesma da barra com rolagem no Figma (mobile).
    val edgePadding = OceanSpacing.md

    SubcomposeLayout(
        modifier = modifier
            .background(color = OceanColors.interfaceLightPure)
            .fillMaxWidth()
    ) { constraints ->
        val edgePaddingPx = edgePadding.roundToPx()

        // Mede o conteúdo de cada aba em uma linha, sem limite de largura. Esta cópia só é medida
        // (nunca posicionada) e fica fora da árvore de acessibilidade.
        val itemWidths = subcompose(OceanTabSlot.Measure) {
            tabs.forEach { tab ->
                OceanTabItemContent(
                    tab = tab,
                    highlighted = true,
                    modifier = Modifier.clearAndSetSemantics {}
                )
            }
        }.map { it.maxIntrinsicWidth(Constraints.Infinity) }

        // Dentro de um container sem limite de largura (ex.: horizontalScroll do app), a barra
        // ocupa o maior valor entre a largura mínima recebida e a soma do conteúdo com as margens.
        val rowWidth = if (constraints.hasBoundedWidth) {
            constraints.maxWidth
        } else {
            maxOf(constraints.minWidth, itemWidths.sum() + 2 * edgePaddingPx)
        }

        val equalItemWidth = if (tabs.isEmpty()) 0 else rowWidth / tabs.size
        val allLabelsFit = itemWidths.all { it <= equalItemWidth }

        val placeables = subcompose(OceanTabSlot.Content) {
            if (allLabelsFit) {
                OceanFixedTabRow(
                    tabs = tabs,
                    selectedTabIndex = selectedTabIndexInternal,
                    onClickTab = onClickTab
                )
            } else {
                OceanScrollingTabRow(
                    tabs = tabs,
                    selectedTabIndex = selectedTabIndexInternal,
                    edgePadding = edgePadding,
                    minTabWidth = 0.dp,
                    onClickTab = onClickTab
                )
            }
        }.map {
            it.measure(
                constraints.copy(minWidth = rowWidth, maxWidth = rowWidth)
            )
        }

        val height = placeables.maxOfOrNull { it.height } ?: 0
        layout(rowWidth, height) {
            placeables.forEach { it.placeRelative(0, 0) }
        }
    }
}

/**
 * Barra de abas com rolagem do Ocean (Figma: *Tab Bar*, `Platform=Mobile, With scroll=Yes`).
 *
 * Cada aba fica do tamanho do seu conteúdo (16dp + texto + 16dp, sem largura mínima) e a barra
 * rola para o lado, com 32dp ([OceanSpacing.md]) de margem nas laterais. Use quando há 4 ou mais abas ou
 * quando o número de abas ou o tamanho dos textos varia.
 *
 * O visual do item e da linha de baixo é o mesmo de [OceanTab].
 *
 * @param modifier [Modifier] aplicado à barra.
 * @param tabs abas, na ordem de exibição. Ver [OceanTabItemModel].
 * @param defaultSelectedTab índice da aba selecionada. A barra não guarda estado: atualize o valor
 * em [onSelectedTab].
 * @param onSelectedTab chamado com o índice da aba tocada.
 */
@Composable
fun OceanTabScrollable(
    modifier: Modifier = Modifier,
    tabs: List<OceanTabItemModel>,
    defaultSelectedTab: Int,
    onSelectedTab: (Int) -> Unit
) {
    OceanScrollingTabRow(
        modifier = modifier
            .background(color = OceanColors.interfaceLightPure)
            .fillMaxWidth(),
        tabs = tabs,
        selectedTabIndex = defaultSelectedTab,
        edgePadding = OceanSpacing.md,
        // Item do tamanho do conteúdo (16 + texto + 16), como o _Tab Item do Figma: sem o
        // mínimo de 90dp do Material.
        minTabWidth = 0.dp,
        onClickTab = onSelectedTab
    )
}

@Composable
private fun OceanFixedTabRow(
    tabs: List<OceanTabItemModel>,
    selectedTabIndex: Int,
    onClickTab: (Int) -> Unit
) {
    SecondaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.fillMaxWidth(),
        containerColor = OceanColors.interfaceLightPure,
        contentColor = OceanColors.brandPrimaryPure,
        indicator = { OceanTabIndicator(selectedTabIndex, tabs.size) },
        divider = { OceanTabDivider() }
    ) {
        OceanTabItems(tabs, selectedTabIndex, onClickTab)
    }
}

@Composable
private fun OceanScrollingTabRow(
    modifier: Modifier = Modifier,
    tabs: List<OceanTabItemModel>,
    selectedTabIndex: Int,
    edgePadding: Dp,
    minTabWidth: Dp,
    onClickTab: (Int) -> Unit
) {
    SecondaryScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        scrollState = rememberScrollState(),
        containerColor = OceanColors.interfaceLightPure,
        contentColor = OceanColors.brandPrimaryPure,
        edgePadding = edgePadding,
        indicator = { OceanTabIndicator(selectedTabIndex, tabs.size) },
        divider = { OceanTabDivider() },
        minTabWidth = minTabWidth
    ) {
        OceanTabItems(tabs, selectedTabIndex, onClickTab)
    }
}

@Composable
private fun TabIndicatorScope.OceanTabIndicator(selectedTabIndex: Int, tabCount: Int) {
    if (selectedTabIndex in 0 until tabCount) {
        TabRowDefaults.SecondaryIndicator(
            modifier = Modifier.tabIndicatorOffset(selectedTabIndex, matchContentSize = false),
            height = TabIndicatorHeight,
            color = OceanColors.brandPrimaryPure
        )
    }
}

@Composable
private fun OceanTabDivider() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(OCEAN_TAB_DIVIDER_TAG),
        thickness = TabDividerThickness,
        color = OceanColors.interfaceLightDown
    )
}

@Composable
private fun OceanTabItems(
    tabs: List<OceanTabItemModel>,
    selectedTabIndex: Int,
    onClickTab: (Int) -> Unit
) {
    tabs.forEachIndexed { index, tab ->
        val selected = index == selectedTabIndex
        val interactionSource = remember { MutableInteractionSource() }
        val pressed by interactionSource.collectIsPressedAsState()

        Tab(
            selected = selected,
            onClick = { onClickTab(index) },
            modifier = Modifier
                .background(color = OceanColors.interfaceLightPure)
                .height(TabItemHeight),
            selectedContentColor = OceanColors.brandPrimaryPure,
            unselectedContentColor = OceanColors.interfaceDarkUp,
            interactionSource = interactionSource
        ) {
            OceanTabItemContent(tab = tab, highlighted = selected || pressed)
        }
    }
}

/**
 * Conteúdo do item: texto em uma linha + contador opcional.
 *
 * [highlighted] cobre "selecionado" e "pressionado" (o *Hover* do Figma no app): texto em
 * [OceanColors.brandPrimaryPure] e contador no [OceanTabItemModel.badgeType]. Inativo: texto em
 * [OceanColors.interfaceDarkUp] e contador neutro ([OceanBadgeType.DISABLED], fundo
 * [OceanColors.interfaceDarkUp] — o *Badge Count Neutral* do Figma).
 */
@Composable
private fun OceanTabItemContent(
    tab: OceanTabItemModel,
    highlighted: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = OceanSpacing.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = tab.label,
            style = OceanTextStyle.heading4,
            color = if (highlighted) OceanColors.brandPrimaryPure else OceanColors.interfaceDarkUp,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Visible
        )

        if (tab.counter != null) {
            OceanSpacing.StackXXS()

            OceanBadge(
                text = tab.counter.toString(),
                type = if (highlighted) tab.badgeType else OceanBadgeType.DISABLED,
                size = OceanBadgeSize.Small
            )
        }
    }
}
