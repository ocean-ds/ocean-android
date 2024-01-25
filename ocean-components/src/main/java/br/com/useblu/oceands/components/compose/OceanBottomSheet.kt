package br.com.useblu.oceands.components.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons
import br.com.useblu.oceands.utils.UiText
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun OceanBottomSheetPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BottomSheetPreviewFactory(
            bottomSheetCta = "Bottom sheet",
            model = { showState ->
                OceanBottomSheetModel(
                    customContent = { sheetState ->
                        val coroutineScope = rememberCoroutineScope()
                        Column(Modifier.padding(vertical = 16.dp)) {
                            Text(text = "Teste de bottom sheet")

                            OceanButton(
                                text = "Fechar",
                                buttonStyle = OceanButtonStyle.SecondaryMedium,
                                onClick = {
                                    coroutineScope.launch {
                                        sheetState.hide()
                                    }.invokeOnCompletion {
                                        showState.value = false
                                    }
                                }
                            )
                        }
                    },
                    title = UiText.DynamicString("Teste de bottom sheet"),
                    icon = OceanIcons.CLOCK_OUTLINE.icon,
                    isDismissible = false
                )
            }
        )

        BottomSheetPreviewFactory(
            bottomSheetCta = "Bottom sheet com imagem",
            model = {
                OceanBottomSheetModel(
                    customContent = {
                        Text(text = "Teste de bottom sheet")
                    },
                    imageUrl = "https://portal-cicloentrada.blu.com.br/assets/icons/coin_trail-cc541831a7fbf4d215f3910fb241b14701f5ab0f79d574ad3a6e12379b7e871e.png",
                    title = UiText.DynamicString("Bottomsheet com Imagem")
                )
            }
        )

        BottomSheetPreviewFactory(
            bottomSheetCta = "Bottom sheet completa (exceto conteúdo custom)",
            model = {
                OceanBottomSheetModel(
                    title = UiText.DynamicString("Title"),
                    message = UiText.DynamicString("Message"),
                    subMessage = UiText.DynamicString("SubMessage"),
                    imageUrl = "https://portal-cicloentrada.blu.com.br/assets/icons/coin_trail-cc541831a7fbf4d215f3910fb241b14701f5ab0f79d574ad3a6e12379b7e871e.png",
                    code = 2000,
                    actionPositive = UiText.DynamicString("Aceitar") to {},
                    actionNegative = UiText.DynamicString("Cancelar") to {},
                    buttonsOrientation = BottomSheetButtonsOrientation.Vertical
                )
            }
        )

        BottomSheetPreviewFactory(
            bottomSheetCta = "Bottom sheet com dismiss",
            model = {
                OceanBottomSheetModel(
                    customContent = {
                        Text(text = "Teste de bottom sheet")
                    }
                )
            }
        )

        BottomSheetPreviewFactory(
            bottomSheetCta = "Bottom sheet com botão",
            model = {
                OceanBottomSheetModel(
                    customContent = {
                        Text(text = "Teste de bottom sheet")
                    },
                    actionPositive = UiText.DynamicString("Botão") to {},
                    code = 2000
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Immutable
data class OceanBottomSheetModel(
    val customContent: @Composable (SheetState) -> Unit = {},
    val isDismissible: Boolean = true,
    val isCritical: Boolean = false,
    val title: UiText? = null,
    val message: UiText? = null,
    val subMessage: UiText? = null,
    val code: Int? = null,
    @DrawableRes val icon: Int? = null,
    val imageUrl: String? = null,
    val actionPositive: Pair<UiText, () -> Unit>? = null,
    val actionNegative: Pair<UiText, () -> Unit>? = null,
    val buttonsOrientation: BottomSheetButtonsOrientation = BottomSheetButtonsOrientation.Horizontal
) {
    companion object
}

enum class BottomSheetButtonsOrientation {
    Horizontal, Vertical
}

@Composable
private fun BottomSheetPreviewFactory(
    bottomSheetCta: String,
    model: (MutableState<Boolean>) -> OceanBottomSheetModel
) {
    val showSheet = remember { mutableStateOf(false) }

    Button(
        onClick = {
            showSheet.value = true
        }
    ) {
        Text(bottomSheetCta)
    }

    if (showSheet.value) {
        OceanBottomSheet(
            model = model(showSheet),
            onDismiss = {
                showSheet.value = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OceanBottomSheet(
    model: OceanBottomSheetModel,
    onDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(
        confirmValueChange = { model.isDismissible },
        skipPartiallyExpanded = true
    )

    val onClickDismiss = remember {
        {
            scope.launch {
                sheetState.hide()
            }.invokeOnCompletion {
                onDismiss()
            }
        }
    }

    ModalBottomSheet(
        containerColor = OceanColors.interfaceLightPure,
        sheetState = sheetState,
        onDismissRequest = { onDismiss() },
        dragHandle = null
    ) {
        if (model.isDismissible) {
            IconButton(
                onClick = {
                    onClickDismiss()
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp, bottom = 4.dp)
                    .padding(end = 8.dp)
            ) {
                OceanIcon(
                    iconType = OceanIcons.X_OUTLINE,
                    tint = OceanColors.interfaceDarkUp,
                    modifier = Modifier.padding(6.dp)
                )
            }
        } else {
            Spacer(modifier = Modifier.size(40.dp))
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (model.icon != null) {
                Image(
                    painter = painterResource(id = model.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .padding(8.dp)
                        .heightIn(40.dp, 120.dp)
                        .widthIn(40.dp, 120.dp)
                )
            }

            if (model.imageUrl != null) {
                GlideImage(
                    imageModel = { model.imageUrl },
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .padding(8.dp)
                        .heightIn(40.dp, 120.dp)
                        .widthIn(40.dp, 120.dp),
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    )
                )
            }

            if (model.title != null) {
                val textColor = if (model.isCritical) {
                    OceanColors.statusNegativePure
                } else {
                    OceanColors.interfaceDarkDeep
                }

                OceanText(
                    text = model.title.asString(),
                    style = OceanTextStyle.heading3,
                    color = textColor,
                    textAlign = TextAlign.Center
                )
            }

            if (model.message != null) {
                OceanSpacing.StackXXS()
                OceanText(
                    text = model.message.asString(),
                    style = OceanTextStyle.paragraph,
                    textAlign = TextAlign.Center
                )
            }

            if (model.subMessage != null) {
                OceanSpacing.StackXXS()
                OceanText(
                    text = model.subMessage.asString(),
                    style = OceanTextStyle.paragraph,
                    fontFamily = OceanFontFamily.BaseBold,
                    textAlign = TextAlign.Center
                )
            }

            model.customContent(sheetState)

            BottomButtons(
                positiveButton = model.actionPositive,
                negativeButton = model.actionNegative,
                isCritical = model.isCritical,
                orientation = model.buttonsOrientation,
                onDismiss = {
                    onClickDismiss()
                }
            )

            if (model.code != null) {
                OceanSpacing.StackXS()
                Text(
                    text = "Código: ${model.code}",
                    style = OceanTextStyle.description,
                    color = OceanColors.interfaceDarkUp
                )
                OceanSpacing.StackXXS()
            }

            OceanSpacing.StackMD()
        }
    }
}

@Composable
private fun BottomButtons(
    positiveButton: Pair<UiText, () -> Unit>? = null,
    negativeButton: Pair<UiText, () -> Unit>? = null,
    isCritical: Boolean = false,
    orientation: BottomSheetButtonsOrientation = BottomSheetButtonsOrientation.Horizontal,
    onDismiss: () -> Unit
) {
    if (positiveButton == null && negativeButton == null) {
        return
    }

    val buttons: @Composable (Modifier) -> Unit = {
        val primaryStyle = if (isCritical) {
            OceanButtonStyle.PrimaryCriticalMedium
        } else OceanButtonStyle.PrimaryMedium

        if (positiveButton != null) {
            OceanButton(
                text = positiveButton.first.asString(),
                buttonStyle = primaryStyle,
                onClick = {
                    positiveButton.second()
                    onDismiss.invoke()
                },
                modifier = it
            )
        }

        if (negativeButton != null) {
            OceanSpacing.StackXS()

            OceanButton(
                text = negativeButton.first.asString(),
                buttonStyle = OceanButtonStyle.SecondaryMedium,
                onClick = {
                    negativeButton.second()
                    onDismiss.invoke()
                },
                modifier = it
            )
        }
    }

    val modifier = Modifier
        .padding(top = 24.dp)

    when (orientation) {
        BottomSheetButtonsOrientation.Horizontal -> {
            Row(
                modifier = modifier
            ) {
                buttons(Modifier.weight(1f))
            }
        }

        BottomSheetButtonsOrientation.Vertical -> {
            Column(
                modifier = modifier
            ) {
                buttons(Modifier.fillMaxWidth())
            }
        }
    }
}