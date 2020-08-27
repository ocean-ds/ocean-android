package br.com.useblu.oceands

import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("app:ocean_visible_or_invisible")
fun setVisibleOrInvisible(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("app:ocean_text_span_bold")
fun setTextSpanBold(view: TextView, text: String?) {
    view.text = HtmlCompat.fromHtml("<html><body>$text</body></html>", HtmlCompat.FROM_HTML_MODE_LEGACY)
}
