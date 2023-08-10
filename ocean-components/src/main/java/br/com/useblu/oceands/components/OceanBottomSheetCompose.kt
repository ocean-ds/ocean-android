package br.com.useblu.oceands.components

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanBottomSheetComposeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OceanBottomSheetCompose(
    private val context: Context
): BottomSheetDialogFragment() {

    private var code: String? = null
    private var orientationButtons: Int = Orientation.Horizontal.direction
    private var isDismiss: Boolean = true
    private var textPositive: String? = context.getString(R.string.ok)
    private var textNegative: String? = null
    private var actionPositive: (() -> Unit?)? = null
    private var actionNegative: (() -> Unit?)? = null
    private var isCritical: Boolean = false
    private var composeContent: @Composable (() -> Unit)? = null
    private lateinit var binding: OceanBottomSheetComposeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OceanBottomSheetComposeBinding.inflate(inflater, container, false)

        textPositive?.let {
            binding.textPositive = it
        }

        binding.clickPositive = {
            dismiss()
            actionPositive?.let { it() }
        }

        textNegative?.let {
            binding.textNegative = it
        }

        binding.clickNegative = {
            dismiss()
            actionNegative?.let { it() }
        }

        code?.let {
            binding.code = it
        }

        composeContent?.let {
            binding.composeView.setContent(it)
        }

        binding.cancelabled = isDismiss
        binding.isCritical = isCritical
        binding.orientationButtons = orientationButtons

        isCancelable = isDismiss

        return binding.root
    }

    fun withComposeContent(content: @Composable ColumnScope.() -> Unit): OceanBottomSheetCompose {
        composeContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }

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
        this.orientationButtons = orientation.direction
        return this
    }

    fun withActionPositive(text: Int, callBack: () -> Unit): OceanBottomSheetCompose {
        this.textPositive = context.getString(text)
        this.actionPositive = callBack
        return this
    }

    fun withActionNegative(text: Int, callBack: () -> Unit): OceanBottomSheetCompose {
        this.textNegative = context.getString(text)
        this.actionNegative = callBack
        return this
    }

    fun withCritical(isCritical: Boolean): OceanBottomSheetCompose {
        this.isCritical = isCritical
        return this
    }

    sealed class Orientation(val direction: Int) {
        object Horizontal : Orientation(direction = LinearLayout.HORIZONTAL)
        object Vertical : Orientation(direction = LinearLayout.VERTICAL)
    }
}