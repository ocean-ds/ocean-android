package br.com.useblu.oceands.components.compose.pinpad

import android.util.Range
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.OceanDS
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanShimmering
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTextResizable
import br.com.useblu.oceands.extensions.compose.disabledOverlay
import br.com.useblu.oceands.model.compose.OceanViewStatus
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

@Composable
fun <Result> OceanPinPad(
    modifier: Modifier = Modifier,
    handler: OceanPinPadHandler<Result>,
    isLoading: Boolean = false,
    isEnabled: Boolean = true,
    haptics: OceanPinPadhapticsDelegate? = null
) {
    val focusRequester = remember { FocusRequester() }
    val usesPhysicalKeyboard = OceanDS.usesPhysicalNumericKeyboard()

    Column(
        modifier = modifier
            .background(OceanColors.interfaceLightPure)
            .fillMaxSize()
            .let {
                if (usesPhysicalKeyboard) {
                    it
                        .focusRequester(focusRequester)
                        .focusable()
                        .onKeyEvent { keyEvent ->
                            PinPadInputKeyHandler.onInputKey(
                                keyEvent = keyEvent,
                                enabled = isEnabled,
                                handler = handler
                            )
                        }
                } else {
                    it
                }
            }
            .disabledOverlay(isDisabled = isEnabled.not())
    ) {
        if (isLoading) {
            InputInfoSkeleton(modifier = Modifier.weight(1F))
            if (usesPhysicalKeyboard.not()) {
                InputPadSkeleton()
            }
            return@Column
        }
        InputInfo(
            modifier = Modifier.weight(1F),
            handler = handler,
            isEnabled = isEnabled
        )

        if (usesPhysicalKeyboard) return@Column
        InputPad(
            onClick = { digit ->
                handler.newDigit(digit = digit)
                haptics?.didTapKey(key = digit)
            },
            onDelete = {
                handler.deleteLast()
                haptics?.didTapDelete()
            },
            onClear = {
                handler.clear()
                haptics?.didTapClear()
            }
        )
    }

    if (usesPhysicalKeyboard && !isLoading) {
        SideEffect {
            focusRequester.requestFocus()
        }
    }
}

@Composable
private fun <Result> InputInfo(
    modifier: Modifier = Modifier,
    handler: OceanPinPadHandler<Result>,
    isEnabled: Boolean
) {
    val uiState = handler.uiState
    val oceanTextStyle = OceanTextStyle.display2.copy(
        fontFamily = OceanFontFamily.BaseMedium,
        fontSize = OceanFontSize.xxl
    )
    val status = when {
        isEnabled.not() -> OceanViewStatus.Disabled
        uiState.inputValue.isNotBlank() -> OceanViewStatus.Activated
        else -> OceanViewStatus.Enabled
    }

    Column(
        modifier = modifier
            .padding(horizontal = OceanSpacing.xs),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val color = if (handler.getErrorMessage().isNotBlank()) {
            OceanColors.statusNegativePure
        } else {
            OceanColors.interfaceLightDeep
        }
        OceanTextResizable(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.inputValue.ifBlank { uiState.placeholder },
            color = handler.getInputColor(status = status),
            textAlign = TextAlign.Center,
            maxLines = 1,
            softWrap = false,
            style = oceanTextStyle
        )
        OceanText(
            modifier = Modifier.fillMaxWidth(),
            text = handler.getErrorMessage().ifBlank { uiState.hint },
            color = color,
            fontFamily = OceanFontFamily.BaseMedium,
            fontSize = OceanFontSize.xxxs,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun InputPad(
    onClick: (digit: String) -> Unit,
    onDelete: () -> Unit,
    onClear: () -> Unit
) {
    Column(
        modifier = Modifier.padding(bottom = OceanSpacing.xxs),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.sm)
    ) {
        repeat(3) { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(3) { col ->
                    val number = row * 3L + col + 1L
                    NumberButton(
                        modifier = Modifier
                            .weight(1f),
                        number = number,
                        onClick = onClick
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ClearButton(modifier = Modifier.weight(1f), onClick = onClear)
            NumberButton(
                modifier = Modifier.weight(1f),
                number = 0,
                onClick = onClick
            )
            DeleteButton(modifier = Modifier.weight(1f), onClick = onDelete)
        }
    }
}

@Composable
private fun NumberButton(
    modifier: Modifier,
    number: Long,
    onClick: (digit: String) -> Unit
) {
    OutlinedButton(
        border = null,
        onClick = { onClick(number.toString()) },
        modifier = modifier
    ) {
        OceanText(
            textAlign = TextAlign.Center,
            text = number.toString(),
            fontSize = OceanFontSize.lg,
            fontFamily = OceanFontFamily.BaseMedium,
            color = OceanColors.interfaceDarkDeep
        )
    }
}

@Composable
private fun ClearButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        border = null,
        onClick = onClick,
        modifier = modifier
    ) {
        OceanIcon(
            modifier = Modifier
                .padding(OceanSpacing.xxxs)
                .size(24.dp),
            iconType = OceanIcons.X_SOLID
        )
    }
}

@Composable
private fun DeleteButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        border = null,
        onClick = onClick,
        modifier = modifier
    ) {
        OceanIcon(
            modifier = Modifier
                .padding(OceanSpacing.xxxs)
                .size(24.dp),
            iconType = OceanIcons.BACKSPACE_SOLID
        )
    }
}

