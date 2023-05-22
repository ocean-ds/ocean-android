package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanChartLegendAdapter
import br.com.useblu.oceands.model.OceanDonutItem

@BindingAdapter("items")
fun setChartCardAdapter(
    recyclerView: RecyclerView,
    items: List<OceanDonutItem>?
) {
    items?.let { list ->
        val oceanShortcutsListAdapter = OceanChartLegendAdapter(list)
        recyclerView.adapter = oceanShortcutsListAdapter
    }
}