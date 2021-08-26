package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.ItemShortcutsAdapterOceanBinding

class OceanShortcutsListAdapter(
    private val items: List<OceanShortcutItem>,
    private val onClickItem: (Int) -> Unit
) : RecyclerView.Adapter<OceanShortcutsListAdapter.OceanShortcutsListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OceanShortcutsListAdapter.OceanShortcutsListViewHolder {
        val itemBinding = ItemShortcutsAdapterOceanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OceanShortcutsListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: OceanShortcutsListAdapter.OceanShortcutsListViewHolder,
        position: Int
    ) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = items.size

    inner class OceanShortcutsListViewHolder(
        private val itemBinding: ItemShortcutsAdapterOceanBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.label.text = items[position].label
            itemBinding.icon.setImageResource(items[position].icon)
            itemBinding.cardView.setOnClickListener {
                onClickItem(position)
            }
        }
    }

}

data class OceanShortcutItem(
    val icon: Int,
    val label: String
)