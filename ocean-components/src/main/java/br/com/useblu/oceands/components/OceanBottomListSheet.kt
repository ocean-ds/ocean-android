package br.com.useblu.oceands.components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.adapter.OceanBottomListSheetAdapter
import br.com.useblu.oceands.adapter.OceanBottomListSheetWithIconAdapter
import br.com.useblu.oceands.databinding.OceanBottomListSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class OceanBottomListSheet(context: Context) : BottomSheetDialog(context) {

    private var title: String? = null
    private var hint: String? = null
    private var isDismiss: Boolean = true
    private var adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>? = null
    private var manager: FragmentManager? = null
    private var limit: Int? = null
    private var bodyIcon: Drawable? = null
    private var buttonText: String? = null
    private var buttonIcon: Drawable? = null
    private var buttonClick: (() -> Unit)? = null
    private var buttonLoading : LiveData<Boolean>? = null
    private var useSecondaryButtonStyle: Boolean = false
    private var caption: String? = null

    private lateinit var binding: OceanBottomListSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.ocean_bottom_list_sheet,
            null,
            false
        )
        
        binding.lifecycleOwner = this

        binding.title = title

        bodyIcon?.let {
            binding.bodyIcon = it
        }

        buttonText?.let {
            binding.bottomSheetListButtonPrimary.text = it
            binding.bottomSheetListButtonSecondary.text = it
        }

        buttonIcon?.let {
            binding.bottomSheetListButtonPrimary.icon = it
            binding.bottomSheetListButtonSecondary.icon = it
        }

        buttonClick?.let{
            binding.bottomSheetListButtonPrimary.click = it
            binding.bottomSheetListButtonSecondary.click = it
        }

        buttonLoading?.let {
            binding.loading = it
        }

        if (useSecondaryButtonStyle) {
            binding.bottomSheetListButtonPrimary.root.visibility = View.GONE
            binding.bottomSheetListButtonSecondary.root.visibility = View.VISIBLE
        }

        binding.caption = caption

        adapter?.let {
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        }

        binding.cancelabled = isDismiss
        binding.showFooterButton = buttonText != null

        setCancelable(isDismiss)
        setCanceledOnTouchOutside(isDismiss)
        setContentView(binding.root)

        val view = binding.root.parent as View
        view.background = ColorDrawable(Color.TRANSPARENT)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        behavior.peekHeight = context.resources.displayMetrics.heightPixels
    }

    fun withDismiss(dismiss: Boolean): OceanBottomListSheet {
        this.isDismiss = dismiss
        return this
    }

    fun withTitle(title: String): OceanBottomListSheet {
        this.title = title
        return this
    }

    fun withTitle(title: Int): OceanBottomListSheet {
        this.title = context.getString(title)
        return this
    }

    fun withHint(hint: String): OceanBottomListSheet {
        this.hint = hint
        return this
    }

    fun withHint(hint: Int): OceanBottomListSheet {
        this.hint = context.getString(hint)
        return this
    }

    fun withSearch(manager: FragmentManager, limit: Int): OceanBottomListSheet {
        this.limit = limit
        this.manager = manager
        return this
    }

    fun withFooterButton(
        text: String,
        icon: Drawable? = null,
        loading: LiveData<Boolean>? = null,
        click: () -> Unit,
        useSecondaryStyle: Boolean = false
    ): OceanBottomListSheet {
        this.buttonText = text
        this.buttonIcon = icon
        this.buttonClick = {
            click()
            dismiss()
        }
        this.buttonLoading = loading
        this.useSecondaryButtonStyle = useSecondaryStyle
        return this
    }

    fun withSimpleList(
        items: List<String>,
        selectedPosition: Int = -1,
        onItemSelect: (Int) -> Unit,
    ): OceanBottomListSheet {
        adapter = OceanBottomListSheetAdapter(
            oceanBottomSheet = this,
            items = items,
            selected = selectedPosition,
            onSelect = {
                onItemSelect(it)
                dismiss()
            },
            limit = limit,
            title = title,
            hint = hint,
            manager = manager
        )
        return this
    }

    fun withCustomList(
        items: List<OceanBottomListSheetUIModel>,
        selectedPosition: Int = -1,
        onItemSelect: (Int) -> Unit
    ): OceanBottomListSheet {
        adapter = OceanBottomListSheetWithIconAdapter(
            items = items,
            selected = selectedPosition,
            onSelect = {
                onItemSelect(it)
                dismiss()
            }
        )
        return this
    }

    fun withCustomList(
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>
    ): OceanBottomListSheet {
        this.adapter = adapter
        return this
    }

    fun withCaption(caption: String): OceanBottomListSheet {
        this.caption = caption
        return this
    }

    fun withBodyIcon(icon: Drawable?): OceanBottomListSheet {
        this.bodyIcon = icon
        return this
    }
}

data class OceanBottomListSheetUIModel(
    val drawableIcon: Drawable?,
    val title: String,
    val description: String
)
