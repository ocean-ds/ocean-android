package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanChartLegendAdapter
import br.com.useblu.oceands.model.OceanDonutModel

@BindingAdapter("items")
fun setChartCardAdapter(
    recyclerView: RecyclerView,
    model: OceanDonutModel?
) {
    model?.let {
        val oceanShortcutsListAdapter = OceanChartLegendAdapter(it)
        recyclerView.adapter = oceanShortcutsListAdapter
    }

}