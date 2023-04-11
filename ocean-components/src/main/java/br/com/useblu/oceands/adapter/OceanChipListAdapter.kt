package br.com.useblu.oceands.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanBasicChipItemBinding
import br.com.useblu.oceands.databinding.OceanChipFilterTextViewBinding
import br.com.useblu.oceands.databinding.OceanChipOptionItemBinding
import br.com.useblu.oceands.databinding.OceanFilterChipItemBinding
import br.com.useblu.oceands.model.FilterOptionsItem
import br.com.useblu.oceands.model.MultipleChoice
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanBasicChip
import br.com.useblu.oceands.model.OceanChip
import br.com.useblu.oceands.model.OceanChipItemState
import br.com.useblu.oceands.model.OceanFilterChip
import br.com.useblu.oceands.model.SingleChoice

class OceanChipListAdapter
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<OceanChip>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == 1) {
            val itemBinding = OceanBasicChipItemBinding.inflate(
                inflater,
                parent,
                false
            )

            OceanBasicChipViewHolder(itemBinding)
        } else {
            val itemBinding = OceanFilterChipItemBinding.inflate(
                inflater,
                parent,
                false
            )

            OceanFilterChipViewHolder(itemBinding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is OceanBasicChip -> 1
            else -> 2
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
        get() = items.find { it.state == OceanChipItemState.ACTIVE }

    private fun selectItem(item: OceanChip) {
        val selectedItem = items.find { it.id == item.id }
        selectedItem?.state = OceanChipItemState.ACTIVE
    }

    private fun unselectCurrent() {
        currentSelectedItem?.state = OceanChipItemState.DEFAULT
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
                    item.action.invoke()

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
    }

    inner class OceanFilterChipViewHolder(
        private val binding: OceanFilterChipItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: OceanFilterChip) {
            val context = binding.root.context

            binding.item = item
            binding.chipListItemBackground.background = getBackgroundDrawable(item, context)

            setupSpinner(binding.spinner, item)

            binding.chipListItemBackground.setOnClickListener {
                binding.spinner.performClick()
            }
        }

        private fun setupSpinner(spinner: Spinner, chip: OceanFilterChip) {
            val context = spinner.context

            binding.spinner.setSelection(chip.filterOptions.items.indexOfFirst { it.isSelected })
            
            when (chip.filterOptions) {
                is SingleChoice -> {
                    spinner.adapter = getSingleChoiceAdapter(context, chip)

                    var isInitCall = true
                    binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                            if (isInitCall) {
                                isInitCall = false
                                return
                            }

                            chip.filterOptions.items.forEach {
                                it.isSelected = false
                            }
                            chip.filterOptions.items[position].isSelected = true
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) { }
                    }
                }
                is MultipleChoice -> {
                    spinner.adapter = getMultipleChoiceAdapter(context, chip)
                }
            }
        }

        private fun getMultipleChoiceAdapter(context: Context, chipItem: OceanFilterChip): ArrayAdapter<FilterOptionsItem> {
            return getSingleChoiceAdapter(context, chipItem)
        }

        private fun getSingleChoiceAdapter(context: Context, chipItem: OceanFilterChip): ArrayAdapter<FilterOptionsItem> {
            return object : ArrayAdapter<FilterOptionsItem>(
                context, 0, chipItem.filterOptions.items
            ) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    return if (convertView == null) {
                        val layoutInflater = LayoutInflater.from(context)
                        val view = OceanChipFilterTextViewBinding.inflate(layoutInflater, parent, false)

                        view.textView.text = chipItem.label
                        view.textView.setTextColor(getContentColor(chipItem, context))

                        view.root
                    } else {
                        convertView
                    }
                }

                override fun getDropDownView(
                    position: Int,
                    convertView: View?,
                    parent: ViewGroup
                ): View {
                    val item = getItem(position) ?: return View(context)

                    if (convertView != null) return convertView

                    val layoutInflater = LayoutInflater.from(context)
                    val view = OceanChipOptionItemBinding.inflate(layoutInflater, parent, false)

                    view.textView.text = item.title

                    if (item.isSelected) {
                        view.textView.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.ocean_color_brand_primary_pure
                            )
                        )

                        view.layout.background = ContextCompat.getDrawable(
                            context,
                            R.drawable.ocean_filter_selected_item_background
                        )
                    }

                    return view.root
                }
            }
        }
    }

    private fun getContentColor(
        item: OceanChip,
        context: Context
    ) = when (item.state) {
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

    private fun getBackgroundDrawable(
        item: OceanChip,
        context: Context
    ) = when (item.state) {
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