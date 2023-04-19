package br.com.useblu.oceands.bindingadapters

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R
import com.github.florent37.expansionpanel.ExpansionLayout

@BindingAdapter("colorTitle")
fun setColorTitle(expansionLayout: ExpansionLayout, setCustomBehavior: Boolean) {
    if (setCustomBehavior.not()) return

    val linearLayout = expansionLayout.parent as LinearLayout
    val containerAccordion = linearLayout.findViewById<ConstraintLayout>(R.id.container_accordion)
    val textViewHeaderTitle = containerAccordion.findViewById<TextView>(R.id.text_view_title)
    val headerIndicator = containerAccordion.findViewById<ImageView>(R.id.image_indicator)

    expansionLayout.addListener { _, expanded ->
        if (expanded) {
            textViewHeaderTitle.setTextColor(
                ContextCompat.getColor(
                    textViewHeaderTitle.context,
                    R.color.ocean_color_brand_primary_pure
                )
            )
            headerIndicator.setColorFilter(
                ContextCompat.getColor(
                    headerIndicator.context,
                    R.color.ocean_color_brand_primary_pure
                ), android.graphics.PorterDuff.Mode.SRC_IN
            )
        } else {
            textViewHeaderTitle.setTextColor(
                ContextCompat.getColor(
                    textViewHeaderTitle.context,
                    R.color.ocean_color_interface_dark_deep
                )
            )
            headerIndicator.setColorFilter(
                ContextCompat.getColor(
                    headerIndicator.context,
                    R.color.ocean_color_interface_dark_deep
                ), android.graphics.PorterDuff.Mode.SRC_IN
            )
        }
    }
}
