package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanDescriptorListItemBinding
import br.com.useblu.oceands.databinding.OceanDividerBinding
import br.com.useblu.oceands.databinding.OceanDividerDescriptorListBinding

class OceanDescriptorListAdapter(
    private val items: List<OceanDescriptorListItem>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = when (viewType) {
        ItemViewType.ROW_ITEM.ordinal -> {
            OceanDescriptorListItemViewHolder(
                OceanDescriptorListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        else -> {
            OceanDividerListItemViewHolder(
                OceanDividerDescriptorListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]

        return if (item.isDivider == true) {
            ItemViewType.DIVIDER.ordinal
        } else {
            ItemViewType.ROW_ITEM.ordinal
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) = when (getItemViewType(position)) {
        ItemViewType.ROW_ITEM.ordinal -> {
            (holder as OceanDescriptorListItemViewHolder).bindView(position)
        }
        else -> {
            (holder as OceanDividerListItemViewHolder).bindView()
        }
    }

    override fun getItemCount(): Int = items.size

    inner class OceanDescriptorListItemViewHolder(
        private val itemBinding: OceanDescriptorListItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.item = items[position]
            itemBinding.executePendingBindings()
        }
    }

    inner class OceanDividerListItemViewHolder(
        private val itemBinding: OceanDividerDescriptorListBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView() {
            itemBinding.executePendingBindings()
        }
    }

    enum class ItemViewType {
        DIVIDER, ROW_ITEM
    }

}

data class OceanDescriptorListItem(
    val title: String? = null,
    val value: String? = null,
    val newValue: String? = null,
    val color: String? = null,
    val icon: String? = null,
    val isBold: Boolean? = false,
    val isDivider: Boolean? = false,
    val isStrike: Boolean? = !newValue.isNullOrBlank()
)
