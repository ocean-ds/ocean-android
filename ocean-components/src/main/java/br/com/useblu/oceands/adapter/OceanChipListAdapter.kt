package br.com.useblu.oceands.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.components.OceanOptionsBottomListSheet
import br.com.useblu.oceands.databinding.OceanBasicChipItemBinding
import br.com.useblu.oceands.databinding.OceanFilterChipItemBinding
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanBasicChip
import br.com.useblu.oceands.model.OceanChip
import br.com.useblu.oceands.model.OceanChipFilterOptions
import br.com.useblu.oceands.model.OceanChipItemState
import br.com.useblu.oceands.model.OceanFilterChip

class OceanChipListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<OceanChip>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ChipViewType.BASIC.value -> {
                val itemBinding = OceanBasicChipItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )

                OceanBasicChipViewHolder(itemBinding)
            }

            ChipViewType.FILTER.value -> {
                val itemBinding = OceanFilterChipItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )

                OceanFilterChipViewHolder(itemBinding)
            }
            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is OceanBasicChip -> ChipViewType.BASIC.value
            is OceanFilterChip -> ChipViewType.FILTER.value
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val item = items[position]

        when (holder) {
            is OceanBasicChipViewHolder -> {
                holder.bindView(item as OceanBasicChip)
            }
            is OceanFilterChipViewHolder -> {
                holder.bindView(item as OceanFilterChip)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(chips: List<OceanChip>) {
        items.clear()
        items.addAll(chips)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    private val currentSelectedItem: OceanChip?
        get() = items.find { it.state == OceanChipItemState.DEFAULT }

    private fun selectItem(item: OceanChip) {
        val selectedItem = items.find { it.id == item.id }
        selectedItem?.state = OceanChipItemState.DEFAULT
    }

    private fun unselectCurrent() {
        currentSelectedItem?.state = OceanChipItemState.INACTIVE_HOVER
    }

    inner class OceanBasicChipViewHolder(
        private val binding: OceanBasicChipItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bindView(item: OceanBasicChip) {
            val context = binding.root.context

            binding.item = item
            setBadge(item)
            binding.label.setTextColor(getContentColor(item, context))
            binding.leftIcon.setColorFilter(getContentColor(item, context))

            binding.chipListItemBackground.background = getBackgroundDrawable(item, context)

            binding.chipListItemBackground.setOnClickListener {
                if (item != currentSelectedItem) {
                    unselectCurrent()
                    selectItem(item)
                    item.onClick.invoke(true)

                    notifyDataSetChanged()
                } else {
                    unselectCurrent()
                    item.onClick.invoke(false)
                    notifyDataSetChanged()
                }
            }
        }

        private fun setBadge(item: OceanBasicChip) {
            item.badge ?: return

            if (item.badge.text <= 0) {
                item.badge.type = OceanBadgeType.DISABLED
                return
            }

            if (item.state == OceanChipItemState.DEFAULT) {
                if (item.badge.type == OceanBadgeType.PRIMARY) {
                    item.badge.type = OceanBadgeType.PRIMARY_INVERTED
                }
            } else {
                if (item.badge.type == OceanBadgeType.PRIMARY_INVERTED) {
                    item.badge.type = OceanBadgeType.PRIMARY
                }
            }
        }
    }

    inner class OceanFilterChipViewHolder(
        private val binding: OceanFilterChipItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: OceanFilterChip) {
            val context = binding.root.context

            binding.item = item
            binding.chipListItemBackground.background = getBackgroundDrawable(item, context)
            binding.label.setTextColor(getContentColor(item, context))
            binding.icon.setColorFilter(getContentColor(item, context), android.graphics.PorterDuff.Mode.SRC_IN)

            binding.chipListItemBackground.setOnClickListener {
                showBottomSheet(it.context, item)
            }
        }

        private fun showBottomSheet(context: Context, chip: OceanFilterChip) {
            val options = chip.filterOptions
            val internalItems = options.items.map { it.copy() }

            val bottomSheet = OceanOptionsBottomListSheet(context)
                .withTitle(options.title)

            when (options) {
                is OceanChipFilterOptions.MultipleChoice -> {
                    val adapter = OceanFilterChipMultipleOptionsAdapter(internalItems)
                    bottomSheet.withCustomList(adapter)

                    bottomSheet.withFooterButton(options.primaryButtonLabel, options.secondaryButtonLabel) {
                        val selectedItems = internalItems.mapIndexed { index, filterOptionsItem ->
                            if (filterOptionsItem.isSelected) {
                                index
                            } else {
                                -1
                            }
                        }.filter { it != -1 }

                        options.onPrimaryButtonClick(selectedItems)
                    }
                }
                is OceanChipFilterOptions.SingleChoice -> {
                    val adapter = OceanFilterChipSingleOptionsAdapter(options) {
                        options.onSelectItem(it)
                        bottomSheet.dismiss()
                    }
                    bottomSheet.withCustomList(adapter)
                }
            }

            bottomSheet.show()
        }
    }

    private fun getContentColor(
        item: OceanChip,
        context: Context
    ) = when (item.state) {
        OceanChipItemState.INACTIVE_HOVER -> ContextCompat.getColor(
            context,
            R.color.ocean_color_brand_primary_pure
        )
        OceanChipItemState.DISABLED_ACTIVE,
        OceanChipItemState.DISABLED_INACTIVE -> ContextCompat.getColor(
            context,
            R.color.ocean_color_interface_dark_up
        )
        OceanChipItemState.ACTIVE_HOVER,
        OceanChipItemState.DEFAULT -> ContextCompat.getColor(
            context,
            R.color.ocean_color_interface_light_pure
        )
    }

    private fun getBackgroundDrawable(
        item: OceanChip,
        context: Context
    ) = when (item.state) {
        OceanChipItemState.INACTIVE_HOVER -> ContextCompat.getDrawable(
            context,
            R.drawable.ocean_chip_inactive_hover
        )
        OceanChipItemState.ACTIVE_HOVER -> ContextCompat.getDrawable(
            context,
            R.drawable.ocean_chip_active_hover
        )
        OceanChipItemState.DEFAULT -> ContextCompat.getDrawable(
            context,
            R.drawable.ocean_chip_default
        )
        OceanChipItemState.DISABLED_ACTIVE,
        OceanChipItemState.DISABLED_INACTIVE -> ContextCompat.getDrawable(
            context,
            R.drawable.ocean_chip_disabled
        )
    }

    private enum class ChipViewType(val value: Int) {
        BASIC(1),
        FILTER(2)
    }
}