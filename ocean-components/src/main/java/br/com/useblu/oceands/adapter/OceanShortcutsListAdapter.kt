package br.com.useblu.oceands.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.core.OceanBadgeType
import br.com.useblu.oceands.databinding.ItemShortcutsAdapterOceanBinding
import br.com.useblu.oceands.databinding.ItemShortcutsHighlightAdapterOceanBinding

class OceanShortcutsListAdapter(
    private val items: List<OceanShortcutItem>,
    private val isHighlight: Boolean,
    private val onClickItem: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (isHighlight) {
            val itemBinding = ItemShortcutsHighlightAdapterOceanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            OceanShortcutsHighlightListViewHolder(itemBinding)
        } else {
            val itemBinding = ItemShortcutsAdapterOceanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            OceanShortcutsListViewHolder(itemBinding)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (isHighlight) {
            (holder as OceanShortcutsHighlightListViewHolder).bindView(position)
        } else {
            (holder as OceanShortcutsListViewHolder).bindView(position)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class OceanShortcutsHighlightListViewHolder(
        private val itemBinding: ItemShortcutsHighlightAdapterOceanBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.item = items[position]
            itemBinding.cardView.setOnClickListener {
                onClickItem(position)
            }
            itemBinding.executePendingBindings()
        }
    }

    inner class OceanShortcutsListViewHolder(
        private val itemBinding: ItemShortcutsAdapterOceanBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.item = items[position]
            itemBinding.cardView.setOnClickListener {
                onClickItem(position)
            }
            itemBinding.executePendingBindings()
        }
    }

}

data class OceanShortcutItem(
    val iconUrl: String? = null,
    val label: String,
    val count: String? = null,
    val badgeType: OceanBadgeType? = null,
)