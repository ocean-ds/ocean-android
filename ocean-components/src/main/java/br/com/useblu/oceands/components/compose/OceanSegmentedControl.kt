package br.com.useblu.oceands.components.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.compose.OceanSegmentedControlOption
import br.com.useblu.oceands.ui.compose.OceanBorderWidth
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

// O Segmented Control desenha exatamente 2 opções (Figma: Segmented Control, MR-990).
internal const val OCEAN_SEGMENTED_CONTROL_OPTION_COUNT = 2

// Altura da opção no Figma (32; o controle fica com 40 somando o padding de 4 em volta). Não existe
// token de tamanho de componente: literal até o Ocean ganhar um token de altura.
private val SegmentedControlOptionHeight = 32.dp

// Tamanho do ícone no Figma. O Ocean não tem token de tamanho de ícone (os outros componentes
// também usam 16dp literal): literal.
private val SegmentedControlIconSize = 16.dp

// Opacidade do fundo de seleção no Figma: 32% no padrão e 48% pressionado. O OceanOpacity não tem
// esses níveis (0.08, 0.16, 0.4, 0.64, 0.8): literal até existir o token.
private const val SELECTION_ALPHA_DEFAULT = 0.32f
private const val SELECTION_ALPHA_PRESSED = 0.48f

// Movimento aprovado (MR-990): a mesma curva e as mesmas durações da linha de seleção do OceanTab.
// Sem token de motion no Ocean Android, valores literais.
private val OceanSegmentedControlEasing = CubicBezierEasing(0.65f, 0f, 0.35f, 1f)
private const val SELECTION_SLIDE_DURATION_MILLIS = 300
private const val COLOR_DURATION_MILLIS = 150

internal const val OCEAN_SEGMENTED_CONTROL_SELECTION_TAG = "oceanSegmentedControlSelection"

@Preview
@Composable
private fun OceanSegmentedControlPreview() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .background(OceanColors.interfaceLightPure)
            .padding(OceanSpacing.xs),
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
    ) {
        OceanSegmentedControl(
            options = listOf(
                OceanSegmentedControlOption("Loja"),
                OceanSegmentedControlOption("Rede")
            ),
            selectedIndex = selectedIndex,
            onSelectedOption = { selectedIndex = it }
        )

        OceanSegmentedControl(
            options = listOf(
                OceanSegmentedControlOption("Lista", icon = OceanIcons.VIEW_LIST_OUTLINE, counter = 3),
                OceanSegmentedControlOption("Grade", icon = OceanIcons.VIEW_GRID_OUTLINE, counter = 12)
            ),
            selectedIndex = 1,
            onSelectedOption = {}
        )
    }
}

@Preview(widthDp = 240)
@Composable
private fun OceanSegmentedControlLongLabelPreview() {
    OceanSegmentedControl(
        modifier = Modifier.padding(OceanSpacing.xs),
        options = listOf(
            OceanSegmentedControlOption("Todas as lojas da rede"),
            OceanSegmentedControlOption("Rede")
        ),
        selectedIndex = 0,
        onSelectedOption = {}
    )
}

/**
 * Segmented Control do Ocean (Figma: *Segmented Control*).
 *
 * Troca o escopo ou a visão do mesmo conteúdo entre **duas** opções (ex.: Loja | Rede). Uma opção
 * está sempre selecionada. Não é aba ([OceanTab]), filtro nem liga/desliga ([OceanSwitch]). Não
 * existe estado desabilitado nem carregando: se a troca não está disponível, não mostre o controle.
 *
 * Quantidade de opções: o controle desenha **exatamente 2**. Com mais de 2, só as 2 primeiras
 * aparecem; com 1, só ela aparece (selecionada); com a lista vazia, nada é desenhado.
 *
 * Largura: o controle fica do tamanho do conteúdo (não estica). Cada opção tem a largura do seu
 * conteúdo. Se as duas não cabem na largura disponível, a opção curta fica inteira e a longa ocupa o
 * resto (ou as duas dividem ao meio, se as duas forem longas). O texto fica sempre em **uma linha** e
 * é cortado com "…" quando não cabe; ícone e contador continuam visíveis.
 *
 * Visual (tokens do Figma):
 * - controle: fundo [OceanColors.interfaceLightPure], borda de 1dp ([OceanBorderWidth.Hairline]) em
 *   [OceanColors.interfaceLightDown], forma circular, 4dp ([OceanSpacing.xxxs]) de padding em volta
 *   e entre as opções, 40dp de altura;
 * - opção: 32dp de altura, 12dp ([OceanSpacing.xxsExtra]) nas laterais, 4dp ([OceanSpacing.xxxs])
 *   em cima/embaixo e entre ícone, texto e contador;
 * - ícone opcional de 16dp, texto [OceanTextStyle.heading5] e contador (Badge Count Small,
 *   [OceanBadgeType.PRIMARY]) — ícone e texto em [OceanColors.brandPrimaryPure] nas duas opções;
 * - fundo de seleção: [OceanColors.brandPrimaryUp] com 32% de opacidade, do tamanho da opção
 *   selecionada.
 *
 * Pressionado (o *Hover* do Figma no app): enquanto o dedo está sobre o controle, o fundo passa a
 * [OceanColors.interfaceLightUp] e a seleção a 48%. Sem ripple.
 *
 * Movimento: ao trocar de opção, o fundo de seleção desliza (posição e largura) em 300ms com
 * `cubic-bezier(0.65, 0, 0.35, 1)`, a mesma curva do [OceanTab]; as cores mudam em 150ms. Na
 * primeira composição nada anima. Com "Remover animações" ligado no sistema (escala de animação 0),
 * o Compose termina as animações na hora: a troca é imediata.
 *
 * Acessibilidade: o controle é um grupo selecionável e cada opção é anunciada como botão
 * ([Role.Button]) com o estado "selecionado" na opção atual.
 *
 * @param modifier [Modifier] aplicado ao controle.
 * @param options opções, na ordem de exibição. Só as 2 primeiras são desenhadas. Ver
 * [OceanSegmentedControlOption].
 * @param selectedIndex índice da opção selecionada ao compor (0 ou 1; fora do intervalo é limitado
 * às opções desenhadas). O controle guarda a seleção feita pelo toque até o valor mudar.
 * @param onSelectedOption chamado com o índice da opção tocada quando ela não é a selecionada.
 */
