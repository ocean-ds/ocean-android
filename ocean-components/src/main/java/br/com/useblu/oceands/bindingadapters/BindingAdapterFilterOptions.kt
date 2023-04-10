package br.com.useblu.oceands.bindingadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanChipOptionItemBinding
import br.com.useblu.oceands.model.FilterOptionsItem
import br.com.useblu.oceands.model.MultipleChoice
import br.com.useblu.oceands.model.OceanChipFilterOptions


@BindingAdapter("setFilterOptionsAdapter")
fun setFilterOptionsAdapter(spinner: Spinner, options: OceanChipFilterOptions?) {
    options ?: return

    val adapter = if (options is MultipleChoice)  {
        getMultipleChoiceAdapter(spinner, options.items)
    } else {
        getSingleChoiceAdapter(spinner, options.items)
    }

    spinner.adapter = adapter
    adapter.notifyDataSetChanged()
}

private fun getMultipleChoiceAdapter(spinner: Spinner, list: List<FilterOptionsItem>): ArrayAdapter<FilterOptionsItem> {
    return getSingleChoiceAdapter(spinner, list)
}

private fun getSingleChoiceAdapter(spinner: Spinner, list: List<FilterOptionsItem>): ArrayAdapter<FilterOptionsItem> {
    return object : ArrayAdapter<FilterOptionsItem>(
        spinner.context, 0, list
    ) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            return if (convertView == null) {
                val item = getItem(position) ?: return View(spinner.context)
                val context = spinner.context

                val layoutInflater = LayoutInflater.from(context)
                val view = OceanChipOptionItemBinding.inflate(layoutInflater, parent, false)

                view.textView.text = item.title + " KKKK"

                view.root
            } else {
                convertView
            }
        }

        override fun getDropDownView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            val item = getItem(position) ?: return View(spinner.context)
            val context = spinner.context

            if (convertView != null) return convertView

            val layoutInflater = LayoutInflater.from(context)
            val view = OceanChipOptionItemBinding.inflate(layoutInflater, parent, false)

            view.textView.setTextColor(
                ContextCompat.getColor(
                    spinner.context,
                    R.color.ocean_color_interface_dark_deep
                )
            )

            view.textView.text = item.title + " asdasdaad"

            // set selected item style
            if (position == spinner.selectedItemPosition && position > 0) {

            }

            return view.root
        }
    }
}
