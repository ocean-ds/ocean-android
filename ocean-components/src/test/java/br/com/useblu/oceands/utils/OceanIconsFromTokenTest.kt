package br.com.useblu.oceands.utils

import br.com.useblu.oceands.R
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.reflect.Modifier
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

private val FAKE = OceanIcons(
    token = "fakeiconoutline",
    icon = R.drawable.ocean_icon_info_outline,
    name = "FAKE_ICON_OUTLINE"
)

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
        OceanIconRegistry.provider = { FAKE }
        assertEquals(OceanIcons.INFO_OUTLINE, OceanIcons.fromToken("info-outline"))
    }

    @Test
    fun `token desconhecido cai no provider`() {
        OceanIconRegistry.provider = { token ->
            if (token == "fakeiconoutline") FAKE else null
        }
        assertEquals(FAKE, OceanIcons.fromToken("fake-icon-outline"))
    }

    @Test
    fun `provider recebe o token normalizado`() {
        var received: String? = null
        OceanIconRegistry.provider = { token ->
            received = token
            FAKE
        }
        OceanIcons.fromToken("  Fake-Icon-Outline ")
        assertEquals("fakeiconoutline", received)
    }

    @Test
    fun `provider retornando null cai em UNDEFINED`() {
        OceanIconRegistry.provider = { null }
        assertEquals(OceanIcons.UNDEFINED, OceanIcons.fromToken("notAnOceanIcon"))
    }

    @Test
    fun `entries cobre todas as constantes estaticas`() {
        val staticConstants = OceanIcons::class.java.declaredFields.count { field ->
            Modifier.isStatic(field.modifiers) && field.type == OceanIcons::class.java
        }
        assertEquals(staticConstants, OceanIcons.entries.size)
    }

    @Test
    fun `nomes das constantes sao unicos`() {
        assertEquals(OceanIcons.entries.size, OceanIcons.entries.map { it.name }.toSet().size)
    }

    @Test
    fun `fromToken resolve para a primeira entrada de cada token`() {
        OceanIcons.entries.map { it.token }.toSet().forEach { token ->
            assertEquals(OceanIcons.entries.first { it.token == token }, OceanIcons.fromToken(token))
        }
    }

    @Test
    fun `serializacao java preserva igualdade`() {
        val bytes = ByteArrayOutputStream().use { bos ->
            ObjectOutputStream(bos).use { it.writeObject(OceanIcons.UNDEFINED) }
            bos.toByteArray()
        }
        val restored = ObjectInputStream(ByteArrayInputStream(bytes)).use { it.readObject() }
        assertEquals(OceanIcons.UNDEFINED, restored)
    }

    @Test
    fun `name da constante e o nome do campo`() {
        assertEquals("INFO_OUTLINE", OceanIcons.INFO_OUTLINE.name)
    }
}
