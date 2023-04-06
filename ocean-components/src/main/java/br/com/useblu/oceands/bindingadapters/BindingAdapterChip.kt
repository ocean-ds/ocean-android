package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanChipListAdapter
import br.com.useblu.oceands.model.OceanBasicChip
import br.com.useblu.oceands.model.OceanChip
import br.com.useblu.oceands.model.OceanFilterChip

@BindingAdapter("chips")
fun setChipsAdapter(
    recyclerView: RecyclerView,
    chips: List<OceanChip>?
) {
    chips ?: return
    val adapter = OceanChipListAdapter()
    recyclerView.adapter = adapter.apply {
        submitList(chips)
    }
}