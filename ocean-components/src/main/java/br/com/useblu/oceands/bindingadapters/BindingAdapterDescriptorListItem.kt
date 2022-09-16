package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanDescriptorListAdapter
import br.com.useblu.oceands.model.OceanDescriptorListItem

@BindingAdapter("entries")
fun setDescriptorAdapter(
    recyclerView: RecyclerView,
    entries: List<OceanDescriptorListItem>?
) {
    entries?.let { list ->
        val oceanDescriptorListAdapter =
            OceanDescriptorListAdapter(
                items = list
            )

        recyclerView.adapter = oceanDescriptorListAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            recyclerView.context,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}
