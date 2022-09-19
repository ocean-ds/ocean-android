package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.model.OceanShortcutItem
import br.com.useblu.oceands.adapter.OceanShortcutsListAdapter

@BindingAdapter("entries", "isHighlight")
fun setShortcutsAdapter(
    recyclerView: RecyclerView,
    entries: List<OceanShortcutItem>?,
    isHighlight: Boolean = false,
) {
    entries?.let { list ->
        val oceanShortcutsListAdapter =
            OceanShortcutsListAdapter(
                items = list,
                isHighlight = isHighlight
            )

        recyclerView.adapter = oceanShortcutsListAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            recyclerView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

    }
}