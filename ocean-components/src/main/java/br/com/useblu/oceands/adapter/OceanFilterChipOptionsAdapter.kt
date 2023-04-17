package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanChipOptionMultipleChoiceItemBinding
import br.com.useblu.oceands.databinding.OceanChipOptionSingleChoiceItemBinding
import br.com.useblu.oceands.model.FilterOptionsItem
import br.com.useblu.oceands.model.OceanChipFilterOptions
import br.com.useblu.oceands.model.OceanFilterChip

internal class OceanFilterChipOptionsAdapter(
    private val chipItem: OceanFilterChip
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private enum class ViewType(val value: Int) {
        SINGLE_CHOICE(1),
        MULTIPLE_CHOICE(2)
    }

    override fun getItemViewType(position: Int): Int {
        val item = chipItem.filterOptions
        return if (item is OceanChipFilterOptions.SingleChoice) {
            ViewType.SINGLE_CHOICE.value
        } else {
            ViewType.MULTIPLE_CHOICE.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when(chipItem.filterOptions) {
            is OceanChipFilterOptions.MultipleChoice -> {
                val view = OceanChipOptionMultipleChoiceItemBinding.inflate(layoutInflater, parent, false)
                ChipOptionMultipleChoiceViewHolder(view)
            }
            is OceanChipFilterOptions.SingleChoice -> {
                val view = OceanChipOptionSingleChoiceItemBinding.inflate(layoutInflater, parent, false)
                ChipOptionSingleChoiceViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = chipItem.filterOptions.items[position]

        when(chipItem.filterOptions) {
            is OceanChipFilterOptions.MultipleChoice -> {
                holder as ChipOptionMultipleChoiceViewHolder
                holder.bindView(item)
            }

            is OceanChipFilterOptions.SingleChoice -> {
                holder as ChipOptionSingleChoiceViewHolder
                holder.bindView(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return chipItem.filterOptions.items.size
    }

    inner class ChipOptionMultipleChoiceViewHolder(private val binding: OceanChipOptionMultipleChoiceItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: FilterOptionsItem) {
            binding.textView.text = item.title
            binding.checkbox.isChecked = item.isSelected
        }
    }

    inner class ChipOptionSingleChoiceViewHolder(private val binding: OceanChipOptionSingleChoiceItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: FilterOptionsItem) {
            val context = binding.root.context
            binding.textView.text = item.title

            if (item.isSelected) {
                binding.textView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.ocean_color_brand_primary_pure
                    )
                )

                binding.layout.background = ContextCompat.getDrawable(
                    context,
                    R.drawable.ocean_options_list_selected_item_background
                )
            } else {
                binding.textView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.ocean_color_interface_dark_down
                    )
                )

                binding.layout.setBackgroundResource(0)
            }
        }
    }
}