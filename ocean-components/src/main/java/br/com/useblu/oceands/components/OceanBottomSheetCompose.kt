package br.com.useblu.oceands.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.databinding.OceanBottomSheetComposeBinding
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanSpacing
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OceanBottomSheetCompose: BottomSheetDialogFragment() {

    private var code: String? = null
    private var orientationButtons: Orientation = Orientation.Horizontal
    private var isDismiss: Boolean = true
    private var textPositive: String = ""
    private var textNegative: String? = null
    private var actionPositive: (() -> Unit) = { }
    private var actionNegative: (() -> Unit)? = null
    private var isCritical: Boolean = false
    private var composeContent: @Composable (ColumnScope.() -> Unit) = {}
    private lateinit var binding: OceanBottomSheetComposeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OceanBottomSheetComposeBinding.inflate(inflater, container, false)

        binding.clickNegative = {
            dismiss()
            actionNegative?.let { it() }
        }

        code?.let {
            binding.code = it
        }

        binding.composeView.setContent {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                composeContent()

                BottomButtons(
                    positiveLabel = textPositive,
                    positiveAction = {
                        actionPositive.invoke()
                        dismiss()
                    },
                    negativeLabel = textNegative,
                    negativeAction = {
                        actionNegative?.invoke()
                        dismiss()
                    },
                    orientation = orientationButtons
                )
            }

        }

        binding.cancelabled = isDismiss
        isCancelable = isDismiss

        return binding.root
    }

    fun withComposeContent(content: @Composable ColumnScope.() -> Unit): OceanBottomSheetCompose {
        composeContent = content

        return this
    }

    fun withCode(code: String): OceanBottomSheetCompose {
        this.code = code
        return this
    }

    fun withDismiss(dismiss: Boolean): OceanBottomSheetCompose {
        this.isDismiss = dismiss
        return this
    }

    fun withOrientationButtons(orientation: Orientation): OceanBottomSheetCompose {
        this.orientationButtons = orientation
        return this
    }

    fun withActionPositive(text: String, callBack: () -> Unit): OceanBottomSheetCompose {
        this.textPositive = text
        this.actionPositive = callBack
        return this
    }

    fun withActionNegative(text: String, callBack: () -> Unit): OceanBottomSheetCompose {
        this.textNegative = text
        this.actionNegative = callBack
        return this
    }

    fun withCritical(isCritical: Boolean): OceanBottomSheetCompose {
        this.isCritical = isCritical
        return this
    }

    enum class Orientation {
        Horizontal, Vertical
    }
}


@Preview
@Composable
private fun BottomButtonsPreview() {
    BottomButtons(
        positiveLabel = "Teste",
        positiveAction = {},
        negativeLabel = "Cancelar",
        negativeAction = {},
        orientation = OceanBottomSheetCompose.Orientation.Horizontal,
        isCritical = true
    )
}

@Composable
private fun BottomButtons(
    positiveLabel: String,
    positiveAction: () -> Unit,
    negativeLabel: String? = null,
    negativeAction: (() -> Unit)? = null,
    isCritical: Boolean = false,
    orientation: OceanBottomSheetCompose.Orientation = OceanBottomSheetCompose.Orientation.Horizontal
) {

    val buttons: @Composable (Modifier) -> Unit = {
        val primaryStyle = if (isCritical) {
            OceanButtonStyle.PrimaryCriticalMedium
        } else OceanButtonStyle.PrimaryMedium

        OceanButton(
            text = positiveLabel,
            buttonStyle = primaryStyle,
            onClick = { positiveAction.invoke() },
            modifier = it
        )

        if (negativeLabel != null && negativeAction != null) {
            OceanSpacing.StackXS()

            OceanButton(
                text = negativeLabel,
                buttonStyle = OceanButtonStyle.SecondaryMedium,
                onClick = { negativeAction.invoke()},
                modifier = it
            )
        }
    }

    val modifier = Modifier
        .padding(top = 24.dp)

    when (orientation) {
        OceanBottomSheetCompose.Orientation.Horizontal -> {
            Row(
                modifier = modifier
            ) {
                buttons(Modifier.weight(1f))
            }
        }
        OceanBottomSheetCompose.Orientation.Vertical -> {
            Column(
                modifier = modifier
            ) {
                buttons(Modifier.fillMaxWidth())
            }
        }
    }
}