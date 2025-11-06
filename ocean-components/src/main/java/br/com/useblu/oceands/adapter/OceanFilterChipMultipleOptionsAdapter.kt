package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import br.com.useblu.oceands.databinding.OceanChipOptionMultipleChoiceItemBinding
import br.com.useblu.oceands.databinding.OceanChipOptionMultipleChoiceWithBadgeItemBinding
import br.com.useblu.oceands.model.FilterOptionsItem

internal class OceanFilterChipMultipleOptionsAdapter(
    private val items: List<FilterOptionsItem>
) : RecyclerView.Adapter<OceanFilterChipMultipleOptionsAdapter.ChipOptionMultipleChoiceViewHolder>() {

    companion object {
        private const val VIEW_TYPE_NORMAL = 0
        private const val VIEW_TYPE_WITH_BADGE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].badgeCounter != null) {
            VIEW_TYPE_WITH_BADGE
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipOptionMultipleChoiceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_WITH_BADGE -> {
                val binding = OceanChipOptionMultipleChoiceWithBadgeItemBinding.inflate(layoutInflater, parent, false)
                ChipOptionMultipleChoiceViewHolder(binding)
            }
            else -> {
                val binding = OceanChipOptionMultipleChoiceItemBinding.inflate(layoutInflater, parent, false)
                ChipOptionMultipleChoiceViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ChipOptionMultipleChoiceViewHolder, position: Int) {
        val item = items[position]
        val isLastItem = position == items.size - 1
        holder.bindView(item, isLastItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ChipOptionMultipleChoiceViewHolder(
        private val binding: ViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: FilterOptionsItem, isLastItem: Boolean) {
            when (binding) {
                is OceanChipOptionMultipleChoiceWithBadgeItemBinding -> {
                    binding.item = item
                    binding.divider.visibility = if (isLastItem) View.GONE else View.VISIBLE
                    setupListeners(binding.layout, binding.checkbox, item)
                }
                is OceanChipOptionMultipleChoiceItemBinding -> {
                    binding.item = item
                    setupListeners(binding.layout, binding.checkbox, item)
                }
            }
        }

        private fun setupListeners(layout: View, checkbox: View, item: FilterOptionsItem) {
            layout.setOnClickListener {
                checkbox.performClick()
            }

            checkbox.setOnClickListener {
                item.isSelected = !item.isSelected
            }
        }
    }
}
