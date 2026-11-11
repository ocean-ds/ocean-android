package br.com.useblu.oceands.components.compose

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.compose.OceanTabItemModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

// Altura do item no Figma (_Tab Item). Não existe token de tamanho para ela: fica literal até o
// Ocean ganhar um token de altura de componente.
private val TabItemHeight = 56.dp

// Espessura da linha de seleção no Figma. O Ocean não tem token de border/stroke width
// (OceanSpacing, OceanBorderRadius e tokens.xml conferidos): literal.
private val TabIndicatorHeight = 2.dp

// Espessura da linha de baixo da barra no Figma. O Ocean não tem token de border/stroke width
// (OceanSpacing, OceanBorderRadius e tokens.xml conferidos): literal.
private val TabDividerThickness = 1.dp

// Movimento aprovado na web (MR-980): sem token de motion no Ocean Android, valores literais.
private val OceanTabEasing = CubicBezierEasing(0.65f, 0f, 0.35f, 1f)
private const val TAB_INDICATOR_DURATION_MILLIS = 300
private const val TAB_SCROLL_DURATION_MILLIS = 400
private const val TAB_COLOR_DURATION_MILLIS = 150

internal const val OCEAN_TAB_DIVIDER_TAG = "oceanTabDivider"
internal const val OCEAN_TAB_INDICATOR_TAG = "oceanTabIndicator"

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
            OceanTabRowImpl(
                tabs = tabs,
                selectedTabIndex = selectedTabIndexInternal,
                scrollable = !allLabelsFit,
                edgePadding = edgePadding,
                onClickTab = onClickTab
            )
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
    OceanTabRowImpl(
        modifier = modifier,
        tabs = tabs,
        selectedTabIndex = defaultSelectedTab,
        scrollable = true,
        edgePadding = OceanSpacing.md,
        onClickTab = onSelectedTab
    )
}

/** Posição de uma aba no conteúdo da barra (inclui a margem lateral), em px. */
@Immutable
private data class OceanTabPosition(val left: Int, val width: Int) {
    val right: Int get() = left + width
}

/**
 * Para onde rolar a barra para mostrar a aba [tabLeft]..[tabRight] (px, no conteúdo).
 *
 * Só rola quando a aba não está inteira na tela ou está a menos de [edgeMarginPx] de uma borda;
 * nesse caso leva a aba para o centro, limitado a 0..[maxScroll]. Devolve `null` quando não precisa
 * rolar (ou quando o centro limitado é a posição atual).
 */
internal fun oceanTabScrollTarget(
    tabLeft: Int,
    tabRight: Int,
    scrollValue: Int,
    viewportWidth: Int,
    maxScroll: Int,
    edgeMarginPx: Int
): Int? {
    if (viewportWidth <= 0) return null
    val visibleLeft = scrollValue
    val visibleRight = scrollValue + viewportWidth
    val needsScroll = tabLeft < visibleLeft + edgeMarginPx || tabRight > visibleRight - edgeMarginPx
    if (!needsScroll) return null

    val centered = (tabLeft + tabRight) / 2 - viewportWidth / 2
    val target = centered.coerceIn(0, maxOf(0, maxScroll))
    return target.takeIf { it != scrollValue }
}

/**
 * Barra de abas do Ocean, com indicador e rolagem próprios (no lugar do TabRow do Material) para
 * seguir o movimento aprovado na web: indicador desliza em 300ms e a rolagem até a aba leva 400ms,
 * ambos com [OceanTabEasing]. Na primeira composição nada anima.
 *
 * - [scrollable] `false`: as abas dividem a largura em partes iguais.
 * - [scrollable] `true`: cada aba do tamanho do conteúdo, [edgePadding] nas laterais e rolagem.
 *
 * A linha de baixo fica atrás dos itens e o indicador por cima dela.
 */
