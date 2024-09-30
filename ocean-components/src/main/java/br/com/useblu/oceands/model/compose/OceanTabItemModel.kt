package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.model.OceanBadgeType

@Immutable
data class OceanTabItemModel(
    val label: String,
    val counter: Int? = null,
    val badgeType: OceanBadgeType = OceanBadgeType.PRIMARY
)