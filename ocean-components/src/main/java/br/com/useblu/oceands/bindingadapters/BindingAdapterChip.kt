package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanChipListAdapter
import br.com.useblu.oceands.model.OceanBasicChipItem

@BindingAdapter("chips")
fun setChipsAdapter(
    recyclerView: RecyclerView,
    chips: List<OceanBasicChipItem>
) {
    val adapter = OceanChipListAdapter()
    recyclerView.adapter = adapter.apply {
        submitList(chips)
    }
}