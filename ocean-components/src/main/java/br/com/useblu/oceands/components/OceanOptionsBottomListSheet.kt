package br.com.useblu.oceands.components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanOptionsBottomListSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

internal class OceanOptionsBottomListSheet(context: Context) : BottomSheetDialog(context) {

    private var title: String? = null
    private var titleColor: Int? = null
    private var isDismiss: Boolean = true
    private var adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>? = null
    private var primaryButtonText: String? = null
    private var secondaryButtonText: String? = null
    private var primaryButtonAction: (() -> Unit)? = null
    private var secondaryButtonAction: (() -> Unit)? = null
    private var withFooterButton: Boolean = false

    private var shouldShowDivider: Boolean = true

    private lateinit var binding: OceanOptionsBottomListSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OceanOptionsBottomListSheetBinding.inflate(
            LayoutInflater.from(context),
            null,
            false
        )

        binding.title = title
        binding.titleColor = titleColor

        binding.showFooterButton = withFooterButton

        binding.bottomSheetListButtonPrimary.text = primaryButtonText
        binding.bottomSheetListButtonSecondary.text = secondaryButtonText

        primaryButtonAction?.let {
            binding.bottomSheetListButtonPrimary.click = it
        }

        secondaryButtonAction?.let {
            binding.bottomSheetListButtonSecondary.click = it
        }

        adapter?.let {
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        }

        binding.dismissIcon.setOnClickListener {
            dismiss()
        }

        setCancelable(isDismiss)
        setCanceledOnTouchOutside(isDismiss)
        setContentView(binding.root)

        if (shouldShowDivider.not())
            binding.dividerButtons.root.visibility = View.GONE

        val view = binding.root.parent as View
        view.background = ColorDrawable(Color.TRANSPARENT)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        behavior.peekHeight = context.resources.displayMetrics.heightPixels
    }

    fun withTitle(
        title: String,
        titleColor: Int = context.getColor(R.color.ocean_color_interface_dark_deep)
    ): OceanOptionsBottomListSheet {
        this.title = title
        this.titleColor = titleColor
        return this
    }

    fun withFooterButton(
        primaryText: String,
        secondaryText: String,
        primaryAction: () -> Unit,
        secondaryAction: (() -> Unit)? = null,
        shouldShowDivider: Boolean = true
    ): OceanOptionsBottomListSheet {
        this.withFooterButton = true
        this.primaryButtonText = primaryText
        this.secondaryButtonText = secondaryText
        this.primaryButtonAction = {
            primaryAction()
            dismiss()
        }
        this.secondaryButtonAction = {
            secondaryAction?.invoke()
            dismiss()
        }
        this.shouldShowDivider = shouldShowDivider
        return this
    }

    fun withCustomList(
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>
    ): OceanOptionsBottomListSheet {
        this.adapter = adapter
        return this
    }
}
