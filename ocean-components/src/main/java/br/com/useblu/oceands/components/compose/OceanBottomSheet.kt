package br.com.useblu.oceands.components.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun OceanBottomSheetPreview() {
    OceanTheme {
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

                                OceanRadioButton(label = "Testeeee")

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
                        title = "Teste de bottom sheet",
                        icon = OceanIcons.CLOCK_OUTLINE.icon,
                        isDismissible = false,
                        onDismiss = {
                            showState.value = false
                        }
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
                        title = "Bottomsheet com Imagem",
                        onDismiss = {
                            it.value = false
                        }
                    )
                }
            )

            BottomSheetPreviewFactory(
                bottomSheetCta = "Bottom sheet completa (exceto conteúdo custom)",
                model = {
                    OceanBottomSheetModel(
                        title = "Title",
                        message = "Message",
                        subMessage = "SubMessage",
                        imageUrl = "https://portal-cicloentrada.blu.com.br/assets/icons/coin_trail-cc541831a7fbf4d215f3910fb241b14701f5ab0f79d574ad3a6e12379b7e871e.png",
                        code = 2000,
                        actionPositive = OceanBottomSheetModel.Button(
                            text = "Aceitar",
                            onClick = {}
                        ),
                        actionNegative = OceanBottomSheetModel.Button(
                            text = "Cancelar",
                            onClick = {}
                        ),
                        buttonsOrientation = BottomSheetButtonsOrientation.Vertical,
                        onDismiss = {
                            it.value = false
                        }
                    )
                }
            )

            BottomSheetPreviewFactory(
                bottomSheetCta = "Bottom sheet com dismiss",
                model = {
                    OceanBottomSheetModel(
                        customContent = {
                            Text(text = "Teste de bottom sheet")
                        },
                        onDismiss = {
                            it.value = false
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
                        actionPositive = OceanBottomSheetModel.Button(
                            text = "Botão",
                            icon = OceanIcons.WHATSAPP_SOLID,
                            onClick = {
                                println("Botão clicado")
                            },
                        ),
                        code = 2000,
                        onDismiss = {
                            it.value = false
                        }
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Immutable
data class OceanBottomSheetModel(
    val customContent: @Composable (SheetState) -> Unit = {},
    val isDismissible: Boolean = true,
    val isCritical: Boolean = false,
    val title: String? = null,
    val message: String? = null,
    val subMessage: String? = null,
    val code: Int? = null,
    @DrawableRes val icon: Int? = null,
    val imageUrl: String? = null,
    val maxWidth: Int? = null,
    val maxHeight: Int? = null,
    val actionPositive: Button? = null,
    val actionNegative: Button? = null,
    val buttonsOrientation: BottomSheetButtonsOrientation = BottomSheetButtonsOrientation.Horizontal,
    val onDismiss: () -> Unit
) {
    companion object

    class Button(
        val text: String,
        val icon: OceanIcons? = null,
        val onClick: () -> Unit,
        val isDisabled: Boolean = false
    )
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
            model = model(showSheet)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OceanBottomSheet(
    modifier: Modifier = Modifier,
    model: OceanBottomSheetModel
) {

    val sheetState = rememberModalBottomSheetState(
        confirmValueChange = { model.isDismissible },
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        containerColor = OceanColors.interfaceLightPure,
        sheetState = sheetState,
        onDismissRequest = { model.onDismiss() },
        dragHandle = null
    ) {
        Row(
            modifier = Modifier
                .background(OceanColors.interfaceLightPure)
                .padding(
                    top = OceanSpacing.xxs,
                    bottom = OceanSpacing.xxxs,
                    end = OceanSpacing.xxs
                )
                .height(40.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            if (model.isDismissible) {
                IconButton(
                    onClick = { model.onDismiss() },
                ) {
                    OceanIcon(
                        iconType = OceanIcons.X_OUTLINE,
                        tint = OceanColors.interfaceDarkUp,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
        }

        Column(
            modifier = modifier
                .background(OceanColors.interfaceLightPure)
                .padding(OceanSpacing.xs)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (model.icon != null) {
                Image(
                    painter = painterResource(id = model.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = OceanSpacing.sm)
                        .padding(OceanSpacing.xxs)
                        .heightIn(40.dp, 120.dp)
                        .widthIn(40.dp, 120.dp)
                )
            }

            if (model.imageUrl != null) {
                GlideImage(
                    imageModel = { model.imageUrl },
                    modifier = Modifier
                        .padding(bottom = OceanSpacing.sm)
                        .padding(OceanSpacing.xxs)
                        .heightIn(40.dp, model.maxHeight?.dp ?: 120.dp)
                        .widthIn(40.dp, model.maxWidth?.dp ?: 120.dp),
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
                    text = model.title,
                    style = OceanTextStyle.heading3,
                    color = textColor,
                    textAlign = TextAlign.Center
                )
            }

            if (model.message != null) {
                OceanSpacing.StackXXS()
                OceanText(
                    text = model.message,
                    style = OceanTextStyle.paragraph,
                    textAlign = TextAlign.Center
                )
            }

            if (model.subMessage != null) {
                OceanSpacing.StackXXS()
                OceanText(
                    text = model.subMessage,
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
                    model.onDismiss()
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
    positiveButton: OceanBottomSheetModel.Button? = null,
    negativeButton: OceanBottomSheetModel.Button? = null,
    isCritical: Boolean = false,
    orientation: BottomSheetButtonsOrientation = BottomSheetButtonsOrientation.Horizontal,
    onDismiss: () -> Unit
) {
    if (positiveButton == null && negativeButton == null) {
        return
    }

    val buttons = mutableListOf<@Composable (Modifier) -> Unit>()

    if (positiveButton != null) {
        buttons.add {
            val primaryStyle = if (isCritical) {
                OceanButtonStyle.PrimaryCriticalMedium
            } else OceanButtonStyle.PrimaryMedium

            OceanButton(
                text = positiveButton.text,
                buttonStyle = primaryStyle,
                icon = positiveButton.icon,
                onClick = {
                    onDismiss.invoke().run {
                        positiveButton.onClick.invoke()
                    }
                },
                modifier = it,
                disabled = positiveButton.isDisabled
            )
        }
    }

    if (negativeButton != null) {
        buttons.add {
            OceanButton(
                text = negativeButton.text,
                icon = negativeButton.icon,
                buttonStyle = OceanButtonStyle.SecondaryMedium,
                onClick = {
                    onDismiss.invoke().run {
                        negativeButton.onClick.invoke()
                    }
                },
                modifier = it,
                disabled = negativeButton.isDisabled
            )
        }
    }

    val modifier = Modifier
        .padding(top = OceanSpacing.sm)

    when (orientation) {
        BottomSheetButtonsOrientation.Horizontal -> {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
            ) {
                buttons.reversed().forEach {
                    it(Modifier.weight(1f))
                }
            }
        }

        BottomSheetButtonsOrientation.Vertical -> {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
            ) {
                buttons.forEach {
                    it(Modifier.fillMaxWidth())
                }
            }
        }
    }
}
