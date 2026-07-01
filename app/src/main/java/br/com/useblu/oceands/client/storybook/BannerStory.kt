package br.com.useblu.oceands.client.storybook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.R as OceanR
import br.com.useblu.oceands.components.compose.banner.OceanBanner
import br.com.useblu.oceands.components.compose.banner.OceanBannerKind
import br.com.useblu.oceands.components.compose.banner.OceanBannerStyle
import br.com.useblu.oceands.utils.image.OceanImageProxy

private val bannerSizes = listOf("Large", "Small")
private val bannerStyles = listOf("Neutral", "Brand", "Warning", "Negative", "Emphasys")
private val bannerImages = listOf("None", "Local", "URL")

private const val SAMPLE_IMAGE_URL =
    "https://portal-cob.blu.com.br/assets/credblu/" +
        "image_hero_bill_overdue_negative-" +
        "b5bb0660469f00e54ae453279e54f24800fb953f9d4664d704f89f3ebadd9203.webp"

/**
 * Story do componente [OceanBanner] — registrada em [StorybookCatalog].
 */
val bannerStory = StorybookStory(
    name = "Banner",
    group = "Feedback",
    content = { BannerStoryContent() }
)

@Composable
private fun BannerStoryContent() {
    var sizeIndex by remember { mutableIntStateOf(0) }
    var styleIndex by remember { mutableIntStateOf(0) }
    var imageIndex by remember { mutableIntStateOf(1) }
    var title by remember { mutableStateOf("Banner Title") }
    var description by remember {
        mutableStateOf("This is a description for the banner component.")
    }
    var primaryCta by remember { mutableStateOf(true) }
    var primaryEnabled by remember { mutableStateOf(true) }
    var secondaryCta by remember { mutableStateOf(false) }

    val image: OceanImageProxy? = when (imageIndex) {
        1 -> OceanImageProxy.Resource(resId = OceanR.drawable.image_placeholder)
        2 -> OceanImageProxy.URL(url = SAMPLE_IMAGE_URL)
        else -> null
    }

    val kind: OceanBannerKind = if (sizeIndex == 0) {
        OceanBannerKind.Large(image = image)
    } else {
        OceanBannerKind.Small(image = image)
    }

    val style: OceanBannerStyle = when (styleIndex) {
        1 -> OceanBannerStyle.Brand
        2 -> OceanBannerStyle.Warning
        3 -> OceanBannerStyle.Negative
        4 -> OceanBannerStyle.Emphasys
        else -> OceanBannerStyle.Neutral
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        StorybookCanvas {
            OceanBanner(
                modifier = Modifier.fillMaxWidth(),
                style = style,
                kind = kind,
                title = title,
                description = description,
                ctaTitle = if (primaryCta) "Ação principal" else "",
                onCtaClick = {},
                ctaIsEnabled = primaryEnabled,
                secondaryCtaTitle = if (secondaryCta) "Secundária" else "",
                onSecondaryCtaClick = {}
            )
        }

        StorybookControlsPanel {
            StorybookSegmented("Size", bannerSizes, sizeIndex) { sizeIndex = it }
            StorybookSegmented("Style", bannerStyles, styleIndex) { styleIndex = it }
            StorybookSegmented("Image", bannerImages, imageIndex) { imageIndex = it }
            StorybookTextField("Title", title) { title = it }
            StorybookTextField("Description", description) { description = it }
            StorybookSwitch("Primary CTA", primaryCta) { primaryCta = it }
            StorybookSwitch("Primary CTA enabled", primaryEnabled) { primaryEnabled = it }
            StorybookSwitch("Secondary CTA", secondaryCta) { secondaryCta = it }
        }
    }
}
