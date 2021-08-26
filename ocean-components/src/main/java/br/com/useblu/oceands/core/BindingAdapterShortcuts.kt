package br.com.useblu.oceands.core

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanShortcutItem
import br.com.useblu.oceands.adapter.OceanShortcutsListAdapter

@BindingAdapter("entries", "isHighlight", "itemSelected")
fun setShortcutsAdapter(
    recyclerView: RecyclerView,
    entries: List<OceanShortcutItem>?,
    isHighlight: Boolean = false,
    itemSelected: (Int) -> Unit = {}
) {
    entries?.let { list ->
        val oceanShortcutsListAdapter =
            OceanShortcutsListAdapter(
                items = list,
                isHighlight = isHighlight,
                onClickItem = {
                    itemSelected.invoke(it)
                }
            )

        recyclerView.adapter = oceanShortcutsListAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            recyclerView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

    }
}