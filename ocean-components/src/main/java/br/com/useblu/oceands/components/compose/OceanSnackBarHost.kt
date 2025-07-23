package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun OceanSnackBarHost(
    showSnackBar: Boolean,
    type: OceanSnackBarType,
    message: String,
    onAction: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    val snackState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = showSnackBar) {
        if (showSnackBar) {
            coroutineScope.launch {
                val result = snackState.showSnackbar(message)
                when (result) {
                    SnackbarResult.ActionPerformed -> onAction()
                    SnackbarResult.Dismissed -> onDismiss()
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        SnackbarHost(
            modifier = Modifier.align(Alignment.BottomStart),
            hostState = snackState
        ) { snackBarData ->
            OceanSnackBar(
                type = type,
                text = snackBarData.visuals.message
            )
        }
    }
}
