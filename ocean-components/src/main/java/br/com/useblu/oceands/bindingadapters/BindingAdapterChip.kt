package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanChipListAdapter
import br.com.useblu.oceands.model.OceanChipItem

@BindingAdapter("chips")
fun setChipsAdapter(
    recyclerView: RecyclerView,
    chips: List<OceanChipItem>
) {
    val layoutManager =
        LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)

    val adapter = OceanChipListAdapter()
    recyclerView.adapter = adapter.apply {
        submitList(chips)
    }
    recyclerView.layoutManager = layoutManager
}