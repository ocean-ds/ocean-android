package br.com.useblu.oceands.utils

import androidx.annotation.DrawableRes
import br.com.useblu.oceands.R
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

private enum class FakeIcon(
    override val token: String,
    @DrawableRes override val icon: Int
) : OceanIconType {
    FAKE_ICON_OUTLINE(
        token = "fakeiconoutline",
        icon = R.drawable.ocean_icon_info_outline
    )
}

class OceanIconsFromTokenTest {

    @After
    fun tearDown() {
        OceanIconRegistry.provider = null
    }

    @Test
    fun `sem provider, token desconhecido retorna UNDEFINED`() {
        assertEquals(OceanIcons.UNDEFINED, OceanIcons.fromToken("notAnOceanIcon"))
    }

    @Test
    fun `sem provider, token do Ocean retorna a entrada do enum`() {
        assertEquals(OceanIcons.INFO_OUTLINE, OceanIcons.fromToken("info-outline"))
    }

    @Test
    fun `Ocean sempre vence mesmo com provider registrado`() {
        OceanIconRegistry.provider = { FakeIcon.FAKE_ICON_OUTLINE }
        assertEquals(OceanIcons.INFO_OUTLINE, OceanIcons.fromToken("info-outline"))
    }

    @Test
    fun `token desconhecido cai no provider`() {
        OceanIconRegistry.provider = { token ->
            if (token == "fakeiconoutline") FakeIcon.FAKE_ICON_OUTLINE else null
        }
        assertEquals(FakeIcon.FAKE_ICON_OUTLINE, OceanIcons.fromToken("fake-icon-outline"))
    }

    @Test
    fun `provider recebe o token normalizado`() {
        var received: String? = null
        OceanIconRegistry.provider = { token ->
            received = token
            FakeIcon.FAKE_ICON_OUTLINE
        }
        OceanIcons.fromToken("  Fake-Icon-Outline ")
        assertEquals("fakeiconoutline", received)
    }

    @Test
    fun `provider retornando null cai em UNDEFINED`() {
        OceanIconRegistry.provider = { null }
        assertEquals(OceanIcons.UNDEFINED, OceanIcons.fromToken("notAnOceanIcon"))
    }
}
