package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.components.OceanDonutView
import br.com.useblu.oceands.model.OceanDonutModel

@BindingAdapter("donut_model")
fun OceanDonutView.setupDonutModel(model: OceanDonutModel) {
    this.setupDonut(model)
}