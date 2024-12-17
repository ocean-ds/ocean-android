package br.com.useblu.oceands.model.compose

import br.com.useblu.oceands.model.OceanTagType

data class OceanTagModel(
    val type: OceanTagType,
    val text: String
)