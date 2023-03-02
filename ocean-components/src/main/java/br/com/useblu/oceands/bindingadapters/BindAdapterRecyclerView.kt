package br.com.useblu.oceands.bindingadapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanTabItemBinding

@BindingAdapter("setLabels", "setCounters", "setTabSelected", "setDefaultSelected")
fun setLabels(
    recyclerView: RecyclerView,
    labels: List<String>?,
    counters: List<Int>?,
    selected: (Int) -> Unit,
    defaultSelected: Int?
) {
    labels?.let {
        recyclerView.adapter = OceanTabAdapter(
           labels =  labels,
           counters =  counters,
            defaultSelected = defaultSelected,
        ) { positionSelected ->
            selected.invoke(positionSelected)
        }
    }
}

private class OceanTabAdapter(
    val labels: List<String>,
    val counters: List<Int>?,
    val defaultSelected: Int?,
    val onSelect: (Int) -> Unit,
) : RecyclerView.Adapter<OceanTabAdapter.ItemViewHolder>() {

    private var selected = defaultSelected ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = OceanTabItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        calculateItemWidth(parent, view.root)
        return ItemViewHolder(view)
    }

    private fun calculateItemWidth(parent: ViewGroup, view: View) {
        if (labels.size < 5) {
            val totalWidth = parent.measuredWidth
            val itemWidth = totalWidth / labels.size
            view.layoutParams.apply {
                width = itemWidth
            }
        } else {
            view.layoutParams.apply {
                width = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holderItem: ItemViewHolder, position: Int) {

        holderItem.bind(
            label = labels[position],
            counter = counters?.let { it[position] },
            position = position
        ) { pos ->
            selected = pos
            onSelect(pos)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = labels.size

    inner class ItemViewHolder(private val item: OceanTabItemBinding) : RecyclerView.ViewHolder(item.root) {

        fun bind(label: String, counter: Int?, position: Int, onItemSelect: (Int) -> Unit) {
            val context = item.root.context
            item.run {
                val isSelected = position == selected
                val textColor: Int
                val backgroundTag: Int

                if (isSelected) {
                    textColor = R.color.ocean_color_brand_primary_pure
                    backgroundTag = R.drawable.ocean_tag_primary_background
                    divider.visibility = View.VISIBLE
                } else {
                    textColor = R.color.ocean_color_interface_dark_up
                    backgroundTag = R.drawable.ocean_tag_dark_background
                    divider.visibility = View.GONE
                }
                textViewLabel.setTextColor(ContextCompat.getColor(context, textColor))
                linearLayoutTag.background = ContextCompat.getDrawable(context, backgroundTag)
                textViewLabel.text = label

                counter?.let {
                    textViewCounter.text = counter.toString()
                    linearLayoutTag.visibility = View.VISIBLE
                    space2.visibility = View.VISIBLE
                } ?: run {
                    linearLayoutTag.visibility = View.GONE
                    space2.visibility = View.GONE
                }

                item.rootConstraintLayout.setOnClickListener { onItemSelect.invoke(position) }
            }
        }

    }

}
