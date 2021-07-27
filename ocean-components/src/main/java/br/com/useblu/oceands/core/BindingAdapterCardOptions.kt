package br.com.useblu.oceands.core

import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R


@BindingAdapter("app:ocean_title", "app:ocean_subtitle", "app:ocean_disabled")
fun setTitleAndSubtitle(
    radioButton: RadioButton,
    title: String?,
    subtitle: String?,
    disabled: Boolean
) {

    val darkUp = ContextCompat.getColor(
        radioButton.context,
        R.color.ocean_color_interface_dark_up
    )

    val primaryDown = ContextCompat.getColor(
        radioButton.context,
        R.color.ocean_color_brand_primary_down
    )

    val html = if (disabled) {
        "<b><font color='$darkUp'>$title</font></b><br/><font color='$darkUp'>$subtitle</font>"
    } else {
        "<b><font color='$primaryDown'>$title</b></font><br/><font color='$darkUp'>$subtitle</font>"
    }

    radioButton.text = html.parseAsHtml()
}