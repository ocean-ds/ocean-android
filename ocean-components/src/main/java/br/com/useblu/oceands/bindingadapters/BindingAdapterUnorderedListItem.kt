package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanUnorderedListAdapter
import br.com.useblu.oceands.model.OceanUnorderedListItem

@BindingAdapter("entries", "canScroll")
fun setUnorderedListAdapter(
    recyclerView: RecyclerView,
    entries: List<OceanUnorderedListItem>?,
    canScroll: Boolean?
) {
    entries?.let { list ->
        val oceanOrderedListAdapter = OceanUnorderedListAdapter(items = list)

        recyclerView.adapter = oceanOrderedListAdapter
        recyclerView.layoutManager = object : LinearLayoutManager(
            recyclerView.context,
            VERTICAL,
            false
        ) {
            override fun canScrollVertically(): Boolean {
                return canScroll ?: true
            }
        }
    }
}