@Composable
private fun OceanTabRowImpl(
    modifier: Modifier = Modifier,
    tabs: List<OceanTabItemModel>,
    selectedTabIndex: Int,
    scrollable: Boolean,
    edgePadding: Dp,
    onClickTab: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    var positions by remember { mutableStateOf<List<OceanTabPosition>>(emptyList()) }
    // Margem mínima entre a aba selecionada e a borda da barra antes de rolar (movimento da web).
    val edgeMargin = OceanSpacing.sm
    val edgeMarginPx = with(LocalDensity.current) { edgeMargin.roundToPx() }

    if (scrollable) {
        // Rola até a aba selecionada. A primeira vez é um salto (sem animação).
        var hasSettled by remember { mutableStateOf(false) }
        LaunchedEffect(selectedTabIndex, positions) {
            val position = positions.getOrNull(selectedTabIndex) ?: return@LaunchedEffect
            val target = oceanTabScrollTarget(
                tabLeft = position.left,
                tabRight = position.right,
                scrollValue = scrollState.value,
                viewportWidth = scrollState.viewportSize,
                maxScroll = scrollState.maxValue,
                edgeMarginPx = edgeMarginPx
            )
            if (target != null) {
                if (hasSettled) {
                    scrollState.animateScrollTo(
                        target,
                        tween(durationMillis = TAB_SCROLL_DURATION_MILLIS, easing = OceanTabEasing)
                    )
                } else {
                    scrollState.scrollTo(target)
                }
            }
            hasSettled = true
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = OceanColors.interfaceLightPure)
    ) {
        OceanTabDivider(modifier = Modifier.align(Alignment.BottomStart))

        Layout(
            contents = listOf(
                { OceanTabItems(tabs, selectedTabIndex, onClickTab) },
                { OceanTabIndicator(positions.getOrNull(selectedTabIndex)) }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .then(if (scrollable) Modifier.horizontalScroll(scrollState) else Modifier)
                .selectableGroup()
        ) { (tabMeasurables, indicatorMeasurables), constraints ->
            val padding = if (scrollable) edgePadding.roundToPx() else 0
            val equalWidth = if (!scrollable && tabMeasurables.isNotEmpty()) {
                constraints.maxWidth / tabMeasurables.size
            } else {
                0
            }

            val tabPlaceables = tabMeasurables.map {
                it.measure(
                    if (scrollable) {
                        Constraints()
                    } else {
                        Constraints.fixedWidth(equalWidth)
                    }
                )
            }

            var left = padding
            val newPositions = tabPlaceables.map { placeable ->
                OceanTabPosition(left = left, width = placeable.width).also { left += placeable.width }
            }
            if (newPositions != positions) positions = newPositions

            val layoutWidth = if (scrollable) {
                maxOf(left + padding, constraints.minWidth)
            } else {
                constraints.maxWidth
            }
            val layoutHeight = tabPlaceables.maxOfOrNull { it.height } ?: 0

            val indicatorPlaceables = indicatorMeasurables.map {
                it.measure(Constraints.fixed(layoutWidth, layoutHeight))
            }

            layout(layoutWidth, layoutHeight) {
                tabPlaceables.forEachIndexed { index, placeable ->
                    placeable.placeRelative(newPositions[index].left, 0)
                }
                indicatorPlaceables.forEach { it.placeRelative(0, 0) }
            }
        }
    }
}

/**
 * Linha de seleção (2dp, [OceanColors.brandPrimaryPure]) da largura da aba. Desliza entre as abas
 * em [TAB_INDICATOR_DURATION_MILLIS] com [OceanTabEasing]; aparece sem animação na primeira posição.
 */
@Composable
private fun OceanTabIndicator(position: OceanTabPosition?) {
    if (position == null) return

    val density = LocalDensity.current
    val spec = tween<Dp>(durationMillis = TAB_INDICATOR_DURATION_MILLIS, easing = OceanTabEasing)
    val left by animateDpAsState(
        targetValue = with(density) { position.left.toDp() },
        animationSpec = spec,
        label = "oceanTabIndicatorLeft"
    )
    val width by animateDpAsState(
        targetValue = with(density) { position.width.toDp() },
        animationSpec = spec,
        label = "oceanTabIndicatorWidth"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset { IntOffset(left.roundToPx(), 0) }
                .width(width)
                .height(TabIndicatorHeight)
                .background(color = OceanColors.brandPrimaryPure)
                .testTag(OCEAN_TAB_INDICATOR_TAG)
        )
    }
}

@Composable
private fun OceanTabDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier
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
            // Sem fundo no item: o fundo branco vem da barra, e a linha de baixo fica atrás dos
            // itens.
            modifier = Modifier.height(TabItemHeight),
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
    val labelColor by animateColorAsState(
        targetValue = if (highlighted) OceanColors.brandPrimaryPure else OceanColors.interfaceDarkUp,
        animationSpec = tween(durationMillis = TAB_COLOR_DURATION_MILLIS),
        label = "oceanTabLabelColor"
    )

    Row(
        modifier = modifier.padding(horizontal = OceanSpacing.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = tab.label,
            style = OceanTextStyle.heading4,
            color = labelColor,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Visible
        )

        if (tab.counter != null) {
            OceanSpacing.StackXXS()

            // O OceanBadge recebe o tipo, não a cor: a troca de cor é um crossfade de 150ms.
            Crossfade(
                targetState = if (highlighted) tab.badgeType else OceanBadgeType.DISABLED,
                animationSpec = tween(durationMillis = TAB_COLOR_DURATION_MILLIS),
                label = "oceanTabBadgeType"
            ) { badgeType ->
                OceanBadge(
                    text = tab.counter.toString(),
                    type = badgeType,
                    size = OceanBadgeSize.Small
                )
            }
        }
    }
}
