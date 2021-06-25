package br.com.useblu.oceands

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanBottomListSheetBinding
import br.com.useblu.oceands.databinding.OceanBottomListSheetItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class OceanBottomListSheet(context: Context) : BottomSheetDialog(context) {

    private var title: String? = null
    private var items: List<String> = listOf()
    private var isDismiss: Boolean = true
    private var adapter: OceanBottomListSheetAdapter? = null
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

    fun withList(items: List<String>, onItemSelect: (Int) -> Unit): OceanBottomListSheet {
        adapter = OceanBottomListSheetAdapter(
            items = items,
            onSelect = {
                onItemSelect(it)
                dismiss()
            }
        )
        return this
    }

}

class OceanBottomListSheetAdapter(
    private val items: List<String>,
    private val onSelect: (Int) -> Unit
): RecyclerView.Adapter<OceanBottomListSheetAdapter.OceanBottomListSheetViewHolder>() {

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
            itemBinding.textOceanSubtitle1.text = items[position]
            itemBinding.root.setOnClickListener { onSelect(position) }
        }

    }

}