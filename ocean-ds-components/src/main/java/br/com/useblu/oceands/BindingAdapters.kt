package br.com.useblu.oceands

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton

@BindingAdapter("app:ocean_visible_or_invisible")
fun setVisibleOrInvisible(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("app:ocean_visible_or_gone")
fun setVisibleOrGone(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.GONE
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

@BindingAdapter("app:ocean_drawable_padding")
fun setOceanDrawablePadding(button: Button, @DimenRes dimenId: Int) {
    var dimensionPixelSize = 0

    if (dimenId != 0) {
        dimensionPixelSize = button.context.resources.getDimensionPixelSize(dimenId)
    }

    if (button is MaterialButton) {
        button.iconPadding = dimensionPixelSize
    } else {
        button.compoundDrawablePadding = dimensionPixelSize
    }
}
