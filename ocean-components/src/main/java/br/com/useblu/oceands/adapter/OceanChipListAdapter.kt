package br.com.useblu.oceands.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.core.OceanBadgeType
import br.com.useblu.oceands.databinding.OceanChipListItemBinding

class OceanChipListAdapter(
    val items: ArrayList<OceanChipItem>,
    val selectedItem: MutableLiveData<OceanChipItem>
) : RecyclerView.Adapter<OceanChipListAdapter.OceanChipListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OceanChipListAdapter.OceanChipListViewHolder {
        val itemBinding = OceanChipListItemBinding.inflate(
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

    override fun getItemCount(): Int = items.size

    inner class OceanChipListViewHolder(
        private val itemBinding: OceanChipListItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        private val currentSelectedItem: OceanChipItem?
            get() = items.find { it.state == OceanChipItemState.SELECTED }

        @SuppressLint("NotifyDataSetChanged")
        fun bindView(item: OceanChipItem) {
            itemBinding.item = item
            setItemBackground(item)
            setItemTextColor(item)
            setIconTint(item)
            setBadge(item)
            itemBinding.onClick = {
                if (item != currentSelectedItem) {
                    if (item.hasClose) {
                        items.remove(item)
                        notifyItemRemoved(items.indexOf(item))
                    } else {
                        unselectCurrent()
                        selectItem(item)
                        selectedItem.postValue(item)
                    }
                    notifyDataSetChanged()
                }

            }
        }

        private fun setItemBackground(item: OceanChipItem) {
            val context = itemBinding.root.context
            itemBinding.chipListItemBackground.background = when (item.state) {
                OceanChipItemState.ERROR -> ContextCompat.getDrawable(
                    context,
                    R.drawable.ocean_chip_error
                )
                OceanChipItemState.SELECTED -> ContextCompat.getDrawable(
                    context,
                    R.drawable.ocean_chip_selected
                )
                else -> ContextCompat.getDrawable(
                    context,
                    R.drawable.ocean_chip_default
                )
            }
        }

        private fun setItemTextColor(item: OceanChipItem) {
            val context = itemBinding.root.context
            val textColor = when (item.state) {
                OceanChipItemState.ERROR -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_brand_primary_down
                )
                OceanChipItemState.SELECTED -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_light_pure
                )
                OceanChipItemState.DISABLED -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_dark_up
                )
                OceanChipItemState.DEFAULT -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_brand_primary_down
                )
            }

            itemBinding.label.setTextColor(textColor)
        }

        private fun setIconTint(item: OceanChipItem) {
            val context = itemBinding.root.context
            val iconColor = when (item.state) {
                OceanChipItemState.ERROR -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_brand_primary_down
                )
                OceanChipItemState.SELECTED -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_light_pure
                )
                OceanChipItemState.DISABLED -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_dark_up
                )
                OceanChipItemState.DEFAULT -> ContextCompat.getColor(
                    context,
                    R.color.ocean_color_brand_primary_down
                )
            }

            itemBinding.leftIcon.setColorFilter(iconColor)
        }

        private fun setBadge(item: OceanChipItem) {
            item.badge ?: return
            if (item.badge.text > 0) return
            
            item.badge.type = OceanBadgeType.NEUTRAL
        }

        private fun selectItem(item: OceanChipItem) {
            val selectedItem = items.find { it.id == item.id }
            selectedItem?.state = OceanChipItemState.SELECTED
        }

        private fun unselectCurrent() {
            currentSelectedItem?.state = OceanChipItemState.DEFAULT
        }
    }
}

data class OceanChipItem(
    val label: String,
    val id: Int,
    val badge: Badge? = null,
    val icon: Drawable? = null,
    val hasClose: Boolean = false,
    var state: OceanChipItemState = OceanChipItemState.DEFAULT
)

data class Badge(
    val text: Int,
    var type: OceanBadgeType
)

enum class OceanChipItemState {
    ERROR, DISABLED, SELECTED, DEFAULT
}
