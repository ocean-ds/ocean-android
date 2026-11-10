package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable

/**
 * Segmented Control opcional do [br.com.useblu.oceands.components.compose.OceanFilterBar], fixo à
 * direita da barra (Figma: *Filter Bar*, propriedade *Segmented control*).
 *
 * Os campos são os do [br.com.useblu.oceands.components.compose.OceanSegmentedControl], com os
 * mesmos nomes e o mesmo comportamento. O fundo é sempre o padrão
 * ([br.com.useblu.oceands.components.compose.OceanSegmentedControlBackground.Default]), porque a
 * barra é branca.
 *
 * No Filter Bar o Segmented aparece **só com texto**: [OceanSegmentedControlOption.icon] e
 * [OceanSegmentedControlOption.counter] são ignorados mesmo quando preenchidos.
 *
 * @param options opções, na ordem de exibição (o controle desenha as 2 primeiras). Só o
 * [OceanSegmentedControlOption.label] é usado.
 * @param selectedIndex índice da opção selecionada ao compor (0 ou 1).
 * @param enabled `false` desabilita o controle inteiro. Padrão `true`.
 * @param onSelectedOption chamado com o índice da opção tocada quando ela não é a selecionada (e o
 * controle está habilitado).
 */
@Immutable
data class OceanFilterBarSegmentedControl(
    val options: List<OceanSegmentedControlOption>,
    val selectedIndex: Int,
    val enabled: Boolean = true,
    val onSelectedOption: (Int) -> Unit
)
