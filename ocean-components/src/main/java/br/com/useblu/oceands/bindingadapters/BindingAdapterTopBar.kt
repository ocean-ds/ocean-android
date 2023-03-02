package br.com.useblu.oceands.bindingadapters

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter

@BindingAdapter("setNavigationOnClick")
fun setNavigationOnClick(toolbar: Toolbar, click: (() -> Unit?)? = null) {
    click?.let {
        toolbar.setNavigationOnClickListener {
            it()
        }
    }

}
