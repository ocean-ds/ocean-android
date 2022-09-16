package br.com.useblu.oceands.bindingadapters

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanChipListAdapter
import br.com.useblu.oceands.model.OceanChipItem


@BindingAdapter("chips", "itemsSpacing")
fun setChipsAdapter(
    recyclerView: RecyclerView,
    chips: List<OceanChipItem>,
    itemsSpacing: Float
) {
    val layoutManager =
        LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
    val divider = ItemsDivider(recyclerView.context, layoutManager.orientation, itemsSpacing)
    val drawable = ContextCompat.getDrawable(recyclerView.context, android.R.color.transparent)
    if (drawable != null) divider.setDrawable(drawable)

    recyclerView.adapter = OceanChipListAdapter(chips.toMutableList())
    recyclerView.layoutManager = layoutManager
    recyclerView.addItemDecoration(divider)
}

class ItemsDivider(
    context: Context,
    orientation: Int,
    private val itemsSpacing: Float
) :
    DividerItemDecoration(context, orientation) {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == state.itemCount - 1)
            outRect.setEmpty()
        else
            outRect.set(0, 0, itemsSpacing.toInt(), 0)
    }
}
