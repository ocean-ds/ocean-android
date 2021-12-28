package br.com.useblu.oceands.core

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanChipItem
import br.com.useblu.oceands.adapter.OceanChipListAdapter


@BindingAdapter("chips", "selectedItem", "itemsSpacing")
fun setChipsAdapter(
    recyclerView: RecyclerView,
    chips: MutableLiveData<ArrayList<OceanChipItem>>,
    selectedItem: MutableLiveData<OceanChipItem>,
    itemsSpacing: Float
) {
    chips.observe(recyclerView.context as LifecycleOwner) { newChips ->
        (recyclerView.adapter as OceanChipListAdapter?)?.setData(newChips)
    }
    if (recyclerView.adapter == null) {
        val adapter = OceanChipListAdapter(
            chips.value ?: ArrayList(),
            selectedItem
        )
        val layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        val divider = ItemsDivider(recyclerView.context, layoutManager.orientation, itemsSpacing)
        val drawable = ContextCompat.getDrawable(recyclerView.context, android.R.color.transparent)
        if (drawable != null) divider.setDrawable(drawable)


        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(divider)
    }
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
