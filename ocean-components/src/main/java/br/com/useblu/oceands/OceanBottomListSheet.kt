package br.com.useblu.oceands

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanBottomListSheetBinding
import br.com.useblu.oceands.databinding.OceanBottomListSheetItemBinding
import br.com.useblu.oceands.databinding.OceanBottomListSheetWithIconItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class OceanBottomListSheet(context: Context) : BottomSheetDialog(context) {

    private var title: String? = null
    private var isDismiss: Boolean = true
    private var adapter: OceanBottomListSheetAdapter? = null
    private var adapterWithIcon: OceanBottomListSheetWithIconAdapter? = null
    private lateinit var binding: OceanBottomListSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.ocean_bottom_list_sheet,
            null,
            false
        )

        title?.let {
            binding.title = it
        }

        adapter?.let {
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        }

        adapterWithIcon?.let {
            binding.recyclerView.adapter = adapterWithIcon
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        }

        binding.cancelabled = isDismiss

        setCancelable(isDismiss)
        setCanceledOnTouchOutside(isDismiss)
        setContentView(binding.root)

        val view = binding.root.parent as View
        view.background = ColorDrawable(Color.TRANSPARENT)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        behavior.peekHeight = context.resources.displayMetrics.heightPixels
    }

    fun withTitle(title: String): OceanBottomListSheet {
        this.title = title
        return this
    }

    fun withTitle(title: Int): OceanBottomListSheet {
        this.title = context.getString(title)
        return this
    }

    fun withSimpleList(
        items: List<String>,
        selectedPosition: Int = -1,
        onItemSelect: (Int) -> Unit
    ): OceanBottomListSheet {
        adapter = OceanBottomListSheetAdapter(
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
        items: List<OceanBottomListSheetUIModel>,
        selectedPosition: Int = -1,
        onItemSelect: (Int) -> Unit
    ): OceanBottomListSheet {
        adapterWithIcon = OceanBottomListSheetWithIconAdapter(
            items = items,
            selected = selectedPosition,
            onSelect = {
                onItemSelect(it)
                dismiss()
            }
        )
        return this
    }

}

data class OceanBottomListSheetUIModel(
    val drawableIcon: Drawable,
    val title: String,
    val description: String
)

internal class OceanBottomListSheetAdapter(
    private val items: List<String>,
    private val onSelect: (Int) -> Unit,
    private val selected: Int = -1
) : RecyclerView.Adapter<OceanBottomListSheetAdapter.OceanBottomListSheetViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OceanBottomListSheetViewHolder {
        val itemBinding = OceanBottomListSheetItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return OceanBottomListSheetViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: OceanBottomListSheetViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = items.size

    inner class OceanBottomListSheetViewHolder(
        private val itemBinding: OceanBottomListSheetItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.textOceanParagraph.text = items[position]
            itemBinding.root.setOnClickListener { onSelect(position) }
            itemBinding.isSelected = position == selected
        }

    }

}

internal class OceanBottomListSheetWithIconAdapter(
    private val items: List<OceanBottomListSheetUIModel>,
    private val onSelect: (Int) -> Unit,
    private val selected: Int = -1
) : RecyclerView.Adapter<OceanBottomListSheetWithIconAdapter.OceanBottomListSheetWithIconViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OceanBottomListSheetWithIconViewHolder {
        val itemBinding = OceanBottomListSheetWithIconItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return OceanBottomListSheetWithIconViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: OceanBottomListSheetWithIconViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = items.size

    inner class OceanBottomListSheetWithIconViewHolder(
        private val itemBinding: OceanBottomListSheetWithIconItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.item = items[position]
            itemBinding.root.setOnClickListener { onSelect(position) }
            itemBinding.isSelected = position == selected
        }

    }

}