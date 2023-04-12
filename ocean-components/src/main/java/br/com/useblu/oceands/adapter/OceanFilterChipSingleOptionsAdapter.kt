package br.com.useblu.oceands.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanChipOptionItemBinding
import br.com.useblu.oceands.model.OceanFilterChip

internal class OceanFilterChipSingleOptionsAdapter(
    private val context: Context,
    private val chipItem: OceanFilterChip
): OceanFilterChipBaseOptionsAdapter(context, chipItem) {
    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val item = getItem(position) ?: return View(context)

        if (convertView != null) return convertView

        val layoutInflater = LayoutInflater.from(context)
        val view = OceanChipOptionItemBinding.inflate(layoutInflater, parent, false)

        view.textView.text = item.title

        if (item.isSelected) {
            view.textView.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.ocean_color_brand_primary_pure
                )
            )

            view.layout.background = ContextCompat.getDrawable(
                context,
                R.drawable.ocean_options_list_selected_item_background
            )
        }

        return view.root
    }
}