package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.utils.OceanIcons
import kotlinx.coroutines.launch

@Preview
@Composable
private fun OceanBottomSheetPreview() {
    val showBottomSheet = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Teste de bottom sheet")
        Button(onClick = { showBottomSheet.value = true }) {
            Text("Show bottom sheet")
        }

        OceanBottomSheet(
            content = {
                Text(text = "Teste de bottom sheet")
            },
            showBottomSheet = showBottomSheet
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OceanBottomSheet(
    content: @Composable () -> Unit,
    showBottomSheet: MutableState<Boolean>,
    isDismissable: Boolean = true
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                if (isDismissable) {
                    showBottomSheet.value = false
                }
            },
            sheetState = sheetState
        ) {
            if (isDismissable) {
                IconButton(
                    onClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            showBottomSheet.value = false
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    OceanIcon(
                        iconType = OceanIcons.X_OUTLINE,
                        tint = OceanColors.interfaceDarkUp
                    )
                }
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                content()
            }
        }
    }
}