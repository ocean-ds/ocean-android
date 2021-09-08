package br.com.useblu.oceands.core

import android.annotation.SuppressLint
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R

@SuppressLint("SetTextI18n")
@BindingAdapter("app:badge_text")
fun setBadgeText(textView: AppCompatTextView, badgeText: String?) {
    badgeText?.let { text ->
        text.toIntOrNull()?.let { number ->
            textView.text = if (number > 99) "99+" else number.toString()
        } ?: run { // is string
            textView.text = text
        }
    }
}

@BindingAdapter("app:badge_type")
fun setBadgeType(textView: AppCompatTextView, badgeType: OceanBadgeType?) {
    val background = when (badgeType) {
        OceanBadgeType.DEFAULT -> R.drawable.ocean_badge_default
        OceanBadgeType.BRAND_DEFAULT -> R.drawable.ocean_badge_brand_default
        OceanBadgeType.COMPLEMENTARY -> R.drawable.ocean_badge_complementary
        OceanBadgeType.ALERT -> R.drawable.ocean_badge_alert
        OceanBadgeType.NEUTRAL -> R.drawable.ocean_badge_neutral
        else -> R.drawable.ocean_badge_default
    }
    textView.background = ContextCompat.getDrawable(textView.context, background)
}