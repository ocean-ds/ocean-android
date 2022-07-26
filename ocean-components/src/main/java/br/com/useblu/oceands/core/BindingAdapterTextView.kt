package br.com.useblu.oceands.core

import android.graphics.Paint
import android.graphics.Typeface
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R

@BindingAdapter("app:highlight")
fun setHighlight(textView: TextView, highlight: Boolean) {
    if (highlight) textView.setTypeface(null, Typeface.BOLD)
}

@BindingAdapter("app:ocean_strike")
fun setStrike(textView: TextView, status: Boolean) {
    if (status) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

@BindingAdapter("app:textColor")
fun setTextColor(textView: TextView, color: String?) {
    val context = textView.context
    var colorSelect =  R.color.ocean_color_interface_dark_down

    color?.let{
        colorSelect = when (color) {
            "colorStatusNeutralDeep" ->  R.color.ocean_color_status_neutral_deep
            "colorInterfaceDarkPure" -> R.color.ocean_color_interface_dark_pure
            "colorStatusPositiveDeep" -> R.color.ocean_color_status_positive_deep
            "colorInterfaceDarkDown" -> R.color.ocean_color_interface_dark_down
            "colorInterfaceDarkUp" -> R.color.ocean_color_interface_dark_up
            else -> R.color.ocean_color_interface_dark_down
        }
    }

    textView.setTextColor(ContextCompat.getColor(context,colorSelect))
}
