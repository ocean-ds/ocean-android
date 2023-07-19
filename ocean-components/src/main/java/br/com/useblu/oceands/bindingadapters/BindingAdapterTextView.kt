package br.com.useblu.oceands.bindingadapters

import android.graphics.Paint
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R

@BindingAdapter("highlight")
fun setHighlight(textView: TextView, highlight: Boolean) {
    if (highlight) textView.setTypeface(null, Typeface.BOLD)
}

@BindingAdapter("ocean_strike")
fun setStrike(textView: TextView, status: Boolean) {
    if (status) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

@BindingAdapter("textColor")
fun setTextColor(textView: TextView, color: String?) {
    val context = textView.context
    var colorSelect = R.color.ocean_color_interface_dark_down

    color?.let {
        colorSelect = when (color) {
            "colorStatusNeutralDeep" -> R.color.ocean_color_status_warning_deep
            "colorInterfaceDarkPure" -> R.color.ocean_color_interface_dark_pure
            "colorStatusPositiveDeep" -> R.color.ocean_color_status_positive_deep
            "colorInterfaceDarkDown" -> R.color.ocean_color_interface_dark_down
            "colorInterfaceDarkUp" -> R.color.ocean_color_interface_dark_up
            else -> R.color.ocean_color_interface_dark_down
        }
    }

    textView.setTextColor(ContextCompat.getColor(context, colorSelect))
}

@BindingAdapter("oceanUnderline", "ocean_text_from_html")
fun setUnderline(textView: TextView, underline: Boolean, text: String) {
    if (underline) {
        val underlineText = SpannableString(text)
        underlineText.setSpan(UnderlineSpan(), 0, underlineText.length, 0)
        textView.text = underlineText
    }
}