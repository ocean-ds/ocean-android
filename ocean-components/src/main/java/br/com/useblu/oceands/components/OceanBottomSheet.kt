package br.com.useblu.oceands.components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class OceanBottomSheet(context: Context) : BottomSheetDialog(context) {

    private var title: String? = null
    private var message: String? = null
    private var subMessage: String? = null
    private var code: String? = null
    private var orientationButtons: Int = Orientation.Horizontal.direction
    private var isDismiss: Boolean = true
    private var textPositive: String? = context.getString(R.string.ok)
    private var textNegative: String? = null
    private var icon: Drawable? = null
    private var image: String? = null
    private var actionPositive: (() -> Unit?)? = null
    private var actionNegative: (() -> Unit?)? = null
    private var isCritical: Boolean = false

    private lateinit var binding: OceanBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.ocean_bottom_sheet,
            null,
            false
        )

        title?.let {
            binding.title = it
        }

        message?.let {
            binding.message = it
        }

        subMessage?.let {
            binding.subMessage = it
        }

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

        icon?.let {
            binding.icon = it
        }

        image?.let {
            binding.image = it
        }

        code?.let {
            binding.code = it
        }

        binding.cancelabled = isDismiss
        binding.isCritical = isCritical
        binding.orientationButtons = orientationButtons

        setCancelable(isDismiss)
        setCanceledOnTouchOutside(isDismiss)
        setContentView(binding.root)

        val view = binding.root.parent as View
        view.background = ColorDrawable(Color.TRANSPARENT)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        behavior.peekHeight = context.resources.displayMetrics.heightPixels
    }

    fun withTitle(title: String): OceanBottomSheet {
        this.title = title
        return this
    }

    fun withTitle(title: Int): OceanBottomSheet {
        this.title = context.getString(title)
        return this
    }

    fun withCode(code: String): OceanBottomSheet {
        this.code = code
        return this
    }

    fun withMessage(message: String): OceanBottomSheet {
        this.message = message
        return this
    }

    fun withMessage(message: Int): OceanBottomSheet {
        this.message = context.getString(message)
        return this
    }

    fun withSubMessage(subMessage: String): OceanBottomSheet {
        this.subMessage = subMessage
        return this
    }

    fun withSubMessage(subMessage: Int): OceanBottomSheet {
        this.subMessage = context.getString(subMessage)
        return this
    }

    fun withIcon(icon: Int): OceanBottomSheet {
        val drawable = ContextCompat.getDrawable(context, icon)
        drawable?.let {
            this.icon = it
        }
        return this
    }

    fun withImage(image: String): OceanBottomSheet {
        this.image = image
        return this
    }

    fun withDismiss(dismiss: Boolean): OceanBottomSheet {
        this.isDismiss = dismiss
        return this
    }

    fun withOrientationButtons(orientation: Orientation): OceanBottomSheet {
        this.orientationButtons = orientation.direction
        return this
    }

    fun withActionPositive(text: Int, callBack: () -> Unit): OceanBottomSheet {
        this.textPositive = context.getString(text)
        this.actionPositive = callBack
        return this
    }

    fun withActionNegative(text: Int, callBack: () -> Unit): OceanBottomSheet {
        this.textNegative = context.getString(text)
        this.actionNegative = callBack
        return this
    }

    fun withCritical(isCritical: Boolean): OceanBottomSheet {
        this.isCritical = isCritical
        return this
    }

    sealed class Orientation(val direction: Int) {
        object Horizontal : Orientation(direction = LinearLayout.HORIZONTAL)
        object Vertical : Orientation(direction = LinearLayout.VERTICAL)
    }
}