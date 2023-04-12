package br.com.useblu.oceands.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanChipFilterTextViewBinding
import br.com.useblu.oceands.model.FilterOptionsItem
import br.com.useblu.oceands.model.OceanChip
import br.com.useblu.oceands.model.OceanChipItemState
import br.com.useblu.oceands.model.OceanFilterChip

internal abstract class OceanFilterChipBaseOptionsAdapter(
    private val context: Context,
    private val chipItem: OceanFilterChip
) : ArrayAdapter<FilterOptionsItem>(context, 0, chipItem.filterOptions.items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            val view = OceanChipFilterTextViewBinding.inflate(layoutInflater, parent, false)

            view.textView.text = chipItem.label
            view.textView.setTextColor(getTextColor(chipItem, context))

            view.root
        } else {
            convertView
        }
    }

    private fun getTextColor(
        item: OceanChip,
        context: Context
    ) = when (item.state) {
        OceanChipItemState.HOVER,
        OceanChipItemState.ACTIVE -> ContextCompat.getColor(
            context,
            R.color.ocean_color_interface_light_pure
        )
        OceanChipItemState.DISABLED -> ContextCompat.getColor(
            context,
            R.color.ocean_color_interface_dark_up
        )
        OceanChipItemState.DEFAULT -> ContextCompat.getColor(
            context,
            R.color.ocean_color_brand_primary_pure
        )
    }
}