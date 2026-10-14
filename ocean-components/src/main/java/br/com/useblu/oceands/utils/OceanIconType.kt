package br.com.useblu.oceands.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import java.io.Serializable

@Stable
interface OceanIconType : Serializable {
    val token: String

    @get:DrawableRes val icon: Int
    val name: String
}

object OceanIconRegistry {
    /** Ícones que não são do Ocean (ex.: Product Icons do app). Consultado só quando o token não é do Ocean. Recebe o token normalizado (sem espaços, sem "-", minúsculo). */
    @Volatile
    var provider: ((String) -> OceanIconType?)? = null
}
