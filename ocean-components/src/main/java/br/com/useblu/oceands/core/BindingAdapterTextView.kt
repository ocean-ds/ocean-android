package br.com.useblu.oceands.core

import android.graphics.Typeface
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:highlight")
fun setHighlight(textView: TextView, highlight: Boolean) {
    if (highlight) textView.setTypeface(null, Typeface.BOLD)
}
