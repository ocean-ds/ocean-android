package br.com.useblu.oceands.bindingadapters

import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("app:isChecked", "app:setOnChecked")
fun setOnChecked(
    switch: SwitchCompat,
    isChecked: Boolean,
    setOnChecked: (Boolean) -> Unit
) {
    switch.setOnCheckedChangeListener(null)
    switch.isChecked = isChecked
    switch.setOnCheckedChangeListener { _, selected -> setOnChecked.invoke(selected) }
}
