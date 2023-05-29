package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanChartLegendAdapter
import br.com.useblu.oceands.model.OceanDonutItem
import br.com.useblu.oceands.model.OceanDonutModel

@BindingAdapter("items", "itemSelected")
fun setChartCardAdapter(
    recyclerView: RecyclerView,
    model: OceanDonutModel?,
    itemSelected: (OceanDonutItem) -> Unit?
) {
    model?.let {
        val oceanShortcutsListAdapter = OceanChartLegendAdapter(it, itemSelected)
        recyclerView.adapter = oceanShortcutsListAdapter
    }

}