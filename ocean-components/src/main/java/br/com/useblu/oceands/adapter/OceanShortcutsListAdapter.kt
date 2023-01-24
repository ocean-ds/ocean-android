package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.ItemShortcutsAdapterOceanBinding
import br.com.useblu.oceands.model.OceanShortcutItem

class OceanShortcutsListAdapter(
    private val items: List<OceanShortcutItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val itemBinding = ItemShortcutsAdapterOceanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OceanShortcutsListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as OceanShortcutsListViewHolder).bindView(position)
    }

    override fun getItemCount(): Int = items.size

    inner class OceanShortcutsListViewHolder(
        private val itemBinding: ItemShortcutsAdapterOceanBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.item = items[position]
            itemBinding.executePendingBindings()
        }
    }
}