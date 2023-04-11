package br.com.useblu.oceands.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.useblu.oceands.databinding.OceanChipOptionActionItemBinding
import br.com.useblu.oceands.databinding.OceanChipOptionItemBinding
import br.com.useblu.oceands.model.MultipleChoice
import br.com.useblu.oceands.model.OceanFilterChip

internal class OceanFilterChipMultipleOptionsAdapter(
    private val context: Context,
    private val chipItem: OceanFilterChip
): OceanFilterChipBaseOptionsAdapter(context, chipItem) {

    override fun getCount(): Int {
        return super.getCount() + 1
    }

    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        if (convertView != null) return convertView

        return if (position == count - 1) {
            val layoutInflater = LayoutInflater.from(context)
            val view = OceanChipOptionActionItemBinding.inflate(layoutInflater, parent, false)

            chipItem.filterOptions as MultipleChoice
            view.buttonPrimary.text = chipItem.filterOptions.primaryButtonLabel
            view.buttonSecondary.text = chipItem.filterOptions.secondaryButtonLabel

            // TODO confirm and dismiss actions
            view.root
        } else {
            val item = getItem(position) ?: return View(context)

            val layoutInflater = LayoutInflater.from(context)
            val view = OceanChipOptionItemBinding.inflate(layoutInflater, parent, false)

            view.textView.text = item.title

            view.checkbox.visibility = View.VISIBLE
            view.checkbox.isChecked = item.isSelected

            view.root.setOnClickListener {
                view.checkbox.performClick()
            }

            view.root
        }
    }
}