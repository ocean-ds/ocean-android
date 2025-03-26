package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanUnorderedListItemBinding
import br.com.useblu.oceands.model.OceanUnorderedListItem

class OceanUnorderedListAdapter(
    private val items: List<OceanUnorderedListItem>
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
            itemBinding.iconColor = getColor(item.iconColor)
            itemBinding.roundBackgroundColor = getBackgroundColor(item.roundBackgroundColor)
            itemBinding.text = item.title
            itemBinding.needsRoundBackground = item.needsRoundBackgroundIcon
            itemBinding.needTrailingSpacer = item.needTrailingSpacer
            itemBinding.needLeadingSpacer = item.needLeadingSpacer
        }

        private fun getColor(colorId: Int?) = ContextCompat.getColor(
            itemBinding.root.context,
            colorId ?: R.color.ocean_color_brand_primary_down
        )

        private fun getBackgroundColor(colorId: Int?) = ContextCompat.getColor(
            itemBinding.root.context,
            colorId ?: R.color.ocean_color_interface_light_up
        )
    }
}
