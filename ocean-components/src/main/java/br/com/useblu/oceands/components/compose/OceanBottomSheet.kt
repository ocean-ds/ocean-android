package br.com.useblu.oceands.components.compose

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
import kotlinx.coroutines.DisposableHandle
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

                            OceanButton(text = "Fechar", buttonStyle = OceanButtonStyle.SecondaryMedium) {
                                coroutineScope.launch {
                                    sheetState.hide()
                                }.invokeOnCompletion {
                                    showState.value = false
                                }
                            }
                        }
                    },
                    title = "Teste de bottom sheet",
                    icon = OceanIcons.CLOCK_OUTLINE,
                    isDismissable = false
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
                    title = "Bottomsheet com Imagem"
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
                    actionPositive = "Botão" to {},
                    code = 2000
                )
            }
        )
    }
}

@Immutable
data class OceanBottomSheetModel @OptIn(ExperimentalMaterial3Api::class) constructor(
    val customContent: @Composable (SheetState) -> Unit = {},
    val isDismissable: Boolean = true,
    val isCritical: Boolean = false,
    val title: String? = null,
    val message: String? = null,
    val subMessage: String? = null,
    val code: Int? = null,
    val icon: OceanIcons? = null,
    val imageUrl: String? = null,
    val actionPositive: Pair<String, () -> Unit>? = null,
    val actionNegative: Pair<String, () -> Unit>? = null,
    val buttonsOrientation: BottomSheetButtonsOrientation = BottomSheetButtonsOrientation.Horizontal
)

enum class BottomSheetButtonsOrientation {
    Horizontal, Vertical
}

@Composable
private fun BottomSheetPreviewFactory(
    bottomSheetCta: String,
    model: (MutableState<Boolean>) -> OceanBottomSheetModel
) {
    val showBottomSheet = remember { mutableStateOf(false) }
    Button(onClick = { showBottomSheet.value = true }) {
        Text(bottomSheetCta)
    }

    OceanBottomSheet(
        showBottomSheet = showBottomSheet,
        model = model(showBottomSheet)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OceanBottomSheet(
    showBottomSheet: MutableState<Boolean>,
    model: OceanBottomSheetModel
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        confirmValueChange = { model.isDismissable }
    )

    val dimissBottomSheet = remember {
        {
            scope.launch {
                sheetState.hide()
            }.invokeOnCompletion {
                showBottomSheet.value = false
            }
        }
    }

    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet.value = false
            },
            sheetState = sheetState,
            dragHandle = null
        ) {
            if (model.isDismissable) {
                IconButton(
                    onClick = { dimissBottomSheet() },
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
                    OceanIcon(
                        iconType = model.icon
                    )
                }

                if (model.imageUrl != null) {
                    GlideImage(
                        imageModel = { model.imageUrl },
                        modifier = Modifier
                            .heightIn(40.dp, 120.dp)
                            .widthIn(40.dp, 120.dp)
                            .padding(bottom = 24.dp)
                            .padding(8.dp),
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

                    Text(
                        text = model.title,
                        style = OceanTextStyle.heading3,
                        color = textColor
                    )
                }

                if (model.message != null) {
                    OceanSpacing.StackXXS()
                    Text(
                        text = model.message,
                        style = OceanTextStyle.paragraph
                    )
                }

                if (model.subMessage != null) {
                    OceanSpacing.StackXXS()
                    Text(
                        text = model.subMessage,
                        style = OceanTextStyle.paragraph,
                        fontFamily = OceanFontFamily.BaseBold
                    )
                }

                model.customContent(sheetState)

                BottomButtons(
                    positiveButton = model.actionPositive,
                    negativeButton = model.actionNegative,
                    isCritical = model.isCritical,
                    orientation = model.buttonsOrientation,
                    dismissAction = dimissBottomSheet
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
            }
        }
    }
}

@Composable
private fun BottomButtons(
    positiveButton: Pair<String, () -> Unit>? = null,
    negativeButton: Pair<String, () -> Unit>? = null,
    isCritical: Boolean = false,
    orientation: BottomSheetButtonsOrientation = BottomSheetButtonsOrientation.Horizontal,
    dismissAction: () -> DisposableHandle
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
                text = positiveButton.first,
                buttonStyle = primaryStyle,
                onClick = {
                    positiveButton.second()
                    dismissAction()
              },
                modifier = it
            )
        }

        if (negativeButton != null) {
            OceanSpacing.StackXS()

            OceanButton(
                text = negativeButton.first,
                buttonStyle = OceanButtonStyle.SecondaryMedium,
                onClick = {
                    negativeButton.second()
                    dismissAction()
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