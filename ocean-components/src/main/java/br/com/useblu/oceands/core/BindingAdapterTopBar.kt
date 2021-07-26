package br.com.useblu.oceands.core

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setNavigationOnClick")
fun setNavigationOnClick(toolbar: Toolbar, click: (() -> Unit?)? = null) {
    click?.let {
        toolbar.setNavigationOnClickListener {
            it()
        }
    }

}
