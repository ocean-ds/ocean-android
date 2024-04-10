package br.com.useblu.oceands.ui.compose.input

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isFocusable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performKeyPress
import androidx.compose.ui.test.performTextInput
import br.com.useblu.oceands.components.compose.input.OceanTextInput
import br.com.useblu.oceands.ui.compose.stringmask.OceanInputType
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OceanTextInputTest {

    @get:Rule val composeTestRule = createComposeRule()

    private val inputText = mutableStateOf("")
    @Test
    fun testCurrencyType() {
        setupComposeContent(OceanInputType.Currency())

        composeTestRule.onNode(isFocusable()).run {
            performTextInput("1")
            this.assert(hasText("0,01"))
            performTextInput("2")
            this.assert(hasText("0,12"))
            performKeyPress(
                KeyEvent(
                    NativeKeyEvent(
                        NativeKeyEvent.ACTION_DOWN,
                        NativeKeyEvent.KEYCODE_DPAD_LEFT
                    )
                )
            )
            performTextInput("3")
            this.assert(hasText("1,32"))
            performTextInput("4")
            this.assert(hasText("13,24"))
            performTextInput("56789")
            this.assert(hasText("1.324.567,89"))
        }

        Assert.assertEquals("1324567.89", inputText.value)
    }

    @Test
    fun testCEPType() {
        setupComposeContent(OceanInputType.CEP)

        composeTestRule.onNode(isFocusable()).run {
            performTextInput("12345")
            this.assert(hasText("12345"))
            performTextInput("6")
            this.assert(hasText("12345-6"))
            performKeyPress(
                KeyEvent(
                    NativeKeyEvent(
                        NativeKeyEvent.ACTION_DOWN,
                        NativeKeyEvent.KEYCODE_DPAD_LEFT
                    )
                )
            )
            performTextInput("3")
            this.assert(hasText("12345-36"))
            performTextInput("4")
            this.assert(hasText("12345-346"))
        }

        Assert.assertEquals("12345346", inputText.value)
    }

    private fun setupComposeContent(
        oceanInputType: OceanInputType
    ) {
        composeTestRule.setContent {
            OceanTextInput(
                value = inputText.value,
                label = "Label",
                onTextChanged = {
                    inputText.value = it
                },
                oceanInputType = oceanInputType
            )
        }
    }
}