@Composable
fun OceanSegmentedControl(
    modifier: Modifier = Modifier,
    options: List<OceanSegmentedControlOption>,
    selectedIndex: Int,
    onSelectedOption: (Int) -> Unit
) {
    val visibleOptions = options.take(OCEAN_SEGMENTED_CONTROL_OPTION_COUNT)
    if (visibleOptions.isEmpty()) return

    var selectedIndexInternal by remember(selectedIndex) { mutableIntStateOf(selectedIndex) }
    val selected = selectedIndexInternal.coerceIn(0, visibleOptions.lastIndex)

    val onClickOption: (Int) -> Unit = { index ->
        if (index != selected) {
            selectedIndexInternal = index
            onSelectedOption(index)
        }
    }

    // Um só InteractionSource para as duas opções: o estado pressionado é do controle inteiro.
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val containerColor by animateColorAsState(
        targetValue = if (pressed) OceanColors.interfaceLightUp else OceanColors.interfaceLightPure,
        animationSpec = tween(durationMillis = COLOR_DURATION_MILLIS),
        label = "oceanSegmentedControlContainerColor"
    )
    val selectionAlpha by animateFloatAsState(
        targetValue = if (pressed) SELECTION_ALPHA_PRESSED else SELECTION_ALPHA_DEFAULT,
        animationSpec = tween(durationMillis = COLOR_DURATION_MILLIS),
        label = "oceanSegmentedControlSelectionAlpha"
    )

    val gapPx = with(LocalDensity.current) { OceanSpacing.xxxs.roundToPx() }
    var positions by remember { mutableStateOf<List<OceanSegmentedControlPosition>>(emptyList()) }

    Layout(
        contents = listOf(
            {
                OceanSegmentedControlSelection(
                    position = positions.getOrNull(selected),
                    alpha = selectionAlpha
                )
            },
            {
                visibleOptions.forEachIndexed { index, option ->
                    OceanSegmentedControlItem(
                        option = option,
                        selected = index == selected,
                        interactionSource = interactionSource,
                        onClick = { onClickOption(index) }
                    )
                }
            }
        ),
        modifier = modifier
            .background(color = containerColor, shape = CircleShape)
            .border(
                width = OceanBorderWidth.Hairline.value,
                color = OceanColors.interfaceLightDown,
                shape = CircleShape
            )
            .selectableGroup()
            .padding(OceanSpacing.xxxs)
    ) { (selectionMeasurables, optionMeasurables), constraints ->
        val totalGap = gapPx * (optionMeasurables.size - 1).coerceAtLeast(0)
        val widths = oceanSegmentedControlOptionWidths(
            contentWidths = optionMeasurables.map { it.maxIntrinsicWidth(Constraints.Infinity) },
            availableWidth = if (constraints.hasBoundedWidth) {
                constraints.maxWidth - totalGap
            } else {
                null
            }
        )

        val optionPlaceables = optionMeasurables.mapIndexed { index, measurable ->
            measurable.measure(Constraints.fixedWidth(widths[index]))
        }

        var left = 0
        val newPositions = optionPlaceables.map { placeable ->
            OceanSegmentedControlPosition(left = left, width = placeable.width)
                .also { left += placeable.width + gapPx }
        }
        if (newPositions != positions) positions = newPositions

        val contentWidth = optionPlaceables.sumOf { it.width } + totalGap
        val layoutWidth = contentWidth.coerceIn(constraints.minWidth, constraints.maxWidth)
        val layoutHeight = (optionPlaceables.maxOfOrNull { it.height } ?: 0)
            .coerceIn(constraints.minHeight, constraints.maxHeight)

        val selectionPlaceables = selectionMeasurables.map {
            it.measure(Constraints.fixed(layoutWidth, layoutHeight))
        }

        layout(layoutWidth, layoutHeight) {
            // O fundo de seleção fica atrás das opções.
            selectionPlaceables.forEach { it.placeRelative(0, 0) }
            optionPlaceables.forEachIndexed { index, placeable ->
                placeable.placeRelative(newPositions[index].left, 0)
            }
        }
    }
}

