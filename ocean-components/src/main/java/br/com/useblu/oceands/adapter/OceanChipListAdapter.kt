package br.com.useblu.oceands.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanBasicChipListItemBinding
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanBasicChipItem
import br.com.useblu.oceands.model.OceanChipItemState

class OceanChipListAdapter
    : RecyclerView.Adapter<OceanChipListAdapter.OceanChipListViewHolder>() {

    private val items = mutableListOf<OceanBasicChipItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OceanChipListAdapter.OceanChipListViewHolder {
        val itemBinding = OceanBasicChipListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return OceanChipListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: OceanChipListViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.bindView(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(chips: List<OceanBasicChipItem>){
        items.clear()
        items.addAll(chips)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    inner class OceanChipListViewHolder(
        private val itemBinding: OceanBasicChipListItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        private val currentSelectedItem: OceanBasicChipItem?
            get() = items.find { it.state == OceanChipItemState.ACTIVE }

        @SuppressLint("NotifyDataSetChanged")
        fun bindView(item: OceanBasicChipItem) {
            itemBinding.item = item
            setItemBackground(item)
            setItemTextColor(item)
            setIconTint(item)
            setBadge(item)
            itemBinding.chipListItemBackground.setOnClickListener {
                if (item != currentSelectedItem) {
                    unselectCurrent()
                    selectItem(item)
                    item.action.invoke()

                    notifyDataSetChanged()
                }
            }
        }

        private fun setItemBackground(item: OceanBasicChipItem) {
            val context = itemBinding.root.context
            itemBinding.chipListItemBackground.background = when (item.state) {
                OceanChipItemState.HOVER -> ContextCompat.getDrawable(
                    context,
                    R.drawable.ocean_chip_hover
                )
                OceanChipItemState.ACTIVE -> ContextCompat.getDrawable(
                    context,
                    R.drawable.ocean_chip_active
                )
                OceanChipItemState.DEFAULT -> ContextCompat.getDrawable(
                    context,
                    R.drawable.ocean_chip_default
                )
                OceanChipItemState.DISABLED -> ContextCompat.getDrawable(
                    context,
                    R.drawable.ocean_chip_disabled
                )
            }
        }

        private fun setItemTextColor(item: OceanBasicChipItem) {
            val context = itemBinding.root.context
            val textColor = when (item.state) {
                OceanChipItemState.HOVER,
                OceanChipItemState.ACTIVE -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_light_pure
                )
                OceanChipItemState.DISABLED -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_dark_up
                )
                OceanChipItemState.DEFAULT -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_brand_primary_pure
                )
            }

            itemBinding.label.setTextColor(textColor)
        }

        private fun setIconTint(item: OceanBasicChipItem) {
            val context = itemBinding.root.context
            val iconColor = when (item.state) {
                OceanChipItemState.HOVER,
                OceanChipItemState.ACTIVE -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_light_pure
                )
                OceanChipItemState.DISABLED -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_dark_up
                )
                OceanChipItemState.DEFAULT -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_brand_primary_pure
                )
            }

            itemBinding.leftIcon.setColorFilter(iconColor)
        }

        private fun setBadge(item: OceanBasicChipItem) {
            item.badge ?: return

            if (item.badge.text <= 0) {
                item.badge.type = OceanBadgeType.DISABLED
                return
            }

            if (item.state == OceanChipItemState.ACTIVE) {
                if (item.badge.type == OceanBadgeType.PRIMARY) {
                    item.badge.type = OceanBadgeType.PRIMARY_INVERTED
                }
            } else {
                if (item.badge.type == OceanBadgeType.PRIMARY_INVERTED) {
                    item.badge.type = OceanBadgeType.PRIMARY
                }
            }
        }

        private fun selectItem(item: OceanBasicChipItem) {
            val selectedItem = items.find { it.id == item.id }
            selectedItem?.state = OceanChipItemState.ACTIVE
        }

        private fun unselectCurrent() {
            currentSelectedItem?.state = OceanChipItemState.DEFAULT
        }
    }
}