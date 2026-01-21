package br.com.useblu.oceands.components.compose.banner

import br.com.useblu.oceands.utils.image.OceanImageProxy

sealed interface OceanBannerKind {
    val image: OceanImageProxy?

    data class Large(override val image: OceanImageProxy) : OceanBannerKind
    data class Small(override val image: OceanImageProxy? = null) : OceanBannerKind
}
