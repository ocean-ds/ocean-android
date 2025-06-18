package br.com.useblu.oceands.model

import br.com.useblu.oceands.utils.OceanIcons

sealed class OceanTextListStyle {
    data object Default : OceanTextListStyle()
    data object RadioButton : OceanTextListStyle()
    data object Checkbox : OceanTextListStyle()
    data class Icon(val icon: OceanIcons? = null) : OceanTextListStyle()
}
