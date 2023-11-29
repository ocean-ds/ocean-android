package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.utils.OceanIcons
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
                        Text(text = "Teste de bottom sheet")

                        OceanButton(text = "Fechar", buttonStyle = OceanButtonStyle.PrimaryMedium) {
                            coroutineScope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                showState.value = false
                            }
                        }
                    },
                    isDismissable = false
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
    val code: String? = null,
    val icons: OceanIcons? = null,
    val actionPositive: Pair<String, () -> Unit>? = null,
    val actionNegative: Pair<String, () -> Unit>? = null
)

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
                    onClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            showBottomSheet.value = false
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                        .padding(top = 8.dp, bottom = 4.dp)
                        .padding(end = 8.dp)
                ) {
                    OceanIcon(
                        iconType = OceanIcons.X_OUTLINE,
                        tint = OceanColors.interfaceDarkUp,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                model.customContent(sheetState)
            }
        }
    }
}