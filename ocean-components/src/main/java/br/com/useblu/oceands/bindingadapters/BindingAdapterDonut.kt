package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.components.OceanDonutView
import br.com.useblu.oceands.model.OceanDonutItem


@BindingAdapter("donut_items")
fun OceanDonutView.setVisibleOrInvisible(items: List<OceanDonutItem>) {
    this.setSectionItems(items)
}