package br.com.useblu.oceands.core

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.OceanBottomListSheet
import br.com.useblu.oceands.R


@BindingAdapter("setAdapterSpinner")
fun setAdapterSpinner(spinner: Spinner, list: List<String>?) {
    list?.let { items ->
        val adapter: ArrayAdapter<String> = object : ArrayAdapter<String>(
            spinner.context,
            R.layout.ocean_dropdown_menu_item,
            items
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
        adapter.notifyDataSetChanged()
    }
}


@SuppressLint("ClickableViewAccessibility")
@BindingAdapter("setAdapterBottomSheet", "limit", "title", "hint", "valueSelected")
fun setAdapterBottomSheet(
    spinner: Spinner,
    list: List<String>?,
    limit: Int?,
    title: String?,
    hint: String?,
    valueSelected: Int?
) {
    list?.let { items ->
        val adapter = ArrayAdapter(
            spinner.context,
            R.layout.ocean_dropdown_menu_item,
            items
        )
        spinner.adapter = adapter
        spinner.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {

                val selectedItemPosition = if (spinner.selectedItemPosition == 0) {
                    -1
                } else {
                    spinner.selectedItemPosition - 1
                }

                val bottomSheet = OceanBottomListSheet(spinner.context)
                    .withTitle(title.toString())
                    .withHint(hint.toString())

                limit?.let {
                    if (it > 0) {
                        bottomSheet.withSearch(
                                manager = spinner.context.getSupportFragmentManager(),
                                limit = limit,
                            )
                    }
                }

                bottomSheet.withSimpleList(
                    items = items.drop(1),
                    selectedPosition = selectedItemPosition,
                    onItemSelect = { posSelected ->
                        spinner.setSelection(posSelected + 1)
                    }
                ).show()
            }
            true
        }

        valueSelected?.let {
            spinner.setSelection(it)
        }
        adapter.notifyDataSetChanged()
    }
}

@BindingAdapter("spinnerSetDisabled")
fun spinnerSetDisabled(spinner: Spinner, disabled: Boolean?) {
    spinner.isEnabled = disabled?.not() ?: true
}
