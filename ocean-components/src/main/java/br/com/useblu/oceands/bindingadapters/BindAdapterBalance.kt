package br.com.useblu.oceands.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R
import br.com.useblu.oceands.extensions.animateFadeIn
import br.com.useblu.oceands.extensions.animateFadeOut
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
    val textViewHeaderOtherMachinesLabel =
        expansionHeader.findViewById<TextView>(R.id.text_view_header_label_other_machines)
    val textViewHeaderOtherMachinesValue =
        expansionHeader.findViewById<TextView>(R.id.text_view_header_other_machines_value)
    val headerIndicator = expansionHeader.findViewById<ImageView>(R.id.header_indicator)
    val buttonOtherMachines = expansionHeader.findViewById<View>(R.id.button_other_machines)
    val containerDivider = expansionHeader.findViewById<View>(R.id.container_divider)
    val shadow = linearLayout.findViewById<View>(R.id.shadow)

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

            textViewHeaderOtherMachinesLabel.animateFadeOut()
            textViewHeaderOtherMachinesValue.animateFadeOut()
            buttonOtherMachines.animateFadeOut()
            containerDivider.animateFadeOut()
            shadow.animateFadeOut()

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

            textViewHeaderOtherMachinesLabel.animateFadeIn()
            textViewHeaderOtherMachinesValue.animateFadeIn()
            buttonOtherMachines.animateFadeIn()
            containerDivider.animateFadeIn()
            shadow.animateFadeIn()
        }
    }
}
