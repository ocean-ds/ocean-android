package br.com.useblu.oceands.bindingadapters

import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.OceanStatusTextListItem


@BindingAdapter("statusTextListItem")
fun setStatusTextListItem(textView: TextView, status: OceanStatusTextListItem?) {
    val context = textView.context

    val color = getColorByStatus(status)

    textView.setTextColor(ContextCompat.getColor(context, color))
}

@BindingAdapter("statusTextListItem")
fun setStatusTextListItem(icon: ImageView?, status: OceanStatusTextListItem?) {
    val color = getColorByStatus(status)

    icon?.let {
        ImageViewCompat.setImageTintList(
            icon,
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    icon.context,
                    color
                )
            )
        )
    }
}

private fun getColorByStatus(status: OceanStatusTextListItem?): Int {
    return when (status) {
        OceanStatusTextListItem.Inactive -> R.color.ocean_color_interface_dark_up
        OceanStatusTextListItem.Positive -> R.color.ocean_color_status_positive_pure
        OceanStatusTextListItem.Warning -> R.color.ocean_color_status_neutral_pure
        else -> R.color.ocean_color_interface_dark_pure
    }
}