/** Posição de uma opção no conteúdo do controle (sem o padding em volta), em px. */
@Immutable
private data class OceanSegmentedControlPosition(val left: Int, val width: Int)

/**
 * Largura de cada opção, em px.
 *
 * Cada opção fica do tamanho do conteúdo ([contentWidths]). Quando a soma passa de
 * [availableWidth] (largura sem padding e sem os espaços entre as opções), a opção que cabe na
 * metade fica inteira e a outra ocupa o resto; se nenhuma cabe na metade, as duas dividem ao meio.
 * `null` em [availableWidth] significa sem limite.
 */
internal fun oceanSegmentedControlOptionWidths(
    contentWidths: List<Int>,
    availableWidth: Int?
): List<Int> {
    if (availableWidth == null || contentWidths.sum() <= availableWidth) return contentWidths

    val available = maxOf(0, availableWidth)
    if (contentWidths.size != OCEAN_SEGMENTED_CONTROL_OPTION_COUNT) {
        return contentWidths.map { minOf(it, available) }
    }

    val half = available / 2
    val (first, second) = contentWidths
    return when {
        first <= half -> listOf(first, available - first)
        second <= half -> listOf(available - second, second)
        else -> listOf(half, available - half)
    }
}

/**
 * Fundo de seleção ([OceanColors.brandPrimaryUp] com [alpha]) do tamanho da opção selecionada.
 * Desliza entre as opções em [SELECTION_SLIDE_DURATION_MILLIS] com [OceanSegmentedControlEasing];
 * aparece sem animação na primeira posição.
 */
@Composable
private fun OceanSegmentedControlSelection(
    position: OceanSegmentedControlPosition?,
    alpha: Float
) {
    if (position == null) return

    val density = LocalDensity.current
    val spec = tween<Dp>(
        durationMillis = SELECTION_SLIDE_DURATION_MILLIS,
        easing = OceanSegmentedControlEasing
    )
    val left by animateDpAsState(
        targetValue = with(density) { position.left.toDp() },
        animationSpec = spec,
        label = "oceanSegmentedControlSelectionLeft"
    )
    val width by animateDpAsState(
        targetValue = with(density) { position.width.toDp() },
        animationSpec = spec,
        label = "oceanSegmentedControlSelectionWidth"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset { IntOffset(left.roundToPx(), 0) }
                .width(width)
                .height(SegmentedControlOptionHeight)
                .background(
                    color = OceanColors.brandPrimaryUp.copy(alpha = alpha),
                    shape = CircleShape
                )
                .testTag(OCEAN_SEGMENTED_CONTROL_SELECTION_TAG)
        )
    }
}

/** Opção: ícone opcional + texto em uma linha (com "…") + contador opcional. */
@Composable
private fun OceanSegmentedControlItem(
    option: OceanSegmentedControlOption,
    selected: Boolean,
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(SegmentedControlOptionHeight)
            .selectable(
                selected = selected,
                interactionSource = interactionSource,
                // Sem ripple: o retorno do toque é o estado pressionado do controle inteiro.
                indication = null,
                role = Role.Button,
                onClick = onClick
            )
            .padding(horizontal = OceanSpacing.xxsExtra, vertical = OceanSpacing.xxxs),
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (option.icon != null) {
            OceanIcon(
                iconType = option.icon,
                tint = OceanColors.brandPrimaryPure,
                modifier = Modifier.size(SegmentedControlIconSize)
            )
        }

        Text(
            text = option.label,
            // Sem fill: a opção continua do tamanho do texto, mas o texto encolhe (com "…") antes do
            // contador quando a opção é mais estreita que o conteúdo.
            modifier = Modifier.weight(1f, fill = false),
            style = OceanTextStyle.heading5,
            color = OceanColors.brandPrimaryPure,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        if (option.counter != null) {
            OceanBadge(
                text = option.counter.toString(),
                type = OceanBadgeType.PRIMARY,
                size = OceanBadgeSize.Small
            )
        }
    }
}
