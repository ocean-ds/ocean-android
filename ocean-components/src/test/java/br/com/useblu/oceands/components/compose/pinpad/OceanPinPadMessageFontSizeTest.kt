package br.com.useblu.oceands.components.compose.pinpad

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontSize
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w360dp-h640dp")
class OceanPinPadMessageFontSizeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun messageUsesXxxsByDefault() {
        setContent(hint = HINT)

        val layout = layoutOf(HINT)
        assertEquals(12.sp, layout.layoutInput.style.fontSize)
        assertEquals(18.sp, layout.layoutInput.style.lineHeight)
    }

    @Test
    fun messageUsesTheGivenFontSize() {
        setContent(hint = HINT, useFontSizeXs = true)

        val layout = layoutOf(HINT)
        assertEquals(16.sp, layout.layoutInput.style.fontSize)
        assertEquals(24.sp, layout.layoutInput.style.lineHeight)
    }

    @Test
    fun hintUsesInterfaceLightDeepByDefault() {
        var expected = Color.Unspecified
        setContent(hint = HINT, onComposed = { expected = OceanColors.interfaceLightDeep })

        assertEquals(expected, layoutOf(HINT).layoutInput.style.color)
    }

    @Test
    fun hintUsesTheGivenColor() {
        setContent(hint = HINT, hintColor = Color.Magenta)

        assertEquals(Color.Magenta, layoutOf(HINT).layoutInput.style.color)
    }

    @Test
    fun longMessageWrapsWithoutTruncating() {
        setContent(hint = LONG_HINT, useFontSizeXs = true)

        val layout = layoutOf(LONG_HINT)
        assertTrue(layout.lineCount > 1)
        assertTrue(layout.hasVisualOverflow.not())
    }

    private fun setContent(
        hint: String,
        useFontSizeXs: Boolean = false,
        hintColor: Color? = null,
        onComposed: @Composable () -> Unit = {}
    ) {
        val handler = object : OceanPinPadHandler<Unit> {
            override var uiState = OceanPinPadUIState(placeholder = "0", hint = hint)

            override fun newDigit(digit: String) = Unit

            override fun deleteLast() = Unit

            override fun clear() = Unit

            override fun getResult() = Unit

            @Composable
            override fun getErrorMessage(): String = ""
        }
        composeTestRule.setContent {
            onComposed()
            when {
                hintColor != null -> OceanPinPad(handler = handler, hintColor = hintColor)
                useFontSizeXs -> OceanPinPad(handler = handler, messageFontSize = OceanFontSize.xs)
                else -> OceanPinPad(handler = handler)
            }
        }
    }

    private fun layoutOf(text: String): TextLayoutResult {
        val results = mutableListOf<TextLayoutResult>()
        composeTestRule.onNodeWithText(text).fetchSemanticsNode()
            .config[SemanticsActions.GetTextLayoutResult].action?.invoke(results)
        return results.first()
    }

    private companion object {
        const val HINT = "Parcele em até 12x"
        const val LONG_HINT =
            "O parcelamento máximo é de 12x devido a uma configuração de split ativa nesta maquininha"
    }
}
