package br.com.useblu.oceands.core

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R
import com.github.florent37.expansionpanel.ExpansionLayout

@BindingAdapter("setCustomBehavior")
fun setCustomBehavior(expansionLayout: ExpansionLayout, setCustomBehavior: Boolean) {
    if (setCustomBehavior.not()) return

    val linearLayout = expansionLayout.parent as LinearLayout
    val expansionHeader =
        linearLayout.findViewById<ConstraintLayout>(R.id.container_expansion_header)
    val icon = expansionHeader.findViewById<ImageView>(R.id.icon)
    val textviewHeaderLabel = expansionHeader.findViewById<TextView>(R.id.text_view_header_label)
    val textviewHeaderValue = expansionHeader.findViewById<TextView>(R.id.text_view_header_value)
    val textViewHeaderTitle = expansionHeader.findViewById<TextView>(R.id.text_view_header_title)
    val headerIndicator = expansionHeader.findViewById<ImageView>(R.id.header_indicator)

    expansionLayout.addListener { _, expanded ->
        if (expanded) {
            icon.animateFadeOut()
            textviewHeaderLabel.animateFadeOut()
            textviewHeaderValue.animateFadeOut()
            textViewHeaderTitle.animateFadeIn()
            headerIndicator.setColorFilter(
                ContextCompat.getColor(
                    headerIndicator.context,
                    R.color.ocean_color_brand_primary_pure
                ), android.graphics.PorterDuff.Mode.SRC_IN
            )
        } else {
            icon.animateFadeIn()
            textviewHeaderLabel.animateFadeIn()
            textviewHeaderValue.animateFadeIn()
            textViewHeaderTitle.animateFadeOut()
            headerIndicator.setColorFilter(
                ContextCompat.getColor(
                    headerIndicator.context,
                    R.color.ocean_color_interface_dark_deep
                ), android.graphics.PorterDuff.Mode.SRC_IN
            )
        }
    }
}
