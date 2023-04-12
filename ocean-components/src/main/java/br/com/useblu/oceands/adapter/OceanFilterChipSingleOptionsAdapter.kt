package br.com.useblu.oceands.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanChipOptionItemBinding
import br.com.useblu.oceands.model.OceanFilterChip

internal class OceanFilterChipSingleOptionsAdapter(
    private val context: Context,
    chipItem: OceanFilterChip
): OceanFilterChipBaseOptionsAdapter(context, chipItem) {
    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val item = getItem(position) ?: return View(context)

        val viewHolder: ViewHolder
        val viewToReturn: View

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            val view = OceanChipOptionItemBinding.inflate(layoutInflater, parent, false)
            viewHolder = ViewHolder(view.textView, view.checkbox, view.layout)
            view.root.tag = viewHolder
            viewToReturn = view.root
        } else {
            viewToReturn = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.textView.text = item.title
        viewHolder.checkbox.visibility = View.GONE

        if (item.isSelected) {
            viewHolder.textView.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.ocean_color_brand_primary_pure
                )
            )

            viewHolder.linearLayout.background = ContextCompat.getDrawable(
                context,
                R.drawable.ocean_options_list_selected_item_background
            )
        } else {
            viewHolder.textView.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_dark_down
                )
            )

            viewHolder.linearLayout.setBackgroundResource(0)
        }

        return viewToReturn
    }
}