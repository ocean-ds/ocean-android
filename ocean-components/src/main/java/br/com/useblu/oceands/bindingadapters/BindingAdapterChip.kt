package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanChipListAdapter
import br.com.useblu.oceands.model.OceanChip

@BindingAdapter("chips")
fun setChipsAdapter(
    recyclerView: RecyclerView,
    chips: List<OceanChip>?
) {
    chips ?: return

    if (recyclerView.adapter == null) {
        val adapter = OceanChipListAdapter()
        recyclerView.adapter = adapter.apply {
            submitList(chips)
        }
    } else {
        (recyclerView.adapter as OceanChipListAdapter).apply {
            submitList(chips)
        }
    }
}