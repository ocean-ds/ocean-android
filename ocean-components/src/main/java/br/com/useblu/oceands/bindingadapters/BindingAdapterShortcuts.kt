package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanShortcutsListAdapter
import br.com.useblu.oceands.model.OceanShortcutItem

@BindingAdapter("entries", "columns")
fun setShortcutsAdapter(
    recyclerView: RecyclerView,
    entries: List<OceanShortcutItem>?,
    columns: Int?
) {
    entries?.let { list ->
        val oceanShortcutsListAdapter = OceanShortcutsListAdapter(items = list)
        recyclerView.adapter = oceanShortcutsListAdapter

        recyclerView.layoutManager = GridLayoutManager(
            recyclerView.context, columns ?: 2
        )

        recyclerView.addItemDecoration(SpacesItemDecoration(10))
    }
}

class SpacesItemDecoration(
    private var space: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: android.graphics.Rect,
        view: android.view.View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
        outRect.top = space
    }
}