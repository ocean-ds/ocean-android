package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.model.OceanBadgeType

/**
 * Uma aba de [br.com.useblu.oceands.components.compose.OceanTab],
 * [br.com.useblu.oceands.components.compose.OceanTabScrollable] e
 * [br.com.useblu.oceands.components.compose.OceanScrollableTab].
 *
 * @param label texto da aba. Fica sempre em uma linha: se não cabe, a barra rola.
 * @param counter número do contador (Badge Count Small). `null` não mostra contador.
 * @param badgeType cor do contador quando a aba está selecionada ou pressionada. O padrão
 * [OceanBadgeType.PRIMARY] segue o Figma; [OceanBadgeType.WARNING] e os demais continuam
 * disponíveis para telas que já usam. Na aba inativa o contador é sempre neutro.
 */
@Immutable
data class OceanTabItemModel(
    val label: String,
    val counter: Int? = null,
    val badgeType: OceanBadgeType = OceanBadgeType.PRIMARY
)
