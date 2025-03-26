package br.com.useblu.oceands.bindingadapters

import android.graphics.Rect
import android.view.View
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

        recyclerView.addItemDecoration(SpacesItemDecoration(space = 10, columns = columns ?: 2))
    }
}

class SpacesItemDecoration(
    private val space: Int,
    private val columns: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
        outRect.top = space

        if (parent.getChildAdapterPosition(view) % columns == 0) {
            outRect.left = 0
        }

        if (parent.getChildAdapterPosition(view) % columns == (columns - 1)) {
            outRect.right = 0
        }
    }
}
