package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.OceanBottomListSheetUIModel
import br.com.useblu.oceands.databinding.OceanBottomListSheetWithIconItemBinding

class OceanBottomListSheetWithIconAdapter(
    private val items: List<OceanBottomListSheetUIModel>,
    private val onSelect: (Int) -> Unit,
    private val selected: Int = -1
) : RecyclerView.Adapter<OceanBottomListSheetWithIconAdapter.OceanBottomListSheetWithIconViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OceanBottomListSheetWithIconViewHolder {
        val itemBinding = OceanBottomListSheetWithIconItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
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