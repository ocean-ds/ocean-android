package br.com.useblu.oceands.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.useblu.oceands.databinding.OceanChipOptionActionItemBinding
import br.com.useblu.oceands.databinding.OceanChipOptionItemBinding
import br.com.useblu.oceands.model.OceanChipFilterOptions
import br.com.useblu.oceands.model.OceanFilterChip

internal class OceanFilterChipMultipleOptionsAdapter(
    private val context: Context,
    private val chipItem: OceanFilterChip,
    private val closeDropdownCallback: (parent: ViewGroup) -> Unit
): OceanFilterChipBaseOptionsAdapter(context, chipItem) {

    private val internalItems = chipItem.filterOptions.items.map { it.copy() }

    override fun getCount(): Int {
        return super.getCount() + 1
    }

    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        if (position == count - 1) {
            if (convertView != null) return convertView

            val filterOptions = chipItem.filterOptions as OceanChipFilterOptions.MultipleChoice
            return setupButtonBar(parent, filterOptions)
        }

        return setupAdapterItem(convertView, parent, position)
    }

    private fun setupAdapterItem(
        convertView: View?,
        parent: ViewGroup,
        position: Int
    ): View {
        val viewHolder: ViewHolder
        val viewToReturn: View
        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            val view = OceanChipOptionItemBinding.inflate(layoutInflater, parent, false)

            viewHolder = ViewHolder(view.textView, view.checkbox, view.layout)

            view.root.setOnClickListener {
                view.checkbox.performClick()
            }

            view.root.tag = viewHolder
            viewToReturn = view.root
        } else {
            viewToReturn = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        val item = getItem(position) ?: return View(context)

        viewHolder.textView.text = item.title

        viewHolder.checkbox.visibility = View.VISIBLE
        viewHolder.checkbox.isChecked = item.isSelected
        viewHolder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            val itemToEdit = internalItems[position]
            itemToEdit.isSelected = isChecked
        }

        return viewToReturn
    }

    private fun setupButtonBar(
        parent: ViewGroup,
        filterOptions: OceanChipFilterOptions.MultipleChoice
    ): View {
        val layoutInflater = LayoutInflater.from(context)
        val view = OceanChipOptionActionItemBinding.inflate(layoutInflater, parent, false)

        view.buttonPrimary.text = filterOptions.primaryButtonLabel
        view.buttonSecondary.text = filterOptions.secondaryButtonLabel

        view.buttonSecondary.click = {
            closeDropdownCallback(parent)
        }

        view.buttonPrimary.click = {
            val selectedIndexes = mutableListOf<Int>()
            filterOptions.items.forEachIndexed { index, filterOptionsItem ->
                filterOptionsItem.isSelected = internalItems[index].isSelected
                if (filterOptionsItem.isSelected) selectedIndexes.add(index)
            }

            filterOptions.onCloseOptions(selectedIndexes)
            closeDropdownCallback(parent)
        }

        return view.root
    }
}