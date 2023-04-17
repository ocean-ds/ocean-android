package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanChipOptionSingleChoiceItemBinding
import br.com.useblu.oceands.model.FilterOptionsItem
import br.com.useblu.oceands.model.OceanChipFilterOptions

internal class OceanFilterChipSingleOptionsAdapter(
    private val options: OceanChipFilterOptions,
    private val onClickItem: (Int) -> Unit
): RecyclerView.Adapter<OceanFilterChipSingleOptionsAdapter.ChipOptionSingleChoiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipOptionSingleChoiceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = OceanChipOptionSingleChoiceItemBinding.inflate(layoutInflater, parent, false)
        return ChipOptionSingleChoiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChipOptionSingleChoiceViewHolder, position: Int) {
        val item = options.items[position]
        holder.bindView(item, position)
    }

    override fun getItemCount(): Int {
        return options.items.size
    }

    inner class ChipOptionSingleChoiceViewHolder(private val binding: OceanChipOptionSingleChoiceItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: FilterOptionsItem, position: Int) {
            binding.item = item

            binding.layout.setOnClickListener {
                onClickItem(position)
            }
        }
    }
}