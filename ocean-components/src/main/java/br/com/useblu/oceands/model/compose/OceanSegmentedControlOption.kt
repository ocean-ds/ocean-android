package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.utils.OceanIcons

/**
 * Uma opção de [br.com.useblu.oceands.components.compose.OceanSegmentedControl].
 *
 * @param label texto da opção. Fica sempre em uma linha: se não cabe, é cortado com "…".
 * @param icon ícone de 16dp antes do texto. `null` (padrão) não mostra ícone.
 * @param counter número do contador (Badge Count Small, tipo primário) depois do texto. `null`
 * (padrão) não mostra contador.
 */
@Immutable
data class OceanSegmentedControlOption(
    val label: String,
    val icon: OceanIcons? = null,
    val counter: Int? = null
)
