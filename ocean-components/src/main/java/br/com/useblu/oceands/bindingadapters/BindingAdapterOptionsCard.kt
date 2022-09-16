package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanOptionCardItem
import br.com.useblu.oceands.adapter.OceanOptionsCardAdapter


@BindingAdapter("options", "selectedItem")
fun setOptionsCardAdapter(
    recyclerView: RecyclerView,
    options: List<OceanOptionCardItem>?,
    selectedItem: (OceanOptionCardItem) -> Unit
) {
    options?.let { items ->
        val adapter =
            OceanOptionsCardAdapter(
                items = items,
                selectedItem = {
                    selectedItem.invoke(it)
                }
            )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            recyclerView.context,
            LinearLayoutManager.VERTICAL,
            false
        )

    }
}
