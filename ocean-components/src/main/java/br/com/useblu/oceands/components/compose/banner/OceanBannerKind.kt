package br.com.useblu.oceands.components.compose.banner

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.utils.image.OceanImageProxy

sealed interface OceanBannerKind {
    val image: OceanImageProxy?

    data class Large(override val image: OceanImageProxy? = null) : OceanBannerKind
    data class Small(
        override val image: OceanImageProxy? = null,
        val imageSize: OceanBannerImageSize = OceanBannerImageSize.Proportional
    ) : OceanBannerKind
}

/**
 * Largura da imagem lateral do [OceanBannerKind.Small].
 *
 * [Proportional] é o padrão e mantém o comportamento original (até 25% da largura da tela);
 * [Fixed] aplica a largura da spec do Banner (82dp) e precisa ser pedido explicitamente.
 */
sealed interface OceanBannerImageSize {
    data object Proportional : OceanBannerImageSize

    data class Fixed(val width: Dp = 82.dp) : OceanBannerImageSize

    @Composable
    fun widthModifier(): Modifier =
        when (this) {
            Proportional -> Modifier.widthIn(
                max = with(LocalConfiguration.current) { screenWidthDp.dp * SCREEN_WIDTH_FRACTION }
            )
            is Fixed -> Modifier.width(width)
        }

    companion object {
        const val SCREEN_WIDTH_FRACTION = 0.25f
    }
}
