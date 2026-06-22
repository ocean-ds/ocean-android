package br.com.useblu.oceands.snapshot

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.banner.OceanBanner
import br.com.useblu.oceands.components.compose.banner.OceanBannerKind
import br.com.useblu.oceands.components.compose.banner.OceanBannerStyle
import br.com.useblu.oceands.utils.image.OceanImageProxy
import java.io.File
import java.io.FileOutputStream
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Gera os snapshots PNG do componente REAL [OceanBanner] para a galeria web (Storybook
 * via WebView). Não é teste de regressão — é o gerador de imagens consumido pelo CI.
 *
 * Renderiza o componente de verdade via Robolectric + GraphicsMode.NATIVE, então quaisquer
 * divergências reais de render aparecem nas imagens (ao contrário de uma maquete HTML).
 *
 * Saída: `build/outputs/banner-snapshots/<key>.png` (um arquivo por combinação da matriz).
 * Chave compartilhada com o iOS: `banner__{size}__{type}__{image}__{buttons}`.
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w360dp-h920dp-xhdpi")
class OceanBannerSnapshotTest(private val case: BannerCase) {

    @get:Rule
    val compose = createComposeRule()

    @Test
    fun snapshot() {
        compose.setContent {
            OceanTheme {
                Box(
                    modifier = Modifier
                        .width(343.dp)
                        .wrapContentHeight()
                        .testTag(TAG)
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

        val bitmap = compose.onNodeWithTag(TAG).captureToImage().asAndroidBitmap()
        val dir = File(
            System.getProperty("banner.snapshot.outDir")
                ?: "build/outputs/banner-snapshots"
        )
        dir.mkdirs()
        FileOutputStream(File(dir, "${case.key}.png")).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
    }

    companion object {
        private const val TAG = "banner"
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
