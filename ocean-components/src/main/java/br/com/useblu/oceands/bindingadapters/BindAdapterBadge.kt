package br.com.useblu.oceands.bindingadapters

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.OceanBadgeType

@BindingAdapter("badge_text")
fun setBadgeText(textView: TextView, badgeText: String?) {
    badgeText?.let { text ->
        text.toIntOrNull()?.let { number ->
            textView.text = if (number > 99) "99+" else number.toString()
        } ?: run { // is string
            textView.text = text
        }
    }
}

@BindingAdapter("badge_type")
fun setBadgeType(view: View, badgeType: OceanBadgeType?) {
    val background = when (badgeType) {
        OceanBadgeType.HIGHLIGHT -> R.drawable.ocean_badge_highlight
        OceanBadgeType.PRIMARY -> R.drawable.ocean_badge_primary
        OceanBadgeType.PRIMARY_INVERTED -> R.drawable.ocean_badge_primary_inverted
        OceanBadgeType.WARNING -> R.drawable.ocean_badge_warning
        OceanBadgeType.DISABLED -> R.drawable.ocean_badge_disabled
        null -> R.drawable.ocean_badge_highlight
    }
    view.background = ContextCompat.getDrawable(view.context, background)
}