package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.model.OceanShortcutItem
import br.com.useblu.oceands.adapter.OceanShortcutsListAdapter

@BindingAdapter("entries", "shortcutLayoutManager")
fun setShortcutsAdapter(
    recyclerView: RecyclerView,
    entries: List<OceanShortcutItem>?,
    layoutManager: LinearLayoutManager?
) {
    entries?.let { list ->
        val oceanShortcutsListAdapter = OceanShortcutsListAdapter(items = list)
        recyclerView.adapter = oceanShortcutsListAdapter

        if (layoutManager == null) {
            recyclerView.layoutManager = LinearLayoutManager(
                recyclerView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        } else {
            recyclerView.layoutManager = layoutManager
        }
    }
}