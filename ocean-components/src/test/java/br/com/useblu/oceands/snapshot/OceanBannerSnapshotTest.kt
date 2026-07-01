package br.com.useblu.oceands.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.banner.OceanBanner
import br.com.useblu.oceands.components.compose.banner.OceanBannerKind
import br.com.useblu.oceands.components.compose.banner.OceanBannerStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.image.OceanImageProxy
import com.github.takahirom.roborazzi.captureRoboImage
import java.io.File
import org.junit.Assume
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Gera os snapshots PNG do componente REAL [OceanBanner] para a galeria web (Storybook
 * via WebView). Não é teste de regressão — é o gerador de imagens consumido pelo CI.
 *
 * Usa Roborazzi (Robolectric + GraphicsMode.NATIVE) para renderizar o componente de verdade,
 * então quaisquer divergências reais de render aparecem nas imagens.
 *
 * Inerte no CI padrão (Assume) — só roda com BANNER_SNAPSHOT_GENERATE=true.
 * Saída: `build/outputs/banner-snapshots/<key>.png`.
 * Chave compartilhada com o iOS: `banner__{size}__{type}__{image}__{buttons}`.
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w360dp-h920dp-xhdpi")
class OceanBannerSnapshotTest(private val case: BannerCase) {

    @Test
    fun snapshot() {
        Assume.assumeTrue(
            "Gerador de snapshots desabilitado; defina BANNER_SNAPSHOT_GENERATE=true para gerar",
            System.getenv("BANNER_SNAPSHOT_GENERATE") == "true"
        )

        val dir = File(
            System.getProperty("banner.snapshot.outDir")
                ?: "build/outputs/banner-snapshots"
        )
        dir.mkdirs()

        captureRoboImage(File(dir, "${case.key}.png").path) {
            OceanTheme {
                Box(
                    modifier = Modifier
                        .width(360.dp)
                        .background(OceanColors.interfaceLightUp)
                        .padding(OceanSpacing.xs)
                ) {
                    OceanBanner(
                        modifier = Modifier.fillMaxWidth(),
                        style = case.style(),
                        kind = case.kind(),
                        title = TITLE,
                        description = DESCRIPTION,
                        ctaTitle = case.primaryTitle(),
                        onCtaClick = {},
                        secondaryCtaTitle = case.secondaryTitle(),
                        onSecondaryCtaClick = {}
                    )
                }
            }
        }
    }

    companion object {
        private const val TITLE = "Banner Title"
        private const val DESCRIPTION = "This is a description for the banner component."

        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun cases(): List<Array<Any>> = buildBannerMatrix().map { arrayOf<Any>(it) }
    }
}

/** Uma combinação da matriz de variantes do Banner. */
data class BannerCase(
    val size: String,
    val type: String,
    val image: String,
    val buttons: String
) {
    val key: String get() = "banner__${size}__${type}__${image}__$buttons"

    override fun toString(): String = key

    fun style(): OceanBannerStyle = when (type) {
        "warning" -> OceanBannerStyle.Warning
        "negative" -> OceanBannerStyle.Negative
        "emphasys" -> OceanBannerStyle.Emphasys
        else -> OceanBannerStyle.Neutral
    }

    private fun image(): OceanImageProxy? = if (image == "image") {
        OceanImageProxy.Resource(resId = R.drawable.image_placeholder)
    } else {
        null
    }

    fun kind(): OceanBannerKind = if (size == "large") {
        OceanBannerKind.Large(image = image())
    } else {
        OceanBannerKind.Small(image = image())
    }

    fun primaryTitle(): String = if (buttons == "none") "" else "Ação principal"

    fun secondaryTitle(): String = if (buttons == "two") "Secundária" else ""
}

/** Matriz compartilhada com o iOS — mantenha as dimensões e a ordem em sincronia. */
fun buildBannerMatrix(): List<BannerCase> {
    val sizes = listOf("large", "small")
    val types = listOf("default", "warning", "negative", "emphasys")
    val images = listOf("noimage", "image")
    val buttons = listOf("none", "one", "two")

    val matrix = mutableListOf<BannerCase>()
    for (size in sizes) {
        for (type in types) {
            for (image in images) {
                for (button in buttons) {
                    matrix.add(BannerCase(size, type, image, button))
                }
            }
        }
    }
    return matrix
}
