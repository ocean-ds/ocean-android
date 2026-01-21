package br.com.useblu.oceands.utils.image

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.utils.OceanIcons
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

sealed interface OceanImageProxy {
    data class Icon(
        val icon: OceanIcons,
        val tint: Color = Color.Unspecified,
        val fallbackIcon: OceanIcons? = null
    ) : OceanImageProxy
    data class URL(
        val url: String,
        val imageOptions: ImageOptions = ImageOptions()

    ) : OceanImageProxy
    data class Resource(
        val resId: Int,
        val contentScale: ContentScale = ContentScale.Fit,
        val contentDescription: String? = null,
        val alignment: Alignment = Alignment.Center
    ) : OceanImageProxy

    @Composable
    fun View(
        modifier: Modifier = Modifier
    ) {
        when (this) {
            is Icon -> OceanIcon(
                modifier = modifier,
                iconType = icon,
                tint = tint,
                fallbackIcon = fallbackIcon
            )
            is URL -> {
                GlideImage(
                    modifier = modifier,
                    imageModel = { url },
                    imageOptions = imageOptions
                )
            }
            is Resource -> Image(
                modifier = modifier,
                painter = painterResource(id = resId),
                contentDescription = contentDescription,
                contentScale = contentScale,
                alignment = alignment
            )
        }
    }
}
