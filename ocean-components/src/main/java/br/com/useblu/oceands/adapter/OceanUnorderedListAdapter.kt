package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanUnorderedListItemBinding
import br.com.useblu.oceands.model.OceanUnorderedListItem

class OceanUnorderedListAdapter(
    private val items: List<OceanUnorderedListItem>,
) : RecyclerView.Adapter<OceanUnorderedListAdapter.OceanUnorderedListItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OceanUnorderedListItemViewHolder = OceanUnorderedListItemViewHolder(
        OceanUnorderedListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: OceanUnorderedListItemViewHolder,
        position: Int
    ) = holder.bindView(items[position])

    override fun getItemCount(): Int = items.size

    inner class OceanUnorderedListItemViewHolder(
        private val itemBinding: OceanUnorderedListItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(item: OceanUnorderedListItem) {
            itemBinding.icon = item.icon
            itemBinding.text = item.title
            itemBinding.needsRoundBackground = item.needsRoundBackgroundIcon
            itemBinding.needTrailingSpacer = item.needTrailingSpacer
            itemBinding.needLeadingSpacer = item.needLeadingSpacer
        }
    }
}
