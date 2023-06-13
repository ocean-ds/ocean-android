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

    }
}