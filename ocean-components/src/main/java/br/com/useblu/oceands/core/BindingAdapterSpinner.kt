package br.com.useblu.oceands.core

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R

@BindingAdapter("setAdapterSpinner")
fun setAdapterSpinner(spinner: Spinner, list: List<String>) {
    val adapter: ArrayAdapter<String> = object : ArrayAdapter<String>(
        spinner.context,
        R.layout.ocean_dropdown_menu_item,
        list
    ) {
        override fun getDropDownView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            val view: TextView = super.getDropDownView(
                position,
                convertView,
                parent
            ) as TextView

            view.setTextColor(
                ContextCompat.getColor(
                    spinner.context,
                    R.color.ocean_color_interface_dark_deep
                )
            )

            // set style place holder
            if (position == 0) {
                view.setTextColor(
                    ContextCompat.getColor(
                        spinner.context,
                        R.color.ocean_color_interface_dark_up
                    )
                )
                view.setBackgroundColor(
                    ContextCompat.getColor(
                        spinner.context,
                        R.color.ocean_color_interface_light_pure
                    )
                )
            }

            // set selected item style
            if (position == spinner.selectedItemPosition && position > 0) {
                view.setBackgroundColor(
                    ContextCompat.getColor(
                        spinner.context,
                        R.color.ocean_color_interface_light_up
                    )
                )
                view.setTextColor(
                    ContextCompat.getColor(
                        spinner.context,
                        R.color.ocean_color_brand_primary_pure
                    )
                )
            }

            return view
        }

        override fun isEnabled(position: Int): Boolean {
            return position != 0
        }
    }

    spinner.adapter = adapter
}