@Composable
private fun InputInfoSkeleton(
    modifier: Modifier = Modifier
) {
    OceanShimmering { brush ->
        Column(
            modifier = modifier.padding(horizontal = OceanSpacing.xxs),
            verticalArrangement = Arrangement.spacedBy(
                space = OceanSpacing.xxxs,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .padding(horizontal = OceanSpacing.md)
                    .borderBackground(
                        brush = brush,
                        borderRadius = OceanBorderRadius.MD.allCorners
                    )
                    .height(OceanSpacing.xxl)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun InputPadSkeleton() {
    OceanShimmering { brush ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.sm)
        ) {
            repeat(4) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.padding(OceanSpacing.xxxs))
                    repeat(3) {
                        Spacer(
                            modifier = Modifier
                                .borderBackground(
                                    brush = brush,
                                    borderRadius = OceanBorderRadius.MD.allCorners
                                )
                                .padding(OceanSpacing.xxxs)
                                .weight(1f)
                                .height(56.dp)
                        )
                        Spacer(modifier = Modifier.padding(OceanSpacing.xxxs))
                    }
                }
            }
        }
    }
}

@Composable
private fun autoResizeText(
    textStyle: TextStyle,
    onTextLayoutResult: (TextStyle, Boolean) -> Unit
): (TextLayoutResult) -> Unit {
    val baseFontSize = Range(OceanFontSize.xxxs.value, textStyle.fontSize.value)
    var mutableTextStyle: TextStyle = textStyle

    return { textLayoutResult ->
        when {
            textStyle.fontSize > baseFontSize.upper.sp -> {
                mutableTextStyle = textStyle.copy(fontSize = baseFontSize.upper.sp)
            }

            textStyle.fontSize < baseFontSize.lower.sp -> {
                mutableTextStyle = textStyle.copy(fontSize = baseFontSize.lower.sp)
            }

            textLayoutResult.didOverflowWidth -> {
                mutableTextStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9f)
            }
        }
        onTextLayoutResult(mutableTextStyle, true)
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun OceanPinPadPreview() {
    OceanPinPad(
        handler = object : OceanPinPadHandler<Unit> {
            override val uiState = OceanPinPadUIState(
                inputValue = "R$ 1.100.234,56",
                placeholder = "R$ 0,00",
                hint = "Hint message"
            )

            override fun newDigit(digit: String) { /* no-op */ }
            override fun deleteLast() { /* no-op */ }
            override fun clear() { /* no-op */ }
            override fun getResult() { /* no-op */ }

            @Composable
            override fun getErrorMessage(): String = ""
        },
        isLoading = false
    )
}
