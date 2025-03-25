package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanChipOptionMultipleChoiceItemBinding
import br.com.useblu.oceands.model.FilterOptionsItem

internal class OceanFilterChipMultipleOptionsAdapter(
    private val items: List<FilterOptionsItem>
) : RecyclerView.Adapter<OceanFilterChipMultipleOptionsAdapter.ChipOptionMultipleChoiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipOptionMultipleChoiceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = OceanChipOptionMultipleChoiceItemBinding.inflate(layoutInflater, parent, false)
        return ChipOptionMultipleChoiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChipOptionMultipleChoiceViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ChipOptionMultipleChoiceViewHolder(private val binding: OceanChipOptionMultipleChoiceItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: FilterOptionsItem) {
            binding.item = item

            binding.layout.setOnClickListener {
                binding.checkbox.performClick()
            }

            binding.checkbox.setOnClickListener {
                item.isSelected = !item.isSelected
            }
        }
    }
}
