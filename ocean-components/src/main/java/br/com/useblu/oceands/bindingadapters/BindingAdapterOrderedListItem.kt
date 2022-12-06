package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanOrderedListAdapter

@BindingAdapter("entries")
fun setOrderedListAdapter(
    recyclerView: RecyclerView,
    entries: List<String>?
) {
    entries?.let { list ->
        val oceanOrderedListAdapter = OceanOrderedListAdapter(items = list)

        recyclerView.adapter = oceanOrderedListAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            recyclerView.context,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}
