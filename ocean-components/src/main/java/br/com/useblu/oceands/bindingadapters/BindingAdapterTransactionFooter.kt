package br.com.useblu.oceands.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanTransactionFooterListAdapter
import br.com.useblu.oceands.model.OceanInlineTextList

@BindingAdapter("entries")
fun setTransactionFooterAdapter(
    recyclerView: RecyclerView,
    entries: List<OceanInlineTextList>?
) {
    entries?.let { list ->
        val oceanTransactionFooterListAdapter =
            OceanTransactionFooterListAdapter(
                items = list
            )

        recyclerView.adapter = oceanTransactionFooterListAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            recyclerView.context,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}
