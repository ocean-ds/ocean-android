package br.com.useblu.oceands

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("app:ocean_visible_or_invisible")
fun setVisibleOrInvisible(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("app:ocean_text_from_html")
fun setTextFromHtml(view: TextView, text: String?) {
    view.text = HtmlCompat.fromHtml(text ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
}

@BindingAdapter("app:ocean_layout_width")
fun setLayoutWidth(view: View, width: Int) {
    val params = view?.layoutParams as ViewGroup.LayoutParams
    params.width = width
    view?.layoutParams = params
}